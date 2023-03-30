package org.fd.dailyattendance.controller;


import com.system.supercommon.funcbean.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/daily")
public class DailyAttendanceController {


    @RequestMapping("/attendance")
    public R attendance(){

        return R.success();
    }


}
