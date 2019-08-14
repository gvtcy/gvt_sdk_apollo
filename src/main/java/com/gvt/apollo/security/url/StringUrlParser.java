package com.gvt.apollo.security.url;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author jiaozi《liaomin@gvt861.com》
 * @since JDK8
 * Creation time：2019/8/14 9:00
 */
public class StringUrlParser extends MapUrlParser {
    private Map<String,String> dataMap=new TreeMap<>();

    public StringUrlParser(String queryString) {
        super(getHeader(queryString));
    }
    private static Map<String, Object> getHeader(String url) {
        Map<String, Object> map = new TreeMap<>();
        int start = url.indexOf("?");
        String str = url.substring(start + 1);
        System.out.println(str);
        String[] paramsArr = str.split("&");
        for (String param : paramsArr) {
            int spCharIndex = param.indexOf("=");
            String key=param.substring(0,spCharIndex);
            String value=null;
            if(spCharIndex<param.length()){
               value=param.substring(spCharIndex+1);
            }
            if(StringUtils.isNotEmpty(value) && !"null".equalsIgnoreCase(value)){
                map.put(key,value);
            }
        }
        return map;
    }
}
