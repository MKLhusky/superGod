package com.system.supercommon.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Description: 展示对象公共父类
 *
 * @author Mr. Dai
 * @date 2023/3/30 11:52
 */
@Getter
@Setter
@Accessors(chain = true)
public class ParentVO {

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long  createBy;

    private String createByName;

    private Long  updateBy;

    private String updateByName;

    private String remark;
}
