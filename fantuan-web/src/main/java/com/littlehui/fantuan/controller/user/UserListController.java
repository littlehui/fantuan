package com.littlehui.fantuan.controller.user;

import com.littlehui.fantuan.controller.BindListenerForwardComposer;
import com.littlehui.fantuan.controller.compbean.UserListBean;
import com.littlehui.fantuan.services.service.UserService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserLeaderVB;
import com.littlehui.fantuan.web.WebConext;
import com.littlehui.zk.addition.plus.utils.MessageBox;
import com.littlehui.zk.addition.plus.utils.ZkMsgBox;
import com.littlehui.zk.addition.plus.utils.ZkUtils;
import com.littlehui.zk.addition.plus.utils.ZkWindUtil;
import com.littlehui.zk.addition.plus.zul.MessageboxPlus;
import lombok.extern.log4j.Log4j;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.ConventionWires;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author littlehui
 * @date 2018/4/26
 */
@Log4j
public class UserListController extends BindListenerForwardComposer {

    private final static String USER_LIST_ZUL = "/zul/mvc/user/userList.zul";

    private final static String USER_EDITOR_ZUL = "/zul/mvc/user/userEditor.zul";

    private UserListBean userListBean = new UserListBean();

    Listitem selectedItem;

    UserService userService = ApplicationContextUtil.getBean(UserService.class);

    UserLeaderVB userLeaderVB;

    ListModelList<UserLeaderVB> userLeaderVBListModelList;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
    }

    @Override
    protected void bindListener(Component component) {
        ConventionWires.wireVariables(component, userListBean);
        // do nothing
        /**
         * 删除
         */
        component.addEventListener("onDelete", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                initSelectedItem(event);
                MessageboxPlus.show("是否确定删除：" + userLeaderVB.getUserName()
                        , "确认窗口", MessageboxPlus.OK, "", "删除后无法恢复", null, new EventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                if (event.getName().equals(MessageboxPlus.ON_OK)) {
                                    userService.transDeleteUser(userLeaderVB.getUserCode(), WebConext.getRemoteIp());
                                    showUsers();
                                }
                            }
                        });
            }
        });

        /**
         * 修改
         */
        component.addEventListener("onOpenEditor", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                Map<String, Object> args = new HashMap();
                initSelectedItem(event);
                args.put("userLeaderVB", selectedItem.getValue());
                log.debug("打开编辑用户窗口");
                Window editWin = (Window) ZkUtils.createComponents(USER_EDITOR_ZUL, userListBean.getUserListWin(), args);
                ZkWindUtil.doModal(editWin, ZkWindUtil.WIN_MID_SIZE);
            }
        });

        /**
         * 刷新用户列表
         */
        component.addEventListener("onNotifyOk", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                //刷新用户页面
                //showUserPrice();
                showUsers();

            }
        });
    }

    public void showUsers() {
        List<UserLeaderVB> userLeaderVBList = userService.queryAllUserLeaderVB();
        userLeaderVBListModelList = new ListModelList(userLeaderVBList);
        showUsers(userLeaderVBListModelList);
    }

    private void showUsers(ListModelList<UserLeaderVB> vbListModelList) {
        //vbListModelList.setMultiple(true);
        userListBean.getUserListBox().setModel(vbListModelList);
        userListBean.getUserListBox().getFellow("operateHeader").setVisible(true);
    }

    public void onCreate$userListWin() {
        if (!WebConext.loginAsGod()) {
            return;
        }
        showUsers();
    }

    public void onClick$searchButton() {
        List<UserLeaderVB> leaderVBS = userService.queryByUserName(userListBean.getSearchBox().getValue());
        ListModelList<UserLeaderVB> modelList = new ListModelList<>(leaderVBS);
        showUsers(modelList);
    }

    public void onClick$addButton() {
        Map<String, Object> args = new HashMap();
        log.debug("打开编辑用户窗口");
        Window editWin = (Window) ZkUtils.createComponents(USER_EDITOR_ZUL, userListBean.getUserListWin(), args);
        ZkWindUtil.doModal(editWin, ZkWindUtil.WIN_MID_SIZE);
    }

    private void initSelectedItem(Event event) {
        Map<String, Object> args = new HashMap();
        choseSelectedItem(event);
        userListBean.getUserListBox().setSelectedItem(selectedItem);
        userListBean.getUserListBox().renderAll();
    }

    public Listitem choseSelectedItem(Event event) {
        selectedItem = (Listitem) ((ForwardEvent) event).getOrigin().getTarget().getParent().getParent().getParent();
        userLeaderVB = selectedItem.getValue();
        return selectedItem;
    }
}
