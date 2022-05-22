package com.parachute.main;

import com.parachute.main.entity.Hospital;
import com.parachute.main.service.HospitalService;
import com.parachute.main.utils.DateUtils;
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
