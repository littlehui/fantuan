package com.littlehui.fantuan.controller.recharge;

import com.littlehui.fantuan.controller.BindListenerForwardComposer;
import com.littlehui.fantuan.services.service.PriceService;
import com.littlehui.fantuan.services.service.UserService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserRechargeVB;
import com.littlehui.fantuan.web.WebConext;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by littlehui on 2016/10/23 0023.
 */
public class RechargeListController extends BindListenerForwardComposer {

    @Getter
    @Setter
    Listbox rechargeListListBox;

    @Getter
    @Setter
    Window rechargeListWin;

    Listitem selectedItem;

    List<UserRechargeVB> UserRechargeVBList;

    UserService userService = ApplicationContextUtil.getBean(UserService.class);

    PriceService priceService = ApplicationContextUtil.getBean(PriceService.class);

    protected void bindListener(Component component) {
        // do nothing
        component.addEventListener("onCancelRecharge", new EventListener<Event>() {
            Map<String, Object> args = new HashMap();

            @Override
            public void onEvent(Event event) throws Exception {
                choseSelectedItem(event);
                UserRechargeVB rechargeVB = selectedItem.getValue();
                priceService.transUnRecharge(rechargeVB.getId(), Executions.getCurrent().getRemoteAddr());
                showUserRecharge(null);
                Events.postEvent("onAllRefresh",rechargeListWin.getRoot() , null);
            }
        });

        rechargeListWin.getRoot().addEventListener("onAllRefresh", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                showUserRecharge(null);
            }
        });

    }

    public void onCreate$rechargeListWin() {
        showUserRecharge(null);
    }

    private void showUserRecharge(String userCode) {
        UserRechargeVBList = userService.queryUserRechargeVBs(userCode);
        rechargeListListBox.getFellow("operateHeader").setVisible(WebConext.isLoginLeader());
        rechargeListListBox.setModel(new ListModelList<>(UserRechargeVBList));
    }

    private void initSelectedItem(Event event) {
        Map<String, Object> args = new HashMap();
        choseSelectedItem(event);
        UserRechargeVB rechargeVB = selectedItem.getValue();
        rechargeListListBox.setSelectedItem(selectedItem);
        rechargeListListBox.renderAll();
    }

    public Listitem choseSelectedItem(Event event) {
        selectedItem = (Listitem) ((ForwardEvent) event).getOrigin().getTarget().getParent().getParent().getParent();
        rechargeListListBox.setSelectedItem(selectedItem);
        return selectedItem;
    }
}
