package com.littlehui.fantuan.controller.user;

import com.littlehui.fantuan.controller.BindListenerForwardComposer;
import com.littlehui.fantuan.services.bean.User;
import com.littlehui.fantuan.web.WebConext;
import com.littlehui.zk.addition.plus.utils.MessageBox;
import com.littlehui.zk.addition.plus.utils.ZkUtils;
import com.littlehui.zk.addition.plus.utils.ZkWindUtil;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author littlehui
 * @date 2018/4/27
 */
public class HeaderController extends BindListenerForwardComposer {


    @Getter
    @Setter
    Window headerWin;

    @Getter
    @Setter
    Label loginUserName;

    @Getter
    @Setter
    Toolbarbutton userManager;

    private static final String PASSWORD_EDITOR_URL = "/zul/login/passwordEditor.zul";

    private static final String MAIN_URL = "/indexmvc.zul";

    private static final String USER_URL = "/zul/mvc/user/userList.zul";

    public void onClick$logOutToolbarButton() {
        WebConext.removeLogiinUser();
        Executions.sendRedirect("/zul/login.zul");
    }

    public void onCreate$headerWin() {
        loginUserName.setValue(WebConext.getLoginName());
        if (WebConext.loginAsGod()) {
            userManager.setVisible(true);
        }
    }

    public void onClick$changePassword() throws InterruptedException {
        if (!WebConext.isLoginUser()) {
            MessageBox.alert("未登陆不能修改密码");
            return ;
        }
        Map<String, Object> args = new HashMap();
        Window exchangeWin = (Window) ZkUtils.createComponents(PASSWORD_EDITOR_URL, headerWin, args);
        ZkWindUtil.doModal(exchangeWin, ZkWindUtil.WIN_MIN_SIZE);
    }

    public void onClick$mainIndex() {
        ZkUtils.sendRedirect(MAIN_URL);
    }

    public void onClick$userManager() {
        ZkUtils.sendRedirect(USER_URL);
    }
}
