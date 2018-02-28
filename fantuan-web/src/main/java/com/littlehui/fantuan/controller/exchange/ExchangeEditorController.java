package com.littlehui.fantuan.controller.exchange;

import com.littlehui.fantuan.controller.BindListenerForwardComposer;
import com.littlehui.fantuan.services.bean.Exchange;
import com.littlehui.fantuan.services.bean.User;
import com.littlehui.fantuan.services.service.PriceService;
import com.littlehui.fantuan.services.service.UserService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserPriceVB;
import com.littlehui.zk.addition.plus.utils.ZkUtils;
import com.littlehui.zk.addition.plus.utils.ZkWindUtil;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.util.List;

/**
 * Created by littlehui on 2016/10/19 0019.
 */
public class ExchangeEditorController extends BindListenerForwardComposer {

    @Getter
    @Setter
    Window exchangeEditWin;

    @Getter
    @Setter
    Listbox userListBox;

    @Getter
    @Setter
    private Combobox userCombobox;

    @Getter
    @Setter
    private Textbox exchangeDetail;

    @Getter
    @Setter
    private Decimalbox exchangePrice;

    private UserPriceVB userPriceVB;

    PriceService priceService = ApplicationContextUtil.getBean(PriceService.class);

    UserService userService = ApplicationContextUtil.getBean(UserService.class);

    public void onCreate$exchangeEditWin() {
        userPriceVB = (UserPriceVB) arg.get("userPriceVB");
        List<User> allUsers = userService.getAllUsersExceptOne(userPriceVB.getUserCode());
        userCombobox.setModel(new ListModelList(allUsers));
        userListBox.setModel(new ListModelList(allUsers));
    }

    public void onClick$exchangeSubmit() {
        Exchange exchange = new Exchange();
        exchange.setExchangePrice(exchangePrice.getValue());
        exchange.setSourceUserCode(userPriceVB.getUserCode());
        exchange.setDesUserCode(userCombobox.getSelectedItem().getValue() +"");
        exchange.setExchangeDetail(exchangeDetail.getValue());
        priceService.transExchange(exchange);
        Events.postEvent("onNotifyOk", exchangeEditWin.getParent(), null);
        Events.postEvent("onAllRefresh",exchangeEditWin.getRoot() , null);
        ZkWindUtil.doClose(exchangeEditWin);
    }
}
