package com.gvt.apollo.security.sign;

import com.gvt.apollo.utils.SecurityUtils;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Map;

/**
 * url签名 比如a=1&amp;b=2
 * @author jiaozi《liaomin艾特gvt861.com》
 * @since JDK8
 * Creation time：2019/8/8 15:43
 */
public abstract class AbstractUrlSign implements RestSign {
    /**
     * 获取url
     * @return 拼接url
     */
    public abstract String url();

    @Override
    public byte[] sign() throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if(!allowdSign()){
            throw new SecurityException("数据中是否未包含appid字符串");
        }
        return SecurityUtils.sign((PrivateKey) getKey(), url().getBytes());
    }

    @Override
    public boolean validateSign() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        Map data = (Map)this.data();
        return SecurityUtils.validateSign((PublicKey) getKey(),
                url().getBytes(),
                Base64.getDecoder().decode(data.get(MapToUrlSign.SIGN_KEY).toString())
                );
    }
}
