package com.littlehui.fantuan.services.vbean;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Column;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.MultiTable;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Table;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by littlehui on 2016/10/23 0023.
 */
@MultiTable("t_user,t_consume")
@Data
public class UserConsumeVB {
    @Table("t_consume")
    @Column("id")
    private Integer id;
    @MultiTable(value="t_user, t_consume", sign="t_consume")
    @Column("user_code")
    private String userCode;
    @Table("t_user")
    @Column("user_name")
    private String userName;
    @Table("t_consume")
    @Column("consume_price")
    private BigDecimal consumePrice;
    @Table("t_consume")
    @Column("consume_detail")
    private String consumeDetail;
    @Table("t_consume")
    @Column("update_time")
    private Long updateTime;
    @Table("t_consume")
    @Column("create_time")
    private Long createTime;
}
