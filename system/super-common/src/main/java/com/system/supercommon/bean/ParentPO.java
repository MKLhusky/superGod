package com.system.supercommon.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Description: 持久化实体公共父类与数据库字段一一对应
 * @Author: Mr. Dai
 * @Date: 2023/3/27 21:54
 **/
@Getter
@Setter
@Accessors(chain = true)
public class ParentPO {

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long  createBy;

    private Long  updateBy;

    private Long version;

    private Integer delFlag;

    private String remark;
}
