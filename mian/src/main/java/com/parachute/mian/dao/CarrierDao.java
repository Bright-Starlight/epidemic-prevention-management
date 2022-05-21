package com.parachute.mian.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.mian.entity.Carrier;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (Carrier)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
@Mapper
public interface CarrierDao extends BaseMapper<Carrier> {

}

