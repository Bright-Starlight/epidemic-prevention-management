package com.parachute.main;

import com.parachute.main.entity.Hospital;
import com.parachute.main.service.HospitalService;
import com.parachute.main.utils.AreaCodeList;
import com.parachute.main.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;


class MianApplicationTests {



    @Test
    void contextLoads() {

        System.out.println(new Date());

        System.out.println(DateUtils.date2String(new Date()));
        String s = DateUtils.date2String(new Date());
        String[] s1 = s.split(" ");
        System.out.println(s1[0]);

    }

}
