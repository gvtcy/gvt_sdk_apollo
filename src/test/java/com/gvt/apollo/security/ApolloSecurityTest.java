package com.gvt.apollo.security;


import com.gvt.apollo.utils.SecurityUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

/**
 * @author jiaozi<liaomin @ gvt861.com>
 * @since JDK8
 * Creation time：2019/8/8 10:41
 */
public class ApolloSecurityTest {

    private String platformPubKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFG4jX0dzwVN3F/JoyaSAbSUcK65TA9vpVs9PQQ/FkW4Fj55l8ki6wLPd5SRp6lWJwodvN1UJ+Zf7hRTLis8CGhtAcMi2z+gRrTfqTombmXkQ3r6mGAXrqRHh27CZ5v2HjpiJLfCOIOIYezcmQXtFwPY0cmd2bHW2YLA5N6IixwQIDAQAB";
    private String platformPriKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIUbiNfR3PBU3cX8mjJpIBtJRwrrlMD2+lWz09BD8WRbgWPnmXySLrAs93lJGnqVYnCh283VQn5l/uFFMuKzwIaG0BwyLbP6BGtN+pOiZuZeRDevqYYBeupEeHbsJnm/YeOmIkt8I4g4hh7NyZBe0XA9jRyZ3ZsdbZgsDk3oiLHBAgMBAAECgYAK6rIzeSvUrjEuLsR6w/J1KnJgK+gcV2U6qDAEEb53i6W2CuWkuNyJaHa3hM5eJWhYcn3ziL0WXskjsqg0vadZgVzyUF5ggkavjQZ15OApVaf98OvrGCbJa+1Sl+cnKIYWKNPCMN5MmA9oML6VC9UWgqlVjpN5TLgntDt0k7+WQQJBAP2hSIIiTIXedxaeGy2IJyr9Tog9Wqc3NmHhV1n2R1D0/FInDQrxeNuOcFi1raCzIN+sWd0zei+Bjxdr0bSvTSkCQQCGWfIPLBUrUN/8+Abxdkny9rl7ikT5G/QmF1xJL9AkivjPxS91B7JgWVq4YwFxYinZDReE19t6i6n/Mc5+bTrZAkEAosj0b8mx0a4CQzsA+I1NjlL8J4cxeud9+P6XgP8HsNc8Z0H3JhHr3wch5l3c7apqrATDvKyAfKMsAv1JoC1vWQJAMyl7eXWjFhy1P4NAOaF/Jav5FGeiPm77uam5thEkJVZay9xeZyWoMvK0DnV9bi0gnIxUwXzmErOu6ASSiyiTeQJBAOsaL/aewkS6/wbIVVhtxxyfJCx+RMNjh3vSUeR7OFftVClfQXd4LuCW8ORTDAmSHqMcfKZRv0EZdewWDiSevoU=";
    private String personPubKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnIZpC42a4R5TdVtOF/F3bfZoHMmf6CQTuEfmYVgeCiVGv8fI1o+gLJ4x2M2JsGeSSU9GQLHD+KzUR8/4AmhjDpju8hiBxkPa/UpLWObJ+DK+7CJwf8TAPyCSfYuZ6yb8VNyvaw5jceke4SF2Q2wY9457Ar7RipEcpwWlZk3VPIwIDAQAB";
    private String personPriKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKchmkLjZrhHlN1W04X8Xdt9mgcyZ/oJBO4R+ZhWB4KJUa/x8jWj6AsnjHYzYmwZ5JJT0ZAscP4rNRHz/gCaGMOmO7yGIHGQ9r9SktY5sn4Mr7sInB/xMA/IJJ9i5nrJvxU3K9rDmNx6R7hIXZDbBj3jnsCvtGKkRynBaVmTdU8jAgMBAAECgYBu+J3PyeP7efP7H1qlfVLomTY7jxmA6JowZRkAMCceYoUtuQ1k1mcNeP4HwciZFHwzOJpOC2QfL5s2R3ag+bB0quD+Ul+OOLz39WZfq6Aoh/0/RKTZaIXf1CluVAVjsrh3aBVqlpr88MaJ2I23wQ1MOqNFUow+5qSwuhaaiGljcQJBAOz+3rgBSMi7kirest9IrfrpF3PfppEQOmvkb/GdCmCiKcpM85QPSgibbhVftcQsvb0WtCHDELvMZzR2zkr00IkCQQC0iIhjJr0xDEdWmelAnIMEvw9k3wfduzI/TgBtt7BPuIlpKNlKM6cwYV2l30GhveJNqBIS5VDhFkOOEDJfdr9LAkEA0wU1Zn2uQx3Qzl8wwePDFjDJ8yDm/S+H9V2X13jPLq/1qe3OzLy4XOWYpWttO1njMCSxC4bWtYERPAO1N8S4kQJACfHDYYLOxRbiDxknShHU/bvwgyPt8P7Qw7/uMhz+L0YA+7PuVDIIMZgLaomjud8VyiVJ6ZSMIfnx+q9VtwB5hQJAfZ8JgcUoc7iXwpN/IdzUfyd5ZE/A0XlZVWroJENW2XDL0UUGvW+G+UVeAjJo7KMdRigieKLIhzpwUh8aDmcUag==";

    /**
     * 验证加解密
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     */
    @org.junit.Test
    public void encrypt() throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        String data="hello";
        //平台公钥加密
        PublicKey pubKey = SecurityUtils.getPubKey(platformPubKey);
        byte[] encrypt = SecurityUtils.encrypt(pubKey, data.getBytes());
        //平台私钥解密
        byte[] decrypt = SecurityUtils.decrypt(SecurityUtils.getPriKey(platformPriKey), encrypt);
        System.out.println(new String(decrypt));
    }

    /**
     * 签名和验证签名
     */
    @org.junit.Test
    public void sign() throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        String json="{" +
                "\"appid\":\"123\"," +
                "\"appSecrect\":\"1233434\"," +
                "\"payUser\":\"1\"" +
                "}";
        PublicKey pubKey = SecurityUtils.getPubKey(personPubKey);
        PrivateKey priKey = SecurityUtils.getPriKey(personPriKey);
        //签名
        byte[] sign = SecurityUtils.sign(priKey, json.getBytes());
        //验证签名
        System.out.println(SecurityUtils.validateSign(pubKey, json.getBytes(), sign));
    }

}
