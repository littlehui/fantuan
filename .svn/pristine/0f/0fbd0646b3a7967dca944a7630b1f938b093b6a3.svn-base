package com.littlehui.fantuan.common.util.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangj on 2015/11/6.
 */
public class SimpleSqlBuilder {
    /**
     * 包含除条件以外的条件,可以是全连接左连接
     */
    private StringBuilder query = new StringBuilder();

    private boolean hasWhere = false;

    boolean firstAnd = true;

    private List<String> conditions = new ArrayList<String>();

    private List<Object> params = new ArrayList<Object>();

    private List<String> tails = new ArrayList<String>();

    private SimpleSqlBuilder() {
    }

    public static SimpleSqlBuilder build(String... baseSqls) {
        SimpleSqlBuilder builder = new SimpleSqlBuilder();
        for (String sql : baseSqls) {
            if (sql.toLowerCase().contains("where")) {
                builder.hasWhere = true;
                builder.firstAnd = false;
            }

            builder.query.append(sql).append(" ");
        }
        return builder;
    }

    /**
     * 跳过为空对象条件
     * @param condition
     * @param param
     * @return
     */
    public SimpleSqlBuilder addParam(String condition , Object param) {
        if (param == null) {
            return this;
        }
        this.conditions.add(condition);
        this.params.add(param);
        return this;
    }



    public SimpleSqlBuilder addLikeParam(String condition , Object param) {
        if (param == null) {
            return this;
        }
        this.conditions.add(condition);
        this.params.add("%"+param +"%");

        return this;
    }

    public SimpleSqlBuilder addBetween(String condition , Object param1 , Object param2) {
        if (param1 == null || param2 == null) {
            return this;
        }
        this.conditions.add(condition);
        this.params.add(param1);
        this.params.add(param2);
        return this;
    }

    /**
     * 多值字段查询,如果存在一个为空则跳过此段查询
     * @param conditions
     * @param params
     * @return
     */
    public SimpleSqlBuilder addCondition(String[] conditions ,Object... params) {
        for (Object obj : params) {
            if (obj == null) {
                return this;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String condition : conditions) {
            sb.append(condition).append(" ");
        }

        this.conditions.add(sb.toString());
        for (Object obj : params) {
            this.params.add("%"+obj +"%");
        }
        return this;
    }

    /**
     * 多值字段查询,如果存在一个为空则跳过此段查询
     * @param conditions
     * @param params
     * @return
     */
    public SimpleSqlBuilder addLikeCondition(String[] conditions ,Object... params) {
        for (Object obj : params) {
            if (obj == null) {
                return this;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String condition : conditions) {
            sb.append(condition).append(" ");
        }

        this.conditions.add(sb.toString());
        for (Object obj : params) {
            this.params.add("%"+obj +"%");
        }
        return this;
    }

    public SimpleSqlBuilder appendTail(String conditions) {
        this.tails.add(conditions);
        return this;
    }

    /**
     * 无参数子句,用于in操作
     * @param condition
     * @return
     */
    public SimpleSqlBuilder addCondition(String condition){
        this.conditions.add(condition);
        return this;
    }

    public String toSql() {
        StringBuilder sql = new StringBuilder(query);

        if (!hasWhere && conditions.size() > 0) {
            sql.append(" where ");
        }
        for (String condition : conditions) {
            if (!firstAnd) {
                sql.append(" ").append("and");
            }else {
                firstAnd = false;
            }
            sql.append(" ").append(condition);
        }
        for (String tail : tails) {
            sql.append(" ").append(tail);
        }
        return sql.toString();
    }

    public List<Object> getParams() {
        return params;
    }

    public Object[] getParamObjects() {
        return params.toArray();
    }
}
