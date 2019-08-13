package com.gvt.apollo;

import com.alibaba.fastjson.JSONObject;
import com.gvt.apollo.bean.BizBean;
import com.gvt.apollo.security.sign.url.BeanTranslateUrlSign;
import com.gvt.apollo.security.sign.url.JsonTranslateUrlSign;
import com.gvt.apollo.security.sign.url.MapTranslateUrlSign;
import com.gvt.apollo.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * @author jiaozi<liaomin @ gvt861.com>
 * @since JDK8
 * Creation time：2019/8/8 12:13
 */
public class ApolloSdk {
    /**
     * json数据中包装签名字符串放篡改
     * @param personPriKey 个人私钥
     * @param jsonData 业务json数据
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     */
    public String wrapSign(PrivateKey personPriKey, /**PublicKey platformPubKey,**/String jsonData) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        JsonTranslateUrlSign jsonTranslateUrlSign=new JsonTranslateUrlSign(personPriKey,jsonData);
        byte[] sign = jsonTranslateUrlSign.sign();
        JSONObject jsonObject=JSONObject.parseObject(jsonData);
//        jsonObject.put(MapTranslateUrlSign.SIGN_KEY,new String(SecurityUtils.encrypt(platformPubKey,sign),charset));
        jsonObject.put(MapTranslateUrlSign.SIGN_KEY,Base64.getEncoder().encodeToString(sign));
        return jsonObject.toJSONString();
    }

    /**
     * 对象包装签名
     * @param personPriKey 个人私钥
     * @param object 继承自BizBean的实体类
     * @param <T>
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     */
    public <T extends BizBean> T wrapSign(PrivateKey personPriKey/**, PublicKey platformPubKey**/, T object) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        BeanTranslateUrlSign<T> jsonTranslateUrlSign=new BeanTranslateUrlSign<T>(personPriKey,object);
        byte[] sign = jsonTranslateUrlSign.sign();
        object.setSign(Base64.getEncoder().encodeToString(sign));
        return object;
    }
}
