package com.dream.order.manage.timezone.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author jiangll01
 * @Date: 2020/8/19 16:30
 * @Description: 前端传输时间数据时，是传输的字符串，后端接收的话，需要添加
 * @DateTimeFormat(pattern = "yyyy-MM-dd")转为java.util.date
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeBean {

    private int id;

    private String timeName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatedTime;

}
