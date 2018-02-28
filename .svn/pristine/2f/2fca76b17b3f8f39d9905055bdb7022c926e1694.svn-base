package com.littlehui.fantuan.services.service;

import com.littlehui.fantuan.common.exception.BusinessException;
import com.littlehui.fantuan.common.util.EncryptUtil;
import com.littlehui.fantuan.common.util.ListUtils;
import com.littlehui.fantuan.services.bean.User;
import com.littlehui.fantuan.services.bean.UserLeader;
import com.littlehui.fantuan.services.manager.UserLeaderManager;
import com.littlehui.fantuan.services.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by littlehui on 2016/10/30 0030.
 */
@Service
public class LoginService {

    @Autowired
    UserManager userManager;

    @Autowired
    UserLeaderManager userLeaderManager;

    public boolean checkUserCanLogin(String userCode, String password) {
        User user = userManager.queryUserByCodeAndPassword(userCode, EncryptUtil.md5(password.getBytes()));
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUserCanRemember(String userCode, String password) {
        User user = userManager.queryUserByCodeAndPassword(userCode, password);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public void transChangeUserPassword(String userCode, String oldPassword, String newPassword) throws BusinessException {
        User user = userManager.queryUserByCodeAndPassword(userCode, EncryptUtil.md5(oldPassword.getBytes()));
        if (user != null) {
            user.setPassword(EncryptUtil.md5(newPassword.getBytes()));
        } else {
            throw new BusinessException("旧密码不正确。");
        }
        userManager.save(user);
    }

    public boolean isUserLeader(String userCode) {
        List<UserLeader> userLeaders = userLeaderManager.getUserLeader(System.currentTimeMillis());
        if (userLeaders != null) {
            List<String> leaderCodes = ListUtils.getListItemsSingleColumnList(userLeaders, "userCode", String.class);
            if (leaderCodes.contains(userCode)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public User doLogin(String userCode, String password) {
        Boolean canLogin = this.checkUserCanLogin(userCode, password);
        if (canLogin) {
            User loginUser = userManager.getByUserCode(userCode);
            return loginUser;
        } else {
            return null;
        }
    }

    public User doRememberLogin(String userCode, String password) {
        Boolean canLogin = this.checkUserCanRemember(userCode, password);
        if (canLogin) {
            User loginUser = userManager.getByUserCode(userCode);
            return loginUser;
        } else {
            return null;
        }
    }
}
