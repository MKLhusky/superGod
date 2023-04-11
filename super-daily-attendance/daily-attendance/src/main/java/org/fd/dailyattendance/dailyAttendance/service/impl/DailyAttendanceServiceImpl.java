package org.fd.dailyattendance.dailyAttendance.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fd.dailyattendance.dailyAttendance.po.DailyAttendancePO;
import org.fd.dailyattendance.dailyAttendance.service.DailyAttendanceService;
import org.fd.jdbc.SqlUtils;
import org.fd.redis.RedisUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyAttendanceServiceImpl implements DailyAttendanceService {

    private final SqlUtils sqlUtils;

    private final RedisUtil redisUtil;

    @Override
    public int attendance(DailyAttendancePO dailyAttendance) {
        int insert = sqlUtils.insert(dailyAttendance);

        Boolean aBoolean = redisUtil.setValueNx("name", "fanwenpeng");
        System.out.println(aBoolean);

        return insert;
    }

}
