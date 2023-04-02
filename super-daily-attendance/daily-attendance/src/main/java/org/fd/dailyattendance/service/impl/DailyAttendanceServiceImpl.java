package org.fd.dailyattendance.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fd.dailyattendance.po.DailyAttendancePO;
import org.fd.dailyattendance.service.DailyAttendanceService;
import org.fd.jdbc.SqlUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyAttendanceServiceImpl implements DailyAttendanceService {

    private final SqlUtils sqlUtils;

    @Override
    public int attendance(DailyAttendancePO dailyAttendance) {
        int insert = sqlUtils.insert(dailyAttendance);
        return insert;
    }

}
