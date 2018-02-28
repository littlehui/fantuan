package com.littlehui.fantuan.services.vbean;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by littlehui on 2016/10/23 0023.
 */
@MultiTable("t_user,t_exchange")
@Data
public class UserExchangeVB {
    @Table("t_exchange")
    @Column("id")
    private Integer id;
    @Table("t_user")
    @Column("user_name")
    private String srcUserName;
    @MultiColumn(value = {"t_user.user_code","t_exchange.source_user_code"}, sign="t_exchange.source_user_code")
    private String sourceUserCode;
    @Table("t_exchange")
    @Column("des_user_code")
    private String desUserCode;
    @Table("t_exchange")
    @Column("exchange_price")
    private BigDecimal exchangePrice;
    @Table("t_exchange")
    @Column("update_time")
    private Long updateTime;
    @Table("t_exchange")
    @Column("exchange_detail")
    private String exchangeDetail;
    @Table("t_exchange")
    @Column("create_time")
    private Long createTime;
    @UnColumn
    private String desUserName;
}
