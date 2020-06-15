package com.dream.order.manage.instance;

import com.dream.order.common.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangll01
 * @Date: 2020/6/12 16:53
 * @Description:
 */
@Service
public class instanceService {
    @Autowired
    Person person;

    public void getPerson(){
        System.out.println(person.toString());
        System.out.println(person.toString());
        System.out.println(person.toString());
        System.out.println(person.toString());
    }

}
