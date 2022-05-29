package com.parachute.main;

import com.parachute.main.constant.RoleConstants;
import com.parachute.main.entity.Carrier;
import com.parachute.main.entity.Hospital;
import com.parachute.main.service.CarrierService;
import com.parachute.main.service.HospitalService;
import com.parachute.main.utils.AreaCodeList;
import com.parachute.main.utils.NameRandom;
import com.parachute.main.utils.RandomValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
@SpringBootTest
public class CreateDataUtils {

    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private CarrierService carrierService;

    private static Random ran=new Random();
    private final static int delta=0x9fa5-0x4e00+1;
    public static char getRandomHan() {
        return (char) (0x4e00 + ran.nextInt(delta));
    }

    /**
     * 创建数据  医院
     *
     * @throws Exception 异常
     */
    @Test
    public  void createDataHospital() throws Exception {
        for (int i = 0; i < 50; i++) {
            String s = create();
            String t = create();
            String r = create();
            System.out.println();
            Hospital hospital = new Hospital();
            hospital.setHospitalName(s+t+r+"医院");
            hospital.setAddress(RandomValue.getRoad());
            hospital.setTelephoneNumber(RandomValue.getTel());
            hospital.setUpdateName(RoleConstants.ADMIN);
            System.out.println(hospital.toString());
            hospitalService.insert(hospital);
        }
    }

    /**
     * 创建数据 人
     *
     * @throws Exception 异常
     */
    @Test
    public  void createDataPerson() throws Exception {

        for (int i = 0; i < 2; i++) {
            Carrier carrier = new Carrier();
            String chineseName = NameRandom.getChineseName();
            String[] split = chineseName.split(":");
            carrier.setName(split[1]);
            carrier.setAge(ran.nextInt(80));
            carrier.setGender(split[0]);
            carrier.setHomeAddress(RandomValue.getRoad());
            carrier.setTelephoneNumber(RandomValue.getTel());
            carrier.setIdentityCard(AreaCodeList.generate());
            carrier.setCause("未知");
            int i1 = ran.nextInt(80);
            carrier.setFromHospital(i1 + "");
            carrier.setIsolationTime(LocalDateTime.now());
            int a = (int) (Math.random() * 3);
            carrierService.insert(carrier);
        }
    }


    public static String create() throws Exception {
        String str = null;
        int hightPos, lowPos; // 定义高低位
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值
        lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值
        byte[] b = new byte[2];
        b[0] = (new Integer(hightPos).byteValue());
        b[1] = (new Integer(lowPos).byteValue());
        str = new String(b, "GBk");//转成中文
        return str;
    }

}
