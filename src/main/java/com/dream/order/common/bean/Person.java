package com.dream.order.common.bean;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author jiangll01
 * @Date: 2020/6/12 16:28
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {
    @NotBlank
    private String name;
    private Integer age;
    private String sex;
    private Map<String,Object> map;
    private List<String> annimals;
}
