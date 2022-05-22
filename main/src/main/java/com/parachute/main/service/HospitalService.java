package com.parachute.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.main.entity.Hospital;

import java.util.List;

/**
 * (Hospital)表服务接口
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
public interface HospitalService extends IService<Hospital> {

    /**
     * 得到医院相关信息
     *
     * @return {@link List}<{@link Hospital}>
     */
    List<Hospital> getAll();

    void insert(Hospital hospital);
}

