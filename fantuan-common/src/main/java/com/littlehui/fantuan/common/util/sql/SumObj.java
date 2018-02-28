package com.littlehui.fantuan.common.util.sql;

import java.math.BigDecimal;

/**
 * 统计sum
 * Created by wangj on 2015/12/2.
 */
public class SumObj {
    private BigDecimal count;

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public int getSum() {
        return count.intValue();
    }
}
