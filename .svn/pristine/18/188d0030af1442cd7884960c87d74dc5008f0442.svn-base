package com.littlehui.fantuan.controller;

import com.littlehui.fantuan.controller.compbean.IndexBean;
import com.littlehui.fantuan.web.WebConext;
import com.littlehui.zk.addition.plus.utils.MessageBox;
import com.littlehui.zk.addition.plus.utils.ZkUtils;
import com.littlehui.zk.addition.plus.utils.ZkWindUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.ConventionWires;
import org.zkoss.zk.ui.util.GenericAutowireComposer;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
public class IndexController extends BindListenerForwardComposer {

    private IndexBean indexBean = new IndexBean();

    private static final String PASSWORD_EDITOR_URL = "/zul/login/passwordEditor.zul";

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        //ConventionWires.wireVariables(comp, indexBean);
        // bindEvent(comp);
    }

    protected void bindListener(Component component) {
        ConventionWires.wireVariables(component, indexBean);
        // do nothing
        indexBean.getIndexWin().addEventListener("onEditNotifyOK", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {

            }
        });
    }

    public void onCreate$indexWin() {
        indexBean.getLoginUserName().setValue(WebConext.getLoginName());
    }

    public void onClick$logOutToolbarButton() {
        WebConext.removeLogiinUser();
        Executions.sendRedirect("/zul/login.zul");
    }

    public void onClick$changePassword() throws InterruptedException {
        if (!WebConext.isLoginUser()) {
            MessageBox.alert("未登陆不能修改密码");
            return ;
        }
        Map<String, Object> args = new HashMap();
        Window exchangeWin = (Window) ZkUtils.createComponents(PASSWORD_EDITOR_URL, indexBean.getIndexWin(), args);
        ZkWindUtil.doModal(exchangeWin, ZkWindUtil.WIN_MIN_SIZE);
    }
}
