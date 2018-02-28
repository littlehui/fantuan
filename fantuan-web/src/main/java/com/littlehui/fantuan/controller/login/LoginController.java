package com.littlehui.fantuan.controller.login;

import com.alibaba.druid.util.StringUtils;
import com.littlehui.fantuan.controller.BindListenerForwardComposer;
import com.littlehui.fantuan.services.bean.User;
import com.littlehui.fantuan.services.service.LoginService;
import com.littlehui.fantuan.services.service.UserService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.web.WebConext;
import com.littlehui.zk.addition.plus.utils.MessageBox;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 * Created by littlehui on 2016/10/30 0030.
 */
public class LoginController extends BindListenerForwardComposer {

    private static final String INDEX_URL = "/indexmvc.zul";

    @Getter
    @Setter
    Textbox userCode;

    @Getter
    @Setter
    Textbox passWord;

    @Getter
    @Setter
    Button cancel;

    @Getter
    @Setter
    Button submit;

    @Getter
    @Setter
    Window loginWin;

    private Boolean rememberedUserFlag = false;

    LoginService loginService = ApplicationContextUtil.getBean(LoginService.class);

    UserService userService = ApplicationContextUtil.getBean(UserService.class);

    protected void bindListener(Component component) {
        // do nothing
    }

    public void onCreate$loginWin(Event event) {
        User loginUser = WebConext.getLoginUser();
        if (loginUser != null) {
            userCode.setValue(loginUser.getUserCode());
            passWord.setValue(loginUser.getPassword());
            rememberedUserFlag = true;
        }
        System.out.println(userCode.getValue());
    }

    public void onChange$userCode(Event event) {
        rememberedUserFlag = false;
    }

    public void onChange$passWord(Event event) {
        rememberedUserFlag = false;
    }

    public void onClick$submit(Event event) throws InterruptedException {
        String userCode = getUserCode().getValue();
        String password = getPassWord().getValue();
        if (StringUtils.isEmpty(userCode) || StringUtils.isEmpty(password)) {
            MessageBox.alert("账号或密码为空，请重新输入");
            return;
        }
        User loginUser = null;
        if (rememberedUserFlag) {
            loginUser = loginService.doRememberLogin(userCode, password);
        } else {
            loginUser = loginService.doLogin(userCode, password);
        }
        Boolean isUserLeader = loginService.isUserLeader(userCode);
        if (loginUser != null) {
            WebConext.setLoginUser(loginUser, isUserLeader);
            Executions.sendRedirect(INDEX_URL);
        } else {
            MessageBox.alert("账号或者密码有误");
        }
    }

    public void onClick$cancel(Event event) {
        userCode.setValue(null);
        passWord.setValue(null);
    }

    public void onClick$visitor(Event event) {
        User visitor = new User();
        visitor.setUserCode("visit");
        visitor.setUserName("游客");
        WebConext.setLoginUser(visitor, false);
/*        Window indexWindow = (Window)ZkWindUtil.createComponentsWithUrlParams(getLoginWin(), INDEX_URL, null);
        ZkWindUtil.doModal(indexWindow);*/
        Executions.sendRedirect(INDEX_URL);
    }
}
