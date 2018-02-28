package com.littlehui.fantuan.controller.recharge;

import com.littlehui.fantuan.controller.BindListenerForwardComposer;
import com.littlehui.fantuan.services.bean.Recharge;
import com.littlehui.fantuan.services.service.PriceService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserPriceVB;
import com.littlehui.zk.addition.plus.utils.ZkWindUtil;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

/**
 * Created by littlehui on 2016/10/19 0019.
 */
public class RechargeEditorController extends BindListenerForwardComposer {

    @Getter
    @Setter
    Window rechargeEditWin;

    @Getter
    @Setter
    Decimalbox rechargePrice;

    @Getter
    @Setter
    Button rechargeSubmit;

    @Getter
    @Setter
    Label rechargeName;

    UserPriceVB userPriceVB;

    PriceService priceService = ApplicationContextUtil.getBean(PriceService.class);

    public void onCreate$rechargeEditWin() {
        userPriceVB = (UserPriceVB) arg.get("userPriceVB");
        rechargeName.setValue(userPriceVB.getUserName());
    }

    public void onClick$rechargeSubmit() {
        Recharge recharge = new Recharge();
        recharge.setUserCode(userPriceVB.getUserCode());
        recharge.setRechargePrice(rechargePrice.getValue());
        priceService.transRecharge(recharge);
        Events.postEvent("onNotifyOk", rechargeEditWin.getParent(), null);
        Events.postEvent("onAllRefresh", rechargeEditWin.getRoot(), null);
        ZkWindUtil.doClose(rechargeEditWin);
    }
}
