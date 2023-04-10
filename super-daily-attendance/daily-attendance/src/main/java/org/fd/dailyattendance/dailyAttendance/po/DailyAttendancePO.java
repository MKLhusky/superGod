package org.fd.dailyattendance.dailyAttendance.po;


import com.system.supercommon.bean.BaseEntityPO;
import com.system.supercommon.comenum.WhetherEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.fd.dailyattendance.dailyAttendance.enums.AttendanceTypeEnum;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class DailyAttendancePO extends BaseEntityPO {

    private Long id;

    private Long custId;

    private String custName;

    private AttendanceTypeEnum attendanceType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private WhetherEnum supplementFlag;

}
