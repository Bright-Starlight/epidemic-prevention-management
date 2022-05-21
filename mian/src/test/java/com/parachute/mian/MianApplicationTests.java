package com.parachute.mian;

import com.parachute.mian.entity.Hospital;
import com.parachute.mian.service.HospitalService;
import com.parachute.mian.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


class MianApplicationTests {



    @Test
    void contextLoads() {
        System.out.println(DateUtils.date2String(new Date()));
    }

}
