package com.littlehui.fantuan.services.manager;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.bean.MultiQuery;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.bean.Query;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.repository.BaseMultiRepository;
import com.littlehui.fantuan.services.vbean.UserExchangeVB;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by littlehui on 2016/10/23 0023.
 */
@Repository
public class UserExchangeVBManager extends BaseMultiRepository<UserExchangeVB> {

    public List<UserExchangeVB> query(String userCode) {
        MultiQuery multiQuery = MultiQuery.build(UserExchangeVB.class);
        multiQuery.addEq("sourceUserCode", userCode);
        multiQuery.addOrder("createTime", Query.DBOrder.DESC);
        return this.findByQuery(multiQuery);
    }

}
