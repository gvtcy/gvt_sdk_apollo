package com.gvt.apollo.security.sign;

import com.alibaba.fastjson.JSONObject;

import java.security.PrivateKey;

/**
 * bean签名
 * @author 《liaomin艾特gvt861.com》
 * @since JDK8
 * Creation time：2019/8/8 14:08
 */
public class BeanToUrlSign<T> extends JsonToUrlSign {

    public BeanToUrlSign(PrivateKey privateKey, T obj) {
        super(privateKey,JSONObject.toJSONString(obj));
    }
}
