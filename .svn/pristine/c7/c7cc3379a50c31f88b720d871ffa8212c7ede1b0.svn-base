package com.littlehui.fantuan.services.bean;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Id;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Table("t_exchange")
@Data
public class Exchange {
    @Id
    private Integer id;
    private String sourceUserCode;
    private String desUserCode;
    private BigDecimal exchangePrice;
    private String exchangeDetail;
    private Long updateTime;
    private Long createTime;
}
