package com.lv.example.springboot.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {

    //private static Object Map;

    public static void main(String[] args) {
//        String a = "  /test/**,/test2/**,/api ";
//       List<String> list = Arrays.asList(a.split(","));
//        for (String value : list) {
//            System.out.println(value);
//        }

        testReturn r = new testReturn();
        List<Map<String, Object>> list = r.test(new HashMap<String, Object>());
    }
}
