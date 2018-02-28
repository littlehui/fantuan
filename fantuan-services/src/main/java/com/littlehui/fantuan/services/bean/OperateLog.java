package com.littlehui.fantuan.services.bean;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Id;
import com.cyou.fz.commons.mybatis.selecterplus.mybatis.annotation.Table;
import lombok.Data;

/**
 * Created by littlehui on 2016/10/23 0023.
 */
@Data
@Table("t_operate_log")
public class OperateLog {
    @Id
    private Integer id;
    private String operateIp;
    private String operateDetail;
    private String operateType;
    private Long updateTime;
    private Long createTime;
}
