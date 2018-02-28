package com.littlehui.fantuan.services.manager;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.dao.BaseDAO;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.manager.DefaultSqlRepository;
import com.littlehui.fantuan.common.util.ObjectBeanUtils;


/**
 * Created by Administrator on 2016/10/3 0003.
 */
public class BaseManager<T> extends DefaultSqlRepository<T> {

    public final static String DEFAULT_ID_COLUMN = "id";

    protected BaseDAO<T> baseDAO;

    protected BaseDAO<T> getDAO() {
        if (baseDAO != null) {
            return baseDAO;
        } else {
            return super.getDAO();
        }
    }

    public void save(T t) {
        if (ObjectBeanUtils.getPropertyValue(t, DEFAULT_ID_COLUMN) == null) {
            this.insert(t);
        } else {
            this.update(t);
        }
    }
}
