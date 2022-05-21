package com.parachute.mian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.mian.constant.RoleConstants;
import com.parachute.mian.dao.HospitalDao;
import com.parachute.mian.entity.Hospital;
import com.parachute.mian.service.HospitalService;
import com.parachute.mian.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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
    public List<Hospital> getAll() {
        List<Hospital> list = this.list();
        list.forEach(obj->{
            obj.setKey(obj.getId());
        });
        return list;

    }

    @Override
    public void insert(Hospital hospital) {
       hospital.setUpdateTime(LocalDateTime.now());
       hospital.setCreateTime(LocalDateTime.now());
        hospital.setUpdateName(RoleConstants.ADMIN);
        super.save(hospital);
    }
}

