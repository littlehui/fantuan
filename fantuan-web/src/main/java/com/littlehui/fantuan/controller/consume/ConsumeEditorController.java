package com.littlehui.fantuan.controller.consume;

import com.littlehui.fantuan.controller.BindListenerForwardComposer;
import com.littlehui.fantuan.services.bean.Consume;
import com.littlehui.fantuan.services.service.PriceService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserPriceVB;
import com.littlehui.zk.addition.plus.utils.ZkWindUtil;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

/**
 * Created by littlehui on 2016/10/19 0019.
 */
public class ConsumeEditorController extends BindListenerForwardComposer {

    @Getter
    @Setter
    Decimalbox consumePrice;

    @Getter
    @Setter
    Button consumeSubmitButton;

    @Getter
    @Setter
    Textbox consumeDetail;

    @Getter
    @Setter
    Window consumeWin;

    @Getter
    @Setter
    Label consumeName;

    UserPriceVB userPriceVB;

    PriceService priceService = ApplicationContextUtil.getBean(PriceService.class);

    public void onCreate$consumeWin() {
        userPriceVB = (UserPriceVB) arg.get("userPriceVB");
        consumeName.setValue(userPriceVB.getUserName());
    }

    public void onClick$consumeSubmitButton() {
        Consume consume = new Consume();
        consume.setUserCode(userPriceVB.getUserCode());
        consume.setConsumeDetail(consumeDetail.getValue());
        consume.setConsumePrice(consumePrice.getValue());
        priceService.transConsume(consume);
        Events.postEvent("onNotifyOk", consumeWin.getParent(), null);
        Events.postEvent("onAllRefresh",consumeWin.getRoot() , null);
        ZkWindUtil.doClose(consumeWin);
    }
}
