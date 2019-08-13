package com.gvt.apollo.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

/**
 * 不同业务数据格式得到hash过程
 * @author jiaozi《liaomin艾特gvt861.com》
 * @since JDK8
 * Creation time：2019/8/8 12:15
 */
public interface Sign {
    /**
     * 签名数据
     * @return 被签名数据
     */
    public Object signData();

    /**
     * 私钥
     * @return 私钥
     */
    public PrivateKey getPrivateKey();
    /**
     * 是否允许签名
     * @return 是否允许签名
     */
    public boolean allowdSign();

    /**
     * 签名接口
     * @return 签名后数据
     * @throws InvalidKeySpecException 不合法key描述
     * @throws NoSuchAlgorithmException 算法错误
     * @throws InvalidKeyException 不合法key
     * @throws SignatureException 签名异常
     */
    public byte[] sign() throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException;
}
