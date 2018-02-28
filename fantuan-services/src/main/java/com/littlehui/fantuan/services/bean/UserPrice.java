package com.littlehui.fantuan.services.bean;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Id;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Table("t_user_price")
@Data
public class UserPrice {
    @Id
    private Integer id;
    private String userCode;
    private BigDecimal remainPrice;
    private BigDecimal consumePrice;
    private BigDecimal exchangePrice;
    private Long createTime;
    private Long updateTime;
}
