package com.littlehui.fantuan.services.manager;

import com.littlehui.fantuan.services.bean.OperateLog;
import com.littlehui.fantuan.services.dao.OperateLogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by littlehui on 2016/10/23 0023.
 */
@Repository
public class OperateLogManager extends BaseManager<OperateLog> {

    @Autowired
    OperateLogDAO operateLogDAO;

    public OperateLogManager() {
        this.baseDAO = operateLogDAO;
    }
}
