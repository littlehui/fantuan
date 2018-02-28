package com.littlehui.fantuan.services.manager;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.dao.BaseDAO;
import com.littlehui.fantuan.services.bean.Exchange;
import com.littlehui.fantuan.services.dao.ExchangeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Component
public class ExchangeManager extends BaseManager<Exchange> {

    @Autowired
    ExchangeDAO exchangeDAO;

    public ExchangeManager() {
        baseDAO = exchangeDAO;
    }

}
