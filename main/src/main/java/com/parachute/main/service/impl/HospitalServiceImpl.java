package com.parachute.main.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.main.constant.RoleConstants;
import com.parachute.main.constant.ValidateConstants;
import com.parachute.main.dao.HospitalDao;
import com.parachute.main.entity.Hospital;
import com.parachute.main.service.HospitalService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * (Hospital)表服务实现类
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
@Service("hospitalService")
public class HospitalServiceImpl extends ServiceImpl<HospitalDao, Hospital> implements HospitalService {

    @Override
    public List<Hospital> getPage() {
        List<Hospital> list = this.list();
        list.forEach(obj-> obj.setKey(obj.getId()));
        return list;

    }

    @Override
    public void insert(Hospital hospital) {
        //校验表单
      if (Boolean.TRUE.equals(validate(hospital).getFlag())){
          hospital.setUpdateTime(LocalDateTime.now());
          hospital.setCreateTime(LocalDateTime.now());
          hospital.setUpdateName(RoleConstants.ADMIN);
          super.save(hospital);
      }
    }

    @Override
    public ValidateConstants validate(Hospital hospital) {
        String telephoneNumber = hospital.getTelephoneNumber();
        String regex = "^\\d+$";
        if (!telephoneNumber.matches(regex)) {
            return ValidateConstants.of(ValidateConstants.TELEPHONE_NUMBER_ERROR,false);
        }

        return ValidateConstants.of("",true);
    }

    @Override
    public List<HashMap<String, String>> getAll() {
        List<Hospital> list = this.list();
        List<HashMap<String, String>> data = new ArrayList<>();
        list.forEach(obj->{
            HashMap<String, String> map = new HashMap<>(1);
            map.put("id",obj.getId()+ "");
            map.put("hospitalName",obj.getHospitalName());
            data.add(map);
        });
        return data;
    }


}

