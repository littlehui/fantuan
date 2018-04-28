package com.littlehui.fantuan.controller.exchange;

import com.littlehui.fantuan.controller.BindListenerForwardComposer;
import com.littlehui.fantuan.services.service.PriceService;
import com.littlehui.fantuan.services.service.UserService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserExchangeVB;
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
public class ExchangeListController extends BindListenerForwardComposer {

    @Getter
    @Setter
    Listbox exchangeListListBox;

    @Getter
    @Setter
    Window exchangeListWin;

    Listitem selectedItem;

    List<UserExchangeVB> UserExchangeVBList;

    UserService userService = ApplicationContextUtil.getBean(UserService.class);

    PriceService priceService = ApplicationContextUtil.getBean(PriceService.class);

    @Override
    protected void bindListener(Component component) {
        // do nothing
        component.addEventListener("onCancelExchange", new EventListener<Event>() {
            Map<String, Object> args = new HashMap();

            @Override
            public void onEvent(Event event) throws Exception {
                choseSelectedItem(event);
                UserExchangeVB exchangeVB = selectedItem.getValue();
                priceService.transUnExchange(exchangeVB.getId(), Executions.getCurrent().getRemoteAddr());
                showUserExchange(null);
                Events.postEvent("onAllRefresh",exchangeListWin.getRoot() , null);
            }
        });

        exchangeListWin.getRoot().addEventListener("onAllRefresh", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                showUserExchange(null);
            }
        });
    }

    public void onCreate$exchangeListWin() {
        showUserExchange(null);
    }

    private void showUserExchange(String userCode) {
        UserExchangeVBList = userService.queryUserExchangeVBs(userCode);

        exchangeListListBox.setModel(new ListModelList<>(UserExchangeVBList));
        exchangeListListBox.getFellow("operateHeader").setVisible(WebConext.isLoginLeader());
        exchangeListListBox.renderAll();
    }

    private void initSelectedItem(Event event) {
        Map<String, Object> args = new HashMap();
        choseSelectedItem(event);
        UserExchangeVB exchangeVB = selectedItem.getValue();
        exchangeListListBox.setSelectedItem(selectedItem);
        exchangeListListBox.renderAll();
    }

    public Listitem choseSelectedItem(Event event) {
        selectedItem = (Listitem) ((ForwardEvent) event).getOrigin().getTarget().getParent().getParent().getParent();
        exchangeListListBox.setSelectedItem(selectedItem);
        return selectedItem;
    }
}
