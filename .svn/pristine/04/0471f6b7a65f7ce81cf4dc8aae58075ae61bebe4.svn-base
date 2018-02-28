package com.littlehui.fantuan.services.manager;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.bean.MultiQuery;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.repository.BaseMultiRepository;
import com.littlehui.fantuan.services.vbean.UserRechargeVB;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by littlehui on 2016/10/23 0023.
 */
@Repository
public class UserRechargeVBManager extends BaseMultiRepository<UserRechargeVB> {

    public List<UserRechargeVB> query(String userCode) {
        MultiQuery multiQuery = MultiQuery.build(UserRechargeVB.class);
        multiQuery.addFieldEq("userCode", "userCode");
        multiQuery.addEq("userCode", userCode);
        multiQuery.addOrder("createTime", MultiQuery.DBOrder.DESC);
        return this.findByQuery(multiQuery);
    }

    public List<UserRechargeVB> queryAll() {
        MultiQuery multiQuery = MultiQuery.build(UserRechargeVB.class);
        multiQuery.addFieldEq("userCode", "userCode");
        return this.findByQuery(multiQuery);
    }
}
