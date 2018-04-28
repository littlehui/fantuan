package com.littlehui.fantuan.controller.user;

import com.littlehui.fantuan.controller.BindListenerForwardComposer;
import com.littlehui.fantuan.services.service.UserService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserLeaderVB;
import com.littlehui.zk.addition.plus.utils.ZkWindUtil;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.Date;

/**
 * @author littlehui
 * @date 2018/4/26
 */
public class UserEditorController extends BindListenerForwardComposer {

    private UserLeaderVB userLeaderVB;

    @Getter
    @Setter
    Window userEditorWin;

    @Getter
    @Setter
    Textbox userName;

    @Getter
    @Setter
    Textbox userCode;

    @Getter
    @Setter
    Datebox leaderStartTime;
    @Getter
    @Setter
    Datebox leaderEndTime;

    @Getter
    @Setter
    Textbox mobile;

    @Getter
    @Setter
    Textbox email;

    UserService userService = ApplicationContextUtil.getBean(UserService.class);

    public void onCreate$userEditorWin() {
        userLeaderVB = (UserLeaderVB) arg.get("userLeaderVB");
        if (userLeaderVB != null) {
            userCode.setReadonly(true);
        }
        initView();
    }

    private void initView() {
        if (userLeaderVB != null) {
            userName.setValue(userLeaderVB.getUserName());
            userCode.setValue(userLeaderVB.getUserCode());
            leaderStartTime.setValue(new Date(userLeaderVB.getLeaderStartTime()));
            leaderEndTime.setValue(new Date(userLeaderVB.getLeaderEndTime()));
            mobile.setValue(userLeaderVB.getMobile());
            email.setValue(userLeaderVB.getEmail());
        }
    }

    public void onClick$saveCommit() {
        refreshUserLeaderVB();
        if (userLeaderVB.getId() == null) {
            userService.addUser(userLeaderVB);
        } else {
            userService.updateUser(userLeaderVB);
        }
        Events.postEvent("onNotifyOk", userEditorWin.getParent(), null);
        ZkWindUtil.doClose(userEditorWin);
    }

    public void refreshUserLeaderVB() {
        if (userLeaderVB == null) {
            userLeaderVB = new UserLeaderVB();
            userLeaderVB.setUserCode(userCode.getValue());
        }
        userLeaderVB.setUserName(userName.getValue());
        userLeaderVB.setLeaderEndTime(leaderEndTime.getValue() == null ? 0 : leaderEndTime.getValue().getTime());
        userLeaderVB.setLeaderStartTime(leaderStartTime.getValue() == null ? 0 :leaderStartTime.getValue().getTime());
        userLeaderVB.setEmail(email.getValue());
        userLeaderVB.setMobile(mobile.getValue());
    }
}
