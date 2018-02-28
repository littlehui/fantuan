package com.littlehui.fantuan.services.bean;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Id;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Table("t_consume")
@Data
public class Consume {
    @Id
    private Integer id;
    private String userCode;
    private String consumeDetail;
    private Long createTime;
    private Long updateTime;
    private BigDecimal consumePrice;
}
