package com.parachute.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.main.entity.Carrier;
import com.parachute.main.entity.Hospital;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Select("SELECT hospital_name from hospital WHERE id = #{id} ")
    String getHospitalName(@Param("id") String id);

    /**
     * 得到医院
     *
     * @param hospitalName 医院名字
     * @return int
     */
    @Select("SELECT id from hospital WHERE hospital_name = #{hospitalName}")
    Integer getHospital(@Param("hospitalName")String hospitalName);

    /**
     * 获得新确认
     *
     * @param s 年代
     * @return {@link List}<{@link Carrier}>
     */
    @Select("SELECT * from carrier where update_time like #{date} and is_confirm = '1' and is_delete = '0'")
    List<Carrier> getNewConfirm(@Param("date") String date);

    /**
     * 获得新治疗
     *
     * @param s 年代
     * @return {@link List}<{@link Carrier}>
     */
    @Select("SELECT * from carrier where update_time like #{date} and is_cure = '1' and is_delete = '0'")
    List<Carrier> getNewCure(String s);

    /**
     * 选择new亲密
     *
     * @param s 年代
     * @return {@link List}<{@link Carrier}>
     */
    @Select("SELECT * from carrier where update_time like #{date} and is_confirm = '0' and is_die = '0' and" +
            " is_cure = '0' and is_delete = '0' and is_isolation = '0'" +
            " ")
    List<Carrier> selectNewIntimate(@Param("date") String date);
    @Select("SELECT * from carrier where update_time like #{date} and is_die = '1' and is_delete = '0'")
    List<Carrier> getNewDie(String s);
}

