package lambda;

import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by noodles on 16/4/10.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        Pattern p = Pattern.compile("^[0-9a-zA-Z_-]{1,}$");// 正则表达式匹配手机号
        Matcher m = p.matcher("_-11232fdsA");
        final boolean matches = m.matches();
        System.out.println(matches);
    }

}
