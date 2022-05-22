package com.parachute.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.main.constant.RoleConstants;
import com.parachute.main.dao.CarrierDao;
import com.parachute.main.entity.Carrier;
import com.parachute.main.service.CarrierService;
import com.parachute.main.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * (Carrier)表服务实现类
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
@Service("carrierService")
public class CarrierServiceImpl extends ServiceImpl<CarrierDao, Carrier> implements CarrierService {

    @Autowired
    private CarrierDao carrierDao;

    @Override
    public void insert(Carrier carrier) {
        carrier.setCreateTime(LocalDateTime.now());
        carrier.setUpdateTime(LocalDateTime.now());
        carrier.setUpdateName(RoleConstants.ADMIN);
        this.save(carrier);
    }

    @Override
    public List<Carrier> selectNewIntimate() {
        LambdaQueryWrapper<Carrier> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Carrier::getIsDie,0)
                .eq(Carrier::getIsConfirm,0)
                .eq(Carrier::getIsCure,0);
        List<Carrier> list = list(wrapper);
        list.forEach(obj->{
            String hospitalName = carrierDao.getHospitalName(obj.getFromHospital());
            obj.setFromHospital(hospitalName);
        });
        return list;
}
}

