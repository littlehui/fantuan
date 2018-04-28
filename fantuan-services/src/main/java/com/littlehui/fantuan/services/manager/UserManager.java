package com.littlehui.fantuan.services.manager;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.bean.Query;
import com.littlehui.fantuan.services.bean.User;
import com.littlehui.fantuan.services.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Component
public class UserManager extends BaseManager<User> {

    @Autowired
    UserDAO userDAO;

    public UserManager() {
        baseDAO = userDAO;
    }

    public User getByUserCode(String userCode) {
        Query query = Query.build(User.class);
        query.addEq("userCode", userCode);
        return this.get(query);
    }

    public User queryUserByCodeAndPassword(String userCode, String password) {
        Query query = Query.build(User.class);
        query.addEq("userCode", userCode);
        query.addEq("password", password);
        return this.get(query);
    }
}
