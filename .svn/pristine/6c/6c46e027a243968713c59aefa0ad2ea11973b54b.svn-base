package com.littlehui.fantuan.services.dao;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.dao.BaseDAO;
import com.littlehui.fantuan.services.bean.UserPrice;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Repository
public interface UserPriceDAO extends BaseDAO<UserPrice> {

    @Select("select sum(remain_price) from t_user_price")
    public BigDecimal countTotalRemainPrice();
}
