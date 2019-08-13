package com.gvt.apollo.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

/**
 * 不同业务数据格式得到hash过程
 * @author jiaozi<liaomin @ gvt861.com>
 * @since JDK8
 * Creation time：2019/8/8 12:15
 */
public interface Sign {
    /**
     * 签名数据
     * @return
     */
    public Object signData();

    /**
     * 私钥
     * @return
     */
    public PrivateKey getPrivateKey();
    /**
     * 是否允许签名
     * @return
     */
    public boolean allowdSign();

    /**
     * 签名接口
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public byte[] sign() throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException;
}
