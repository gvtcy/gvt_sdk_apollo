package com.gvt.apollo.security.sign.url;



import lombok.extern.slf4j.Slf4j;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序）格式（即key1=value1&amp;key2=value2…）拼接成字符串stringA。
 * @author jiaozi《liaomin艾特gvt861.com》
 * @since JDK8
 * Creation time：2019/8/8 14:04
 */
@Slf4j
public class MapTranslateUrlSign extends AbstractUrlParamSign {
    private Map<String,Object> jsonMap;
    private PrivateKey privateKey;
    public static final String SIGN_KEY="sign";
    public static final String APPID_KEY="appid";
    public MapTranslateUrlSign(PrivateKey privateKey,Map<String,Object> jsonMap) {
        Map<String, Object> treeMap = new TreeMap<String, Object>();
        treeMap.putAll(jsonMap);
        this.jsonMap = treeMap;
        this.privateKey = privateKey;
    }

    @Override
    public Object signData() {
        return this.jsonMap;
    }
    @Override
    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
    public String urlMap(String parentKey,Map<String,Object> map){
        StringBuffer paramUrl=new StringBuffer();
        map.entrySet().forEach(entry->{
            if(!SIGN_KEY.equals(entry.getKey())) {
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
    @Override
    public String url() {
        StringBuffer paramUrl=new StringBuffer();
        return urlMap(null,jsonMap);
    }

    @Override
    public boolean allowdSign() {
        if(!this.jsonMap.containsKey(APPID_KEY)) {
            return false;
        }
        return true;
    }
}
