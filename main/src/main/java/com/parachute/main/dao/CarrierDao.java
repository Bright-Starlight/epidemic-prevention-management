package com.parachute.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.main.entity.Carrier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


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
     * 得到医院数据
     *
     * @param hospitalName 医院名字
     * @return int
     */
    @Select("SELECT id from hospital WHERE hospital_name = #{hospitalName}")
    Integer getHospital(@Param("hospitalName")String hospitalName);

    /**
     * 获得新增确诊
     *
     * @param date 年代
     * @return {@link List}<{@link Carrier}>
     */
    @Select("SELECT * from carrier where update_time like #{date} and is_confirm = '1' and is_delete = '0'")
    List<Carrier> getNewConfirm(@Param("date") String date);

    /**
     * 获得新增治愈
     *
     * @param date 年代
     * @return {@link List}<{@link Carrier}>
     */
    @Select("SELECT * from carrier where update_time like #{date} and is_cure = '1' and is_delete = '0'")
    List<Carrier> getNewCure(String date);

    /**
     * 获取新增密接
     *
     * @param date 日期
     * @return {@link List}<{@link Carrier}>
     */
    @Select("SELECT * from carrier where update_time like #{date} and is_confirm = '0' and is_die = '0' and" +
            " is_cure = '0' and is_delete = '0' and is_isolation = '0'" +
            " ")
    List<Carrier> selectNewIntimate(@Param("date") String date);

    /**
     * 获得新增遇难
     *
     * @param s 年代
     * @return {@link List}<{@link Carrier}>
     */
    @Select("SELECT * from carrier where update_time like #{date} and is_die = '1' and is_delete = '0'")
    List<Carrier> getNewDie(String s);

    /**
     * 得到累计确诊统计数据
     *
     * @param date 日期
     * @return {@link String}
     */
    @Select("select count(c.id) FROM carrier c WHERE c.create_time <= #{date} and c.is_confirm = 1")
    Integer getConfirmReport(@Param("date") String date);

    /**
     * 得到累计密接统计数据
     *
     * @param date 日期
     * @return {@link String}
     */
    @Select("select count(c.id) FROM carrier c WHERE c.create_time <= #{date} and is_isolation = 1")
    Integer getIntimateReport(@Param("date")String date);

    /**
     * 得到累计治愈统计数据
     *
     * @param date 日期
     * @return {@link String}
     */

    @Select("select count(c.id) FROM carrier c WHERE c.create_time <= #{date} and c.is_cure = 1")
    Integer getCureReport(@Param("date")String date);

    /**
     * 得到累积死亡统计数据
     *
     * @param date 日期
     * @return {@link String}
     */
    @Select("select count(c.id) FROM carrier c WHERE c.create_time <= #{date} and c.is_die = 1")
    Integer getDieReport(@Param("date")String date);


    /**
     * 得到新增确诊统计数据
     *
     * @param date 日期
     * @return {@link Integer}
     */
    @Select("select count(c.id) FROM carrier c WHERE c.create_time like #{date} and c.is_confirm = 1")
    Integer getNewConfirmReport(@Param("date") String date);

    /**
     * 得到新增密接统计数据
     *
     *
     * @param date 日期
     * @return {@link Integer}
     */
    @Select("select count(c.id) FROM carrier c WHERE c.create_time like #{date} and is_isolation = 1")
    Integer getNewIntimateReport(@Param("date")String date);

    /**
     * 得到新增治愈统计报告
     *
     *
     * @param date 日期
     * @return {@link Integer}
     */

    @Select("select count(c.id) FROM carrier c WHERE c.create_time like #{date} and c.is_cure = 1")
    Integer getNewCureReport(@Param("date")String date);

    /**
     * 得到新增遇难统计报告
     *
     *
     * @param date 日期
     * @return {@link Integer}
     */
    @Select("select count(c.id) FROM carrier c WHERE c.create_time like #{date} and c.is_die = 1")
    Integer getNewDieReport(@Param("date")String date);

}

