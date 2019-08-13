package com.gvt.apollo.security.sign.url;

import com.alibaba.fastjson.JSONObject;

import java.security.PrivateKey;

/**
 * bean签名
 * @author jiaozi<liaomin @ gvt861.com>
 * @since JDK8
 * Creation time：2019/8/8 14:08
 */
public class BeanTranslateUrlSign<T> extends JsonTranslateUrlSign {

    public BeanTranslateUrlSign(PrivateKey privateKey,T obj) {
        super(privateKey,JSONObject.toJSONString(obj));
    }
}
