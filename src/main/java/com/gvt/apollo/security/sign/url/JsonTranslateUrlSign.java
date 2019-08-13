package com.gvt.apollo.security.sign.url;

import com.alibaba.fastjson.JSONObject;

import java.security.PrivateKey;
import java.util.Map;

/**
 * json格式数据签名
 * {
 *   appid:34324535,
 *   secrect:redfg564564,
 *   mch_id:11,
 *   hash:34546456
 * }
 * @author jiaozi<liaomin @ gvt861.com>
 * @since JDK8
 * Creation time：2019/8/8 12:15
 */
public class JsonTranslateUrlSign extends MapTranslateUrlSign {
    private String jsonData;
    private PrivateKey privateKey;

    public JsonTranslateUrlSign(PrivateKey privateKey,String jsonData) {
        super(privateKey,JSONObject.parseObject(jsonData, Map.class));
    }
}
