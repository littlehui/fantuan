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

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by littlehui on 2016/10/23 0023.
 */
public class ConsumeBatchEditorController extends BindListenerForwardComposer {

    @Getter
    @Setter
    Label consumeNum;

    @Getter
    @Setter
    Window consumeBatchWin;

    @Getter
    @Setter
    Decimalbox consumeTotalPrice;

    @Getter
    @Setter
    Decimalbox consumePerPrice;

    @Getter
    @Setter
    Textbox consumeDetail;


    List<UserPriceVB> userPriceVBList = new ArrayList<>();

    PriceService priceService = ApplicationContextUtil.getBean(PriceService.class);

    public void onCreate$consumeBatchWin() {
        Set<Listitem> set = (Set<Listitem>) arg.get("userPriceVBList");
        if (set != null && set.size() > 0) {
            for (Listitem listitem : set) {
                userPriceVBList.add((UserPriceVB) listitem.getValue());
            }
        }
        consumeNum.setValue(set.size() + "");
    }

    public void onClick$consumeSubmitButton() {
        for (UserPriceVB userPriceVB : userPriceVBList) {
            Consume consume = new Consume();
            consume.setUserCode(userPriceVB.getUserCode());
            consume.setConsumeDetail(consumeDetail.getValue());
            consume.setConsumePrice(consumePerPrice.getValue());
            priceService.transConsume(consume);
        }
        Events.postEvent("onNotifyOk", consumeBatchWin.getParent(), null);
        Events.postEvent("onAllRefresh",consumeBatchWin.getRoot() , null);
        ZkWindUtil.doClose(consumeBatchWin);
    }

    public void onChange$consumeTotalPrice() {
        BigDecimal resultValue = consumeTotalPrice.getValue().divide(new BigDecimal(userPriceVBList.size()), 2, BigDecimal.ROUND_HALF_EVEN);
        consumePerPrice.setValue(resultValue);
    }
}
