package com.littlehui.fantuan.services.vbean;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Column;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.MultiTable;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Table;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by littlehui on 2016/10/23 0023.
 */
@MultiTable("t_user,t_recharge")
@Data
public class UserRechargeVB {
    @Table("t_recharge")
    @Column("id")
    private Integer id;
    @MultiTable(value="t_user, t_recharge", sign="t_recharge")
    @Column("user_code")
    private String userCode;
    @Table("t_user")
    @Column("user_name")
    private String userName;
    @Table("t_recharge")
    @Column("recharge_price")
    private BigDecimal rechargePrice;
    @Table("t_recharge")
    @Column("recharge_detail")
    private String rechargeDetail;
    @Table("t_recharge")
    @Column("update_time")
    private Long updateTime;
    @Table("t_recharge")
    @Column("create_time")
    private Long createTime;
}
