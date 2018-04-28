package com.littlehui.fantuan.services.manager;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.bean.MultiQuery;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.repository.BaseMultiRepository;
import com.littlehui.fantuan.services.vbean.UserPriceVB;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Component
public class UserPriceVBManager extends BaseMultiRepository<UserPriceVB> {

    public List<UserPriceVB> query(String userCode) {
        if (userCode == null) {
            return new ArrayList<UserPriceVB>();
        }
        MultiQuery multiQuery = MultiQuery.build(UserPriceVB.class);
        multiQuery.addFieldEq("userCode", "userCode");
        multiQuery.addEq("userCode", userCode);
        return this.findByQuery(multiQuery);
    }

    public List<UserPriceVB> queryAll() {
        MultiQuery multiQuery = MultiQuery.build(UserPriceVB.class);
        multiQuery.addFieldEq("userCode", "userCode");
        return this.findByQuery(multiQuery);
    }

    public List<UserPriceVB> queryByUserName(String userName) {
        MultiQuery multiQuery = MultiQuery.build(UserPriceVB.class);
        multiQuery.addFieldEq("userCode", "userCode");
        multiQuery.addLike("userName", userName);
        return this.findByQuery(multiQuery);
    }

    public List<UserPriceVB> queryVBListExcGod() {
        MultiQuery multiQuery = MultiQuery.build(UserPriceVB.class);
        multiQuery.addFieldEq("userCode", "userCode");
        multiQuery.addEq("godFlag", false);
        return this.findByQuery(multiQuery);
    }
}
