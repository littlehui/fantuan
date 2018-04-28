package com.littlehui.fantuan.services.vbean;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Column;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.MultiTable;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@MultiTable("t_user,t_user_price")
public class UserPriceVB {
    @MultiTable(value="t_user, t_user_price", sign="t_user_price")
    @Column("user_code")
    private String userCode;
    @Table("t_user")
    @Column("user_name")
    private String userName;
    @Table("t_user_price")
    @Column("remain_price")
    private BigDecimal remainPrice;
    @Table("t_user_price")
    @Column("consume_price")
    private BigDecimal consumePrice;
    @Table("t_user_price")
    @Column("exchange_price")
    private BigDecimal exchangePrice;
    @Table("t_user_price")
    @Column("create_time")
    private Long createTime;
    @Table("t_user_price")
    @Column("update_time")
    private Long updateTime;
    @Table("t_user")
    @Column("god_flag")
    private Boolean godFlag;

    public Boolean getGodFlag() {
        return godFlag;
    }

    public void setGodFlag(Boolean godFlag) {
        this.godFlag = godFlag;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getRemainPrice() {
        return remainPrice;
    }

    public void setRemainPrice(BigDecimal remainPrice) {
        this.remainPrice = remainPrice;
    }

    public BigDecimal getConsumePrice() {
        return consumePrice;
    }

    public void setConsumePrice(BigDecimal consumePrice) {
        this.consumePrice = consumePrice;
    }

    public BigDecimal getExchangePrice() {
        return exchangePrice;
    }

    public void setExchangePrice(BigDecimal exchangePrice) {
        this.exchangePrice = exchangePrice;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
