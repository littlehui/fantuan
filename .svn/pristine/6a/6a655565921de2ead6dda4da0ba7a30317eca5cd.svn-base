package com.littlehui.fantuan.services.service;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.bean.Query;
import com.littlehui.fantuan.common.util.ListUtils;
import com.littlehui.fantuan.services.bean.OperateLog;
import com.littlehui.fantuan.services.bean.User;
import com.littlehui.fantuan.services.bean.UserLeader;
import com.littlehui.fantuan.services.bean.UserPrice;
import com.littlehui.fantuan.services.manager.*;
import com.littlehui.fantuan.services.vbean.UserConsumeVB;
import com.littlehui.fantuan.services.vbean.UserExchangeVB;
import com.littlehui.fantuan.services.vbean.UserRechargeVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Service
public class UserService {

    @Autowired
    UserManager userManager;

    @Autowired
    UserPriceManager userPriceManager;

    @Autowired
    OperateLogManager operateLogManager;

    @Autowired
    UserConsumeVBManager userConsumeVBManager;

    @Autowired
    UserExchangeVBManager userExchangeVBManager;

    @Autowired
    UserRechargeVBManager userRechargeVBManager;

    @Autowired
    UserLeaderManager userLeaderManager;

    public List<User> getAllUsersExceptOne(String userCode) {
        Query query = Query.build(User.class);
        List<User> users = userManager.findByQuery(query);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserCode().equals(userCode)) {
                users.remove(i);
                break;
            }
        }
        return users;
    }

    public void transDeleteUser(String userCode, String operateIp) {
        Query query = Query.build(User.class);
        query.addEq("userCode", userCode);
        User user = userManager.get(query);

        UserPrice userPrice = userPriceManager.getUserPrice(userCode);
        if (user != null) {
            userManager.delete(user);
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateType("DELETE_USER");
            operateLog.setOperateIp(operateIp);
            operateLog.setOperateDetail(user.toString());
            operateLogManager.save(operateLog);
        }
        if (userPrice != null) {
            userPriceManager.delete(userPrice);
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateType("DELETE_USER_PRICE");
            operateLog.setOperateIp(operateIp);
            operateLog.setOperateDetail(userPrice.toString());
            operateLogManager.save(operateLog);
        }
    }

    public List<UserConsumeVB> queryUserConsumeVBs(String userCode) {
        return userConsumeVBManager.query(userCode);
    }

    public List<UserExchangeVB> queryUserExchangeVBs(String userCode) {
        List<UserExchangeVB> userExchangeVBs = userExchangeVBManager.query(userCode);
        if (ListUtils.isNotEmpty(userExchangeVBs)) {
            for (UserExchangeVB vb : userExchangeVBs) {
                User desUser = userManager.getByUserCode(vb.getDesUserCode());
                vb.setDesUserName(desUser.getUserName());
            }
        }
        return userExchangeVBs;
    }

    public List<UserRechargeVB> queryUserRechargeVBs(String userCode) {
        return userRechargeVBManager.query(userCode);
    }

    public void transSaveUserLeader(String userCode, Long startMills, Long endMills) {
        UserLeader userLeader = new UserLeader();
        userLeader.setUserCode(userCode);
        userLeader.setLeaderEndTime(endMills);
        userLeader.setLeaderStartTime(startMills);

        userLeaderManager.deleteUserLeader(startMills, endMills);
        userLeaderManager.save(userLeader);
    }

    public User getUserByCode(String userCode) {
        return userManager.getByUserCode(userCode);
    }
}
