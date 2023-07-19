package org.fd.dailyattendance;

import com.system.supercommon.comenum.WhetherEnum;
import com.system.supercommon.util.SnowFlake;
import org.fd.dailyattendance.dailyAttendance.enums.AttendanceTypeEnum;
import org.fd.dailyattendance.dailyAttendance.po.DailyAttendancePO;
import org.fd.dailyattendance.dailyAttendance.service.impl.DailyAttendanceServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@EnableTransactionManagement
@SpringBootTest
class DailyAttendanceApplicationTests {

    @Autowired
    private DailyAttendanceServiceImpl dailyAttendanceService;

    @Test
    @Transactional
    void contextLoads() {
        DailyAttendancePO dailyAttendance = new DailyAttendancePO();
        dailyAttendance.setId(SnowFlake.getId());
        dailyAttendance.setAttendanceType(AttendanceTypeEnum.STUDY);
        dailyAttendance.setCustId(1L);
        dailyAttendance.setCustName("fwp");
        dailyAttendance.setSupplementFlag(WhetherEnum.YES);
        dailyAttendance.setStartTime(LocalDateTime.now());
        dailyAttendance.setEndTime(LocalDateTime.now());
        dailyAttendanceService.attendance(dailyAttendance);
    }
}
