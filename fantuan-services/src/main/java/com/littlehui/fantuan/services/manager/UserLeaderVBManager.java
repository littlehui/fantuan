package com.littlehui.fantuan.services.manager;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.bean.MultiQuery;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.repository.BaseMultiRepository;
import com.littlehui.fantuan.services.vbean.UserLeaderVB;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author littlehui
 * @date 2018/4/26
 */
@Repository
public class UserLeaderVBManager extends BaseMultiRepository<UserLeaderVB> {

    public List<UserLeaderVB> queryAllUserLeaderVBS() {
        MultiQuery multiQuery = MultiQuery.build(UserLeaderVB.class);
        multiQuery.addFieldEq("userCode", "userCode");
        multiQuery.addOrder("createTime", MultiQuery.DBOrder.DESC);
        return this.findByQuery(multiQuery);
    }

    public UserLeaderVB getByUserCode(String userCode) {
        MultiQuery multiQuery = MultiQuery.build(UserLeaderVB.class);
        multiQuery.addFieldEq("userCode", "userCode");
        multiQuery.addEq("userCode", userCode);
        multiQuery.addOrder("createTime", MultiQuery.DBOrder.DESC);
        return this.get(multiQuery);
    }

    public List<UserLeaderVB> queryByUserName(String userName) {
        MultiQuery multiQuery = MultiQuery.build(UserLeaderVB.class);
        multiQuery.addFieldEq("userCode", "userCode");
        multiQuery.addLike("userName", userName);
        multiQuery.addOrder("createTime", MultiQuery.DBOrder.DESC);
        return this.findByQuery(multiQuery);
    }
}
