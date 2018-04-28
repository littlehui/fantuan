package com.littlehui.fantuan.services.vbean;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Column;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.MultiTable;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Table;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.UnColumn;
import lombok.Data;

/**
 * Created by littlehui on 2016/10/30 0030.
 */
@Data
@MultiTable("t_user, t_user_leader")
public class UserLeaderVB {
    @Table("t_user")
    @Column("id")
    private Integer id;
    @MultiTable(value = "t_user, t_user_leader", sign = "t_user")
    @Column("user_code")
    private String userCode;
    @Table("t_user")
    @Column("user_name")
    private String userName;
    @Table("t_user")
    @Column("god_flag")
    private Boolean godFlag;
    @Table("t_user_leader")
    @Column("leader_key")
    private String leaderKey;
    @Table("t_user_leader")
    @Column("leader_start_time")
    private Long leaderStartTime;
    @Table("t_user_leader")
    @Column("leader_end_time")
    private Long leaderEndTime;
    @Table("t_user_leader")
    @Column("update_time")
    private Long updateTime;
    @Table("t_user_leader")
    @Column("create_time")
    private Long createTime;
    @UnColumn
    private Boolean leaderFlag;
    @Table("t_user")
    @Column("email")
    private String email;
    @Table("t_user")
    @Column("mobile")
    private String mobile;

    public Boolean getLeaderFlag() {
        Long currentTime = System.currentTimeMillis();
        return (currentTime > leaderStartTime) && (currentTime < leaderEndTime);
    }

    public String getLeaderFlagName() {
        return getLeaderFlag() ? "是" : "否";
    }

    public String getGodFlagName() {
        return godFlag ? "是" : "否";
    }

}
