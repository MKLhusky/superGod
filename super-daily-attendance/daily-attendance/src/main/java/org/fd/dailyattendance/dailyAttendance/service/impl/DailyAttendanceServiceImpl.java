package org.fd.dailyattendance.dailyAttendance.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fd.dailyattendance.dailyAttendance.po.DailyAttendancePO;
import org.fd.dailyattendance.dailyAttendance.service.DailyAttendanceService;
import org.fd.jdbc.SqlUtils;
import org.fd.redis.RedisUtil;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyAttendanceServiceImpl implements DailyAttendanceService {

    private final SqlUtils superSqlUtil;

    private final RedisUtil redisUtil;

    @Override
    public int attendance(DailyAttendancePO dailyAttendance) {

        log.info("测试事务");

        int insert1 = superSqlUtil.insert(dailyAttendance);
        //test(dailyAttendance);

//        Boolean aBoolean = redisUtil.setValueNx("name", "fanwenpeng");
//        System.out.println(aBoolean);
        //int xx = 1/0;

        return insert1;
    }

    public int test(DailyAttendancePO dailyAttendance){
        int insert2 = superSqlUtil.insert(dailyAttendance);
        int insert3 = superSqlUtil.insert(dailyAttendance);
        int insert4 = superSqlUtil.insert(dailyAttendance);
        int insert5 = superSqlUtil.insert(dailyAttendance);
        int insert6 = superSqlUtil.insert(dailyAttendance);
        return 1;
    }

    public static boolean checkMedianCode(String medianCode) {

        //先进行正则匹配
        if (medianCode.length() < 16){
            return false;
        }
        //取出校验位
        String code = medianCode.substring(14, 16);
        //前14位转化为char数组
        char[] idCode = medianCode.substring(0, 14).toCharArray();
        //加权因子
        int[] weight_factor = {1, 3, 5, 7, 11, 2, 13, 1, 1, 17, 19, 97, 23, 29};
        int len = idCode.length;
        int num = 0;
        int temp = 0;
        //循环取和
        for (int i = 0; i < len; i++) {
            //字母转数字
            if (idCode[i] >= 'A' && idCode[i] <= 'Z') {
                temp = (int) idCode[i] - 55;
            } else {
                temp = (int) idCode[i] - 48;
            }
            //求和
            num = num + temp * weight_factor[i];
        }
        //取余+1
        int surplus = num % 97 + 1;
        if (Integer.parseInt(code) - surplus == 0) {
            return true;
        }
        return false;
    }

    public static <T> T newInter(T... t) throws IllegalAccessException, InstantiationException {
        Class<?> componentType = t.getClass().getComponentType();
        T o = (T)componentType.newInstance();
        return o;
    }


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
    }

}
