package org.fd.dailyattendance.dailyAttendance.controller;


import com.system.supercommon.funcbean.R;
import com.system.supercommon.handler.login.TransRequestContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fd.dailyattendance.dailyAttendance.po.DailyAttendancePO;
import org.fd.dailyattendance.dailyAttendance.service.DailyAttendanceService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/daily")
@RequiredArgsConstructor
public class DailyAttendanceController {

    private final DailyAttendanceService dailyAttendanceService;

    @RequestMapping("/attendance")
    public R attendance(@RequestBody DailyAttendancePO dailyAttendance){
        ThreadLocal<TransRequestContextHolder.UserInfo> userInfo = TransRequestContextHolder.getUserInfo();
        dailyAttendance.setCustId(userInfo.get().getUserId());
        int count = dailyAttendanceService.attendance(dailyAttendance);

        return R.success();
    }


}
