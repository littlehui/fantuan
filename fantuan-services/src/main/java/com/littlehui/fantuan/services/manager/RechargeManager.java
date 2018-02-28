package com.littlehui.fantuan.services.manager;

import com.littlehui.fantuan.services.bean.Recharge;
import com.littlehui.fantuan.services.dao.RechargeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Component
public class RechargeManager extends BaseManager<Recharge> {

    @Autowired
    RechargeDAO rechargeDAO;

    public RechargeManager() {
        baseDAO = rechargeDAO;
    }
}
