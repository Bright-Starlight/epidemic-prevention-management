package com.parachute.main.entity;

import lombok.Data;

/**
 * 签证官报告
 *
 * @author machi
 * @date 2022/05/29
 */
@Data
public class ReportVO {

    /**
     * 标题
     */
    private String title;
    /**
     * 日期
     */
    private String date;
    /**
     * 值
     */
    private Integer value;

}
