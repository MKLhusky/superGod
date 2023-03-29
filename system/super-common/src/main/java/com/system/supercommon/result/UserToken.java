package com.system.supercommon.result;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: token加密对象  目前只需要一个id  有业务需要需要加字段
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:54
 */

@Getter
@Setter
public class UserToken {

    private Long userId;
}
