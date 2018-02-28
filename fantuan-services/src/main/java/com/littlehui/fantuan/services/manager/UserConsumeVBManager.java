package com.littlehui.fantuan.services.manager;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.bean.MultiQuery;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.repository.BaseMultiRepository;
import com.littlehui.fantuan.services.vbean.UserConsumeVB;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by littlehui on 2016/10/23 0023.
 */
@Repository
public class UserConsumeVBManager extends BaseMultiRepository<UserConsumeVB> {

    public List<UserConsumeVB> query(String userCode) {
        MultiQuery multiQuery = MultiQuery.build(UserConsumeVB.class);
        multiQuery.addFieldEq("userCode", "userCode");
        multiQuery.addEq("userCode", userCode);
        multiQuery.addOrder("createTime", MultiQuery.DBOrder.DESC);
        return this.findByQuery(multiQuery);
    }

    public List<UserConsumeVB> queryAll() {
        MultiQuery multiQuery = MultiQuery.build(UserConsumeVB.class);
        multiQuery.addFieldEq("userCode", "userCode");
        return this.findByQuery(multiQuery);
    }

}
