package com.littlehui.fantuan.controller;

import com.littlehui.fantuan.common.util.ListUtils;
import com.littlehui.fantuan.controller.compbean.UserPriceListBean;
import com.littlehui.fantuan.services.service.PriceService;
import com.littlehui.fantuan.services.service.UserService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserPriceVB;
import com.littlehui.fantuan.web.WebConext;
import com.littlehui.zk.addition.plus.utils.ZkConstants;
import com.littlehui.zk.addition.plus.utils.ZkConstsnts;
import com.littlehui.zk.addition.plus.utils.ZkUtils;
import com.littlehui.zk.addition.plus.utils.ZkWindUtil;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.http.SimpleSession;
import org.zkoss.zk.ui.http.ZKWebSocket;
import org.zkoss.zk.ui.util.ConventionWires;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by littlehui on 2016/10/15 0015.
 */
@Log4j
public class UserPriceListController extends GenericForwardComposer {

    private String EXCHANGE_URL = "/zul/mvc/exchange/exchangeEditor.zul";

    private String CONSUME_URL = "/zul/mvc/consume/consumeEditor.zul";

    private String RECHARGE_URL = "/zul/mvc/recharge/rechargeEditor.zul";

    private String USER_EDIT_URL = "/zul/mvc/user/userEditor.zul";

    private String CONSUME_BATCH_URL = "/zul/mvc/consume/consumeBatchEditor.zul";

    UserPriceListBean userPriceListBean = new UserPriceListBean();

    PriceService priceService = ApplicationContextUtil.getBean(PriceService.class);

    UserService userService = ApplicationContextUtil.getBean(UserService.class);

    @Getter
    public List<UserPriceVB> userPriceVoList;

    @Getter
    public ListModelList<UserPriceVB> userPriceVoListModel;

    Listitem selectedItem;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        ConventionWires.wireVariables(comp, userPriceListBean);
        //SpringUtil.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(this);
        bindListener(userPriceListBean.getUserPriceListWin());
        //showUserPrice();
    }

    public void bindListener(Window component) {
        /**
         * 转账窗口跳转
         */
        component.addEventListener("onExchange", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                Map<String, Object> args = new HashMap();
                initSelectedItem(event);
                args.put("userPriceVB", selectedItem.getValue());
                log.debug("打开转账窗口");
                Window exchangeWin = (Window) ZkUtils.createComponents(EXCHANGE_URL, userPriceListBean.getUserPriceListWin(), args);
                ZkWindUtil.doModal(exchangeWin, ZkWindUtil.WIN_MID_SIZE);
            }
        });

        /**
         * 消费窗口跳转
         */
        component.addEventListener("onConsume", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                Map<String, Object> args = new HashMap();
                initSelectedItem(event);
                args.put("userPriceVB", selectedItem.getValue());
                log.debug("进入 consume");
                Window exchangeWin = (Window) ZkUtils.createComponents(CONSUME_URL, userPriceListBean.getUserPriceListWin(), args);
                ZkWindUtil.doModal(exchangeWin, ZkWindUtil.WIN_MIN_SIZE);
            }
        });

        /**
         * 充值窗口跳转
         */
        component.addEventListener("onRecharge", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                Map<String, Object> args = new HashMap();
                initSelectedItem(event);
                args.put("userPriceVB", selectedItem.getValue());
                log.debug("进入recharge");
                Window rechargeWin = (Window) ZkUtils.createComponents(RECHARGE_URL, userPriceListBean.getUserPriceListWin(), args);
                ZkWindUtil.doModal(rechargeWin, ZkWindUtil.WIN_MIN_SIZE);
                showUserPrice();
            }
        });

        userPriceListBean.getUserPriceListWin().getRoot().addEventListener("onAllRefresh", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                showUserPrice();
            }
        });

        component.addEventListener("onNotifyOk", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                showUserPrice();
            }
        });
    }

    private void initSelectedItem(Event event) {
        Map<String, Object> args = new HashMap();
        choseSelectedItem(event);
        UserPriceVB userPriceVB = selectedItem.getValue();
        userPriceListBean.getUserPriceListBox().setSelectedItem(selectedItem);
        userPriceListBean.getUserPriceListBox().renderAll();
    }

    public Listitem choseSelectedItem(Event event) {
        selectedItem = (Listitem) ((ForwardEvent) event).getOrigin().getTarget().getParent().getParent().getParent();
        return selectedItem;
    }

    public void onCreate$userPriceListWin() {
        userPriceListBean.getBatchConsume().setVisible(WebConext.isLoginLeader());
        showUserPrice();
    }

    public void onClick$deleteButton() {
        UserPriceVB userPriceVB = userPriceListBean.getUserPriceListBox().getSelectedItem().getValue();
        userService.transDeleteUser(userPriceVB.getUserCode(), Executions.getCurrent().getRemoteAddr());
    }

    public void onClick$addButton() {
        Window exchangeWin = (Window) ZkUtils.createComponents(USER_EDIT_URL, userPriceListBean.getUserPriceListWin(), null);
        ZkWindUtil.doModal(exchangeWin, ZkWindUtil.WIN_MIN_SIZE);
    }

    public void onClick$searchButton() {
        userPriceVoList = priceService.queryByUserName(userPriceListBean.getSearchBox().getValue());
        userPriceVoListModel = new ListModelList(userPriceVoList);
        showUserPrices(userPriceVoListModel);
    }

    public void onClick$cancelFocusButton() {
        if (userPriceListBean.getUserPriceListBox().getSelectedItem() != null) {
            userPriceListBean.getUserPriceListBox().setSelectedItem(null);
            selectedItem = null;
            userPriceListBean.getUserPriceListBox().renderAll();
        }
    }

    public void onClick$batchConsume() {
        Map<String, Object> args = new HashMap();
        Set<Listitem> listItems = userPriceListBean.getUserPriceListBox().getSelectedItems();
        if (ListUtils.isNotEmpty(listItems)) {
            args.put("userPriceVBList", listItems);
            Window consumeBatchWin = (Window) ZkUtils.createComponents(CONSUME_BATCH_URL, userPriceListBean.getUserPriceListWin(), args);
            ZkWindUtil.doModal(consumeBatchWin, ZkWindUtil.WIN_MIN_SIZE);
        }
    }

    private void showUserPrice() {
        userPriceVoList = priceService.queryAllVBList();
        userPriceVoListModel = new ListModelList(userPriceVoList);
        showUserPrices(userPriceVoListModel);
    }

    private void showUserPrices(ListModelList<UserPriceVB> vbListModelList) {
        vbListModelList.setMultiple(true);
        userPriceListBean.getUserPriceListBox().setModel(vbListModelList);
        if (WebConext.isLoginLeader()) {
            userPriceListBean.getUserPriceListBox().getFellow("operateHeader").setVisible(true);
        }
        showTotalRemainPrice();
    }

    private void showTotalRemainPrice() {
        BigDecimal countTotalPrice = priceService.countTotalRemainPrice();
        countTotalPrice = countTotalPrice == null ? new BigDecimal(0) : countTotalPrice;
        userPriceListBean.getTotalRemainPrice().setValue(countTotalPrice.toString());
    }
}
