package com.parachute.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.main.constant.ValidateConstants;
import com.parachute.main.entity.Hospital;

import java.util.HashMap;
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
    List<Hospital> getPage();

    /**
     * 插入
     *
     * @param hospital 医院
     */
    void insert(Hospital hospital);

    /**
     * 验证表单
     *
     * @param hospital 医院
     * @return {@link Boolean}
     */
    ValidateConstants validate(Hospital hospital);

    List<HashMap<String, String>> getAll();
}

