package com.system.supercommon.funcbean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Description: token加密对象  目前只需要一个id  有业务需要需要加字段
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:54
 */

@Getter
@Setter
@Accessors(chain = true)
public class UserToken {

    private Long userId;
}
