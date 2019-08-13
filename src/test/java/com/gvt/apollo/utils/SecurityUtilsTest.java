package com.gvt.apollo.utils;

import org.junit.Test;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

/**
 * @author jiaozi<liaomin @ gvt861.com>
 * @since JDK8
 * Creation time：2019/8/8 10:49
 */
public class SecurityUtilsTest {

    @Test
    public void genKeyPair() throws NoSuchAlgorithmException {
        KeyPair keyPair = SecurityUtils.genKeyPair();
        System.out.println(new String(keyPair.getPublic().getEncoded()));
        System.out.println(SecurityUtils.base64Key(keyPair.getPublic()));
        System.out.println(SecurityUtils.base64Key(keyPair.getPrivate()));
    }

}