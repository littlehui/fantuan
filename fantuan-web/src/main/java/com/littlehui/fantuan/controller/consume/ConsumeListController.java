package com.littlehui.fantuan.controller.consume;

import com.littlehui.fantuan.controller.BindListenerForwardComposer;
import com.littlehui.fantuan.services.service.PriceService;
import com.littlehui.fantuan.services.service.UserService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserConsumeVB;
import com.littlehui.fantuan.services.vbean.UserPriceVB;
import com.littlehui.fantuan.web.WebConext;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.ConventionWires;
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
public class ConsumeListController extends BindListenerForwardComposer {

    @Getter
    @Setter
    Listbox consumeListListBox;

    @Getter
    @Setter
    Window consumeListWin;

    Listitem selectedItem;

    List<UserConsumeVB> userConsumeVBList;

    UserService userService = ApplicationContextUtil.getBean(UserService.class);

    PriceService priceService = ApplicationContextUtil.getBean(PriceService.class);

    protected void bindListener(Component component) {
        // do nothing
        component.addEventListener("onCancelConsume", new EventListener<Event>() {
            Map<String, Object> args = new HashMap();

            @Override
            public void onEvent(Event event) throws Exception {
                choseSelectedItem(event);
                UserConsumeVB consumeVB = selectedItem.getValue();
                priceService.transUnConsume(consumeVB.getId(), Executions.getCurrent().getRemoteAddr());
                showUserConsume(null);
                //通知userPRiceList刷新
                Events.postEvent("onAllRefresh",consumeListWin.getRoot() , null);
            }
        });

        consumeListWin.getRoot().addEventListener("onAllRefresh", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                showUserConsume(null);
            }
        });

        component.addEventListener("onNotifyOk", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {

                showUserConsume(null);
            }
        });
    }

    public void onCreate$consumeListWin() {
        showUserConsume(null);
    }

    private void showUserConsume(String userCode) {
        userConsumeVBList = userService.queryUserConsumeVBs(userCode);
        consumeListListBox.setModel(new ListModelList<>(userConsumeVBList));
        consumeListListBox.getFellow("operateHeader").setVisible(WebConext.isLoginLeader());
    }

    private void initSelectedItem(Event event) {
        Map<String, Object> args = new HashMap();
        choseSelectedItem(event);
        UserConsumeVB consumeVB = selectedItem.getValue();
        consumeListListBox.setSelectedItem(selectedItem);
        consumeListListBox.renderAll();
    }

    public Listitem choseSelectedItem(Event event) {
        selectedItem = (Listitem) ((ForwardEvent) event).getOrigin().getTarget().getParent().getParent().getParent();
        consumeListListBox.setSelectedItem(selectedItem);
        return selectedItem;
    }
}
