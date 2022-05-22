package com.parachute.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.main.entity.Carrier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * (Carrier)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
@Mapper
public interface CarrierDao extends BaseMapper<Carrier> {

    /**
     * 获取医院名字
     *
     * @param id id
     * @return {@link String}
     */
    @Select("SELECT hospital_name from hospital WHERE id = #{id}")
    String getHospitalName(@Param("id") String id);

}

