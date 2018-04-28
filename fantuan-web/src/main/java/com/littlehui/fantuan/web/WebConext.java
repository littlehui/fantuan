package com.littlehui.fantuan.web;

import com.littlehui.fantuan.services.bean.User;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;

/**
 * Created by littlehui on 2016/10/30 0030.
 */
public class WebConext {

    private static final String LOGIN_CODE = "loginCode";

    private static final String LOGIN_NAME = "loginName";

    private static final String IS_LEADER = "loginLeader";

    private static final String LOGIN_USER = "loginUser";

    public static Session getSession() {
        return Executions.getCurrent().getSession();
    }

    public static boolean isLoginLeader() {
        Object value = getSession().getAttribute(IS_LEADER);
        Boolean isLoginLeader = false;
        if (value != null) {
            isLoginLeader = (Boolean) value;
        }
        return isLoginLeader;
    }

    public static boolean isLoginUser() {
        Object value = getSession().getAttribute(LOGIN_CODE);
        if (value != null && !value.equals("visit")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isVisitor() {
        Object value = getSession().getAttribute(LOGIN_CODE);
        if (value != null && value.equals("visit")) {
            return true;
        } else {
            return false;
        }
    }

    public static String getLoginUserCode() {
        Object value = getSession().getAttribute(LOGIN_CODE);
        if (value != null) {
            return value.toString();
        } else {
            return null;
        }
    }

    public static String getLoginName() {
        Object value = getSession().getAttribute(LOGIN_NAME);
        if (value != null) {
            return value.toString();
        } else {
            return null;
        }
    }

    public static User getLoginUser() {
        Object value = getSession().getAttribute(LOGIN_USER);
        if (value != null) {
            return (User) value;
        } else {
            return null;
        }
    }

    public static void setLoginUser(User user, Boolean isLeader) {
        getSession().setAttribute(LOGIN_USER, user);
        getSession().setAttribute(LOGIN_CODE, user.getUserCode());
        getSession().setAttribute(LOGIN_NAME, user.getUserName());
        getSession().setAttribute(IS_LEADER, isLeader);
    }

    public static void removeLogiinUser() {
        getSession().removeAttribute(LOGIN_USER);
        getSession().removeAttribute(LOGIN_CODE);
        getSession().removeAttribute(LOGIN_NAME);
        getSession().removeAttribute(IS_LEADER);
    }

    public static Boolean loginAsGod() {
        User user = WebConext.getLoginUser();
        if (user == null) {
            return false;
        }
        return user.getGodFlag();
    }

    public static String getRemoteIp() {
        return Executions.getCurrent().getRemoteAddr();
    }
}
