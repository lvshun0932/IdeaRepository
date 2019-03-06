package com.lv.example.springboot.util;

import java.util.ArrayList;
import java.util.List;

/***
 *@Title ${TODO}
 *@author shunlv
 *@Date 2018/12/10 2:17 PM
 */
public class testReturn {

    public <T> List<T> test(T bo){
        List<T> list = new ArrayList<>();
        return list;
    }
}
