package com.lv.example.springboot.util;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * xss非法标签过滤工具类
 * 过滤html中的xss字符
 * @author xbz
 */
public class JsoupUtil {

    /**
     * 使用自带的basicWithImages 白名单
     * 允许的便签有a,b,blockquote,br,cite,code,dd,dl,dt,em,i,li,ol,p,pre,q,small,span,
     * strike,strong,sub,sup,u,ul,img
     * 以及a标签的href,img标签的src,align,alt,height,width,title属性
     */
    private static final Whitelist whitelist = Whitelist.basicWithImages();
    /** 配置过滤化参数,不对代码进行格式化 */
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
    static {
        // 富文本编辑时一些样式是使用style来进行实现的
        // 比如红色字体 style="color:red;"
        // 所以需要给所有标签添加style属性
        whitelist.addAttributes(":all", "style");
    }

    public static String clean(String content) {
        if(!StringUtils.isEmpty(content)){
            content = content.trim();
        }
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }

    public static void main(String[] args) throws IOException {
        String text = "   <a href=\"http://www.baidu.com/a\" onclick=\"alert(1);\">sss</a><script>alert(0);</script>sss   ";
        System.out.println(clean(text));
    }


    public static String cleanJson(String s) {
        //先处理双引号的问题
        s = jsonStringConvert(s);
        return clean(s);
    }

    /**
     * 将json字符串本身的双引号以外的双引号变成单引号
     * @param s
     * @return
     */
    public static String jsonStringConvert(String s) {

        char[] temp = s.toCharArray();
        int n = temp.length;
        for (int i = 0; i < n; i++) {
            if (temp[i] == ':' && temp[i + 1] == '"') {
                for (int j = i + 2; j < n; j++) {
                    if (temp[j] == '"') {
                        //如果该字符为双引号,下个字符不是逗号或大括号,替换
                        if (temp[j + 1] != ',' && temp[j + 1] != '}') {
                            //将json字符串本身的双引号以外的双引号变成单引号
                            temp[j] = '\'';
                        } else if (temp[j + 1] == ',' || temp[j + 1] == '}') {
                            break;
                        }
                    }
                }
            }
        }
        String r = new String(temp);
        return r;
    }

}


