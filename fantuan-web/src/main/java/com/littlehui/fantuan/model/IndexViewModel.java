package com.littlehui.fantuan.model;

import com.littlehui.fantuan.services.service.PriceService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserPriceVB;
import com.littlehui.zk.addition.plus.utils.ZkWindUtil;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
public class IndexViewModel implements Serializable {

    @Wire
    Window userPriceListWin;

    @Getter
    @Setter
    public List<UserPriceVB> userPriceVoList;

    @Getter
    @Setter
    public ListModel<UserPriceVB> userPriceVoListModel;

    public UserPriceVB selectedPriceVB;

    public UserPriceVB getSelectedPriceVB() {
        return selectedPriceVB;
    }

    public void setSelectedPriceVB(UserPriceVB selectedPriceVB) {
        this.selectedPriceVB = selectedPriceVB;
    }

    PriceService priceService = ApplicationContextUtil.getBean(PriceService.class);

    @Init // @Init annotates a initial method
    public void init() {
        showUserPrice();
/*        userPriceListWin.addEventListener("onFinishOperate", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                showUserPrice();
            }
        });*/
    }

    private void showUserPrice() {
        userPriceVoList = priceService.queryVBListExcGod();
        userPriceVoListModel = new ListModelList<>(userPriceVoList);
    }

    /**
     * 转账
     */
    @Command
    public void exchange(@BindingParam("item") UserPriceVB userPriceVB) {
        this.selectedPriceVB = userPriceVB;
        Map<String, Object> args = new HashMap<>();
        args.put("userPriceVB", userPriceVB);
        Window window = (Window) ZkWindUtil.createComponentsWithUrlParams(userPriceListWin, "/zul/exchange/exchangeEditor.zul", args);
        ZkWindUtil.doPopup(window, ZkWindUtil.WIN_MIN_SIZE);
    }

    /**
     * 充值
     */
    @Command
    public void recharge(@BindingParam("item") UserPriceVB userPriceVB) {
        this.selectedPriceVB = userPriceVB;
        Map<String, Object> args = new HashMap<>();
        args.put("userPriceVB", userPriceVB);
        Window rechargeWin = (Window) ZkWindUtil.createComponentsWithUrlParams(userPriceListWin, "/zul/recharge/rechargeEditor.zul", args);
        ZkWindUtil.doModal(rechargeWin, ZkWindUtil.WIN_MIN_SIZE);
    }

    /**
     * 消费
     */
    @Command
    public void consume(@BindingParam("item") UserPriceVB userPriceVB) {
        this.selectedPriceVB = userPriceVB;
        Map<String, Object> args = new HashMap<>();
        args.put("userPriceVB", userPriceVB);
        Window rechargeWin = (Window) ZkWindUtil.createComponentsWithUrlParams(userPriceListWin, "/zul/consume/consumeEditor.zul", args);
        ZkWindUtil.doModal(rechargeWin, ZkWindUtil.WIN_MIN_SIZE);
    }
/*
    @Command
    public void selectItem(@BindingParam("item") UserPriceVB item) {
        //this.selectedPriceVB = item;
    }*/
}
