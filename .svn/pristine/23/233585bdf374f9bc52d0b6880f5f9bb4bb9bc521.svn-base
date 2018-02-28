package com.littlehui.fantuan.services.manager;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.bean.Query;
import com.littlehui.fantuan.services.bean.UserPrice;
import com.littlehui.fantuan.services.dao.UserPriceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Component
public class UserPriceManager extends BaseManager<UserPrice> {

    @Autowired
    UserPriceDAO userPriceDAO;

    public void initSqlRepository() {
        super.initSqlRepository();
        //this.addSqlByName("countTotalRemainPrice", "select sum(remain_price) from t_user_price");
    }

    public UserPriceManager() {
        baseDAO = userPriceDAO;
    }

    public UserPrice getUserPrice(String userCode) {
        Query query = Query.build(UserPrice.class);
        query.addEq("userCode", userCode);
        return this.get(query);
    }

    public void saveUserPrice(UserPrice userPrice) {
        this.save(userPrice);
    }

    public BigDecimal countTotalPrice() {
        BigDecimal result = userPriceDAO.countTotalRemainPrice();
        return result;
    }
}
