package com.gvt.apollo.security.url;

import com.gvt.apollo.security.sign.MapToUrlSign;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author jiaozi《liaomin@gvt861.com》
 * @since JDK8
 * Creation time：2019/8/13 13:14
 */
@Slf4j
public class MapUrlParser implements UrlParser<Map<String,Object>> {
    @Override
    public String parse(Map<String, Object> obj) {
        return urlMap(null,obj);
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
        log.debug(paramUrl.toString());
        return paramUrl.toString();
    }
}
