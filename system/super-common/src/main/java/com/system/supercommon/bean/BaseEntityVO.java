package com.system.supercommon.bean;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class BaseEntityVO {

    @Schema(name = "创建时间")
    private LocalDateTime createTime;
    @Schema(name = "更新时间")
    private LocalDateTime updateTime;
    @Schema(name = "创建人")
    private Long  createBy;
    @Schema(name = "创建人名称")
    private String createByName;
    @Schema(name = "更新人")
    private Long  updateBy;
    @Schema(name = "更新人名称")
    private String updateByName;
    @Schema(name = "备注")
    private String remark;
}
