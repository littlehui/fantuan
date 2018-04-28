package com.littlehui.fantuan.services.service;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.bean.Query;
import com.littlehui.fantuan.common.util.EncryptUtil;
import com.littlehui.fantuan.common.util.ListUtils;
import com.littlehui.fantuan.services.bean.*;
import com.littlehui.fantuan.services.constants.FantuanConstants;
import com.littlehui.fantuan.services.constants.OperateEnum;
import com.littlehui.fantuan.services.manager.*;
import com.littlehui.fantuan.services.vbean.UserConsumeVB;
import com.littlehui.fantuan.services.vbean.UserExchangeVB;
import com.littlehui.fantuan.services.vbean.UserLeaderVB;
import com.littlehui.fantuan.services.vbean.UserRechargeVB;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    @Autowired
    UserLeaderVBManager userLeaderVBManager;

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
        UserLeader userLeader = userLeaderManager.get(query);
        if (user != null) {
            userManager.delete(user);
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateType(OperateEnum.DELETE_USER.name());
            operateLog.setOperateIp(operateIp);
            operateLog.setOperateDetail(user.toString());
            operateLogManager.save(operateLog);
        }
        if (userPrice != null) {
            userPriceManager.delete(userPrice);
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateType(OperateEnum.DELETE_PRICE.name());
            operateLog.setOperateIp(operateIp);
            operateLog.setOperateDetail(userPrice.toString());
            operateLogManager.save(operateLog);
        }
        if (userLeader != null) {
            userLeaderManager.delete(userLeader);
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateType(OperateEnum.DELETE_LEADER.name());
            operateLog.setOperateIp(operateIp);
            operateLog.setOperateDetail(userLeader.toString());
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

    @Transactional(rollbackFor = Exception.class)
    public void addUser(User user) {
        user.setPassword(EncryptUtil.md5(FantuanConstants.DEFALUT_PASSWORD.getBytes()));
        User userSaved = userManager.save(user);
        UserPrice userPrice = new UserPrice();
        userPrice.setUserCode(userSaved.getUserCode());
        userPrice.setConsumePrice(new BigDecimal(0));
        userPrice.setExchangePrice(new BigDecimal(0));
        userPrice.setRemainPrice(new BigDecimal(0));

        UserLeader userLeader = new UserLeader();
        userLeader.setUserCode(userSaved.getUserCode());
        userLeader.setLeaderEndTime(0L);
        userLeader.setLeaderStartTime(0L);
        userLeader.setLeaderKey(user.hashCode() + "");

        userPriceManager.save(userPrice);
        userLeaderManager.save(userLeader);
    }

    public void initUserPassWord(String userCode) {
        User user = userManager.getByUserCode(userCode);
        user.setPassword(EncryptUtil.md5(FantuanConstants.DEFALUT_PASSWORD.getBytes()));
        userManager.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changeUserLeader(String userCode, Long leaderStartTime, Long leaderEndTime) {
        User user = userManager.getByUserCode(userCode);
        userLeaderManager.deleteUserLeader(userCode);
        UserLeader userLeader = new UserLeader();
        userLeader.setUserCode(userCode);
        userLeader.setLeaderEndTime(leaderStartTime);
        userLeader.setLeaderStartTime(leaderEndTime);
        userLeader.setLeaderKey(user.hashCode() + "");
        userLeaderManager.save(userLeader);
    }

    public List<UserLeaderVB> queryAllUserLeaderVB() {
        return userLeaderVBManager.queryAllUserLeaderVBS();
    }

    public List<UserLeaderVB> queryByUserName(String userName) {
        return userLeaderVBManager.queryByUserName(userName);
    }

    public void addUser(UserLeaderVB userLeaderVB) {
        User user = new User();
        user.setUserCode(userLeaderVB.getUserCode());
        user.setUserName(userLeaderVB.getUserName());
        user.setPassword(EncryptUtil.md5(FantuanConstants.DEFALUT_PASSWORD.getBytes()));
        user.setEmail(userLeaderVB.getEmail());
        user.setMobile(userLeaderVB.getMobile());
        user.setGodFlag(false);
        User userSaved = userManager.save(user);

        UserPrice userPrice = new UserPrice();
        userPrice.setUserCode(userSaved.getUserCode());
        userPrice.setConsumePrice(new BigDecimal(0));
        userPrice.setExchangePrice(new BigDecimal(0));
        userPrice.setRemainPrice(new BigDecimal(0));

        UserLeader userLeader = new UserLeader();
        userLeader.setUserCode(userSaved.getUserCode());
        userLeader.setLeaderEndTime(userLeaderVB.getLeaderEndTime());
        userLeader.setLeaderStartTime(userLeaderVB.getLeaderStartTime());
        userLeader.setLeaderKey(user.hashCode() + "");

        userPriceManager.save(userPrice);
        userLeaderManager.save(userLeader);
    }

    public void updateUser(UserLeaderVB userLeaderVB) {
        User user = new User();
        user.setId(userLeaderVB.getId());
        user.setUserCode(userLeaderVB.getUserCode());
        user.setUserName(userLeaderVB.getUserName());
        user.setPassword(EncryptUtil.md5(FantuanConstants.DEFALUT_PASSWORD.getBytes()));
        user.setEmail(userLeaderVB.getEmail());
        user.setMobile(userLeaderVB.getMobile());
        user.setGodFlag(false);

        UserLeader userLeader = new UserLeader();
        userLeader.setUserCode(user.getUserCode());
        userLeader.setLeaderEndTime(userLeaderVB.getLeaderEndTime());
        userLeader.setLeaderStartTime(userLeaderVB.getLeaderStartTime());
        userLeader.setLeaderKey(user.hashCode() + "");

        userManager.save(user);
        userLeaderManager.deleteUserLeader(user.getUserCode());
        userLeaderManager.save(userLeader);
    }
}
