package com.littlehui.fantuan.controller.login;

import com.littlehui.fantuan.common.exception.BusinessException;
import com.littlehui.fantuan.controller.BindListenerForwardComposer;
import com.littlehui.fantuan.services.service.LoginService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.web.WebConext;
import com.littlehui.zk.addition.plus.utils.MessageBox;
import com.littlehui.zk.addition.plus.utils.ZkWindUtil;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 * Created by littlehui on 2016/10/30 0030.
 */
public class PasswordEditorController extends BindListenerForwardComposer {

    @Getter
    @Setter
    Textbox oldPassword;

    @Getter
    @Setter
    Textbox newPassword;

    @Getter
    @Setter
    Textbox reNewPassword;

    @Getter
    @Setter
    Window changePasswordWin;

    LoginService loginService = ApplicationContextUtil.getBean(LoginService.class);

    public void onClick$confirmPassword() throws InterruptedException {
        if (!WebConext.isLoginUser()) {
            MessageBox.alert("未登录。不能修改密码。");
        }
        String userCode = WebConext.getLoginUserCode();
        String oldPassword = getOldPassword().getValue();
        loginService.checkUserCanLogin(userCode, oldPassword);
        String newPassword = getNewPassword().getValue();
        String rePassword = getReNewPassword().getValue();
        if (!newPassword.equals(rePassword)) {
            MessageBox.alert("重复的新密码有误。");
        }
        try {
            loginService.transChangeUserPassword(WebConext.getLoginUserCode(), oldPassword, newPassword);
        } catch (BusinessException e) {
            MessageBox.alert(e.getMessage());
            e.printStackTrace();
        }
        ZkWindUtil.doClose(changePasswordWin);
        Executions.sendRedirect("/zul/login.zul");
    }
}
