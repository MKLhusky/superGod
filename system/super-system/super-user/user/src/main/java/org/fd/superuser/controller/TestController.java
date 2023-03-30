package org.fd.superuser.controller;

import jakarta.annotation.Resource;
import org.fd.jdbc.SqlUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Description: aaa
 *
 * @author Mr. Dai
 * @date 2023/3/29 11:21
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private SqlUtils sqlUtils;

}
