package com.dream.order.manage.timezone.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author jiangll01
 * @Date: 2020/11/4 16:12
 * @Description:
 */
@Data
public class TimeVo {
    private int id;

    private String timeName;


    private Date createdTime;


    private Date updatedTime;
}
