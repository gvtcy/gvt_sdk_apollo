package com.gvt.apollo.security.url;

import com.gvt.apollo.security.sign.MapToUrlSign;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author jiaozi《liaomin@gvt861.com》
 * @since JDK8
 * Creation time：2019/8/13 13:14
 */
@Slf4j
public class MapUrlParser implements UrlParser {
    private Map<String,Object> dataMap=new TreeMap<>();

    public MapUrlParser(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public String parse() {
        String returnUrl=urlMap(null,dataMap);
        if(returnUrl.toString().endsWith("&")){
            returnUrl=returnUrl.toString().substring(0,returnUrl.length()-1);
            log.debug(returnUrl);
            System.out.println(returnUrl);
            return returnUrl;
        }
        log.debug(returnUrl.toString());
        System.out.println(returnUrl);
        return returnUrl;
    }
    public String urlMap(String parentKey,Map<String,Object> map){
        StringBuffer paramUrl=new StringBuffer();
        map.entrySet().forEach(entry->{
            if(!MapToUrlSign.SIGN_KEY.equals(entry.getKey())) {
                Object value = entry.getValue();
                if (value instanceof Map) {
                    Map<String, Object> ctreeMap = new TreeMap<String, Object>();
                    ctreeMap.putAll((Map) value);
                    paramUrl.append(urlMap(entry.getKey(),ctreeMap));
                } else {
                    paramUrl.append((parentKey==null?entry.getKey():(parentKey+"_"+entry.getKey())) + "=" + entry.getValue() + "&");
                }
            }
        });

        return paramUrl.toString();
    }
}
