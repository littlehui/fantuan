package com.littlehui.fantuan.services.dao;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.dao.BaseDAO;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.dao.MultiQueryBaseDAO;
import com.littlehui.fantuan.services.vbean.UserPriceVB;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Repository
public interface UserPriceVBDAO extends MultiQueryBaseDAO<UserPriceVB> {
}
