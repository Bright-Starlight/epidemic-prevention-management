package com.parachute.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.main.entity.Hospital;
import org.apache.ibatis.annotations.Mapper;
/**
 * (Hospital)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
@Mapper
public interface HospitalDao extends BaseMapper<Hospital> {

}

