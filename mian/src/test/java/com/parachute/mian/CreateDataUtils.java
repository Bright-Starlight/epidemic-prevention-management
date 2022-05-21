package com.parachute.mian;

import com.parachute.mian.constant.RoleConstants;
import com.parachute.mian.entity.Hospital;
import com.parachute.mian.service.HospitalService;
import com.parachute.mian.utils.RandomValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Random;
@SpringBootTest
public class CreateDataUtils {

    @Autowired
    private HospitalService hospitalService;

    private static Random ran=new Random();
    private final static int delta=0x9fa5-0x4e00+1;
    public static char getRandomHan() {
        return (char) (0x4e00 + ran.nextInt(delta));
    }

    @Test
    public  void createData() throws Exception {
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
