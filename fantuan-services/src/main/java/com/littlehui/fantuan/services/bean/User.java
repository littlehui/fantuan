package com.littlehui.fantuan.services.bean;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Id;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Table("t_user")
@Data
public class User {
    @Id
    private Integer id;
    private String userCode;
    private String userName;
    private String password;
    private Boolean godFlag;
    private String email;
    private String mobile;
    private Long createTime;
    private Long updateTime;
}
