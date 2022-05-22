package com.parachute.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.main.entity.Carrier;

import java.util.List;

/**
 * (Carrier)表服务接口
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
public interface CarrierService extends IService<Carrier> {

    void insert(Carrier carrier);

    List<Carrier> selectNewIntimate();
}

