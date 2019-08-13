package com.gvt.apollo.security.sign.url;

import com.gvt.apollo.security.Sign;
import com.gvt.apollo.utils.SecurityUtils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

/**
 * url签名 比如a=1&b=2
 * @author jiaozi<liaomin @ gvt861.com>
 * @since JDK8
 * Creation time：2019/8/8 15:43
 */
public abstract class AbstractUrlParamSign implements Sign {
    /**
     * 获取url
     * @return
     */
    public abstract String url();

    @Override
    public byte[] sign() throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if(!allowdSign()){
            throw new SecurityException("数据中是否未包含appid字符串");
        }
        return SecurityUtils.sign(getPrivateKey(), url().getBytes());
    }
}
