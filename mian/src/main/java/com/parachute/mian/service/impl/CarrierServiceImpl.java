package com.parachute.mian.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.mian.dao.CarrierDao;
import com.parachute.mian.entity.Carrier;
import com.parachute.mian.service.CarrierService;
import org.springframework.stereotype.Service;

/**
 * (Carrier)表服务实现类
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
@Service("carrierService")
public class CarrierServiceImpl extends ServiceImpl<CarrierDao, Carrier> implements CarrierService {

}

