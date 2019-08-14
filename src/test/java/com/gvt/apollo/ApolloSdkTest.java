package com.gvt.apollo;

import com.alibaba.fastjson.JSONObject;
import com.gvt.apollo.bean.BizBean;
import com.gvt.apollo.utils.SecurityUtils;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author jiaozi《liaomin艾特gvt861.com》
 * @since JDK8
 * Creation time：2019/8/12 20:39
 */
public class ApolloSdkTest {
    @Data
    @ToString
    public class Shop implements BizBean {
        private String appid;
        private String shopName;
        private String shopLocation;
        private Member member=new Member();
        private String sign;
        @Override
        public void setSign(String sign) {
           this.sign=sign;
        }
    }
    @Data
    @ToString
    public class Member{
        private String memberId;
        private String memberName;
    }
    /**
     * 个人私钥
     */
   private String personPriKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKchmkLjZrhHlN1W04X8Xdt9mgcyZ/oJBO4R+ZhWB4KJUa/x8jWj6AsnjHYzYmwZ5JJT0ZAscP4rNRHz/gCaGMOmO7yGIHGQ9r9SktY5sn4Mr7sInB/xMA/IJJ9i5nrJvxU3K9rDmNx6R7hIXZDbBj3jnsCvtGKkRynBaVmTdU8jAgMBAAECgYBu+J3PyeP7efP7H1qlfVLomTY7jxmA6JowZRkAMCceYoUtuQ1k1mcNeP4HwciZFHwzOJpOC2QfL5s2R3ag+bB0quD+Ul+OOLz39WZfq6Aoh/0/RKTZaIXf1CluVAVjsrh3aBVqlpr88MaJ2I23wQ1MOqNFUow+5qSwuhaaiGljcQJBAOz+3rgBSMi7kirest9IrfrpF3PfppEQOmvkb/GdCmCiKcpM85QPSgibbhVftcQsvb0WtCHDELvMZzR2zkr00IkCQQC0iIhjJr0xDEdWmelAnIMEvw9k3wfduzI/TgBtt7BPuIlpKNlKM6cwYV2l30GhveJNqBIS5VDhFkOOEDJfdr9LAkEA0wU1Zn2uQx3Qzl8wwePDFjDJ8yDm/S+H9V2X13jPLq/1qe3OzLy4XOWYpWttO1njMCSxC4bWtYERPAO1N8S4kQJACfHDYYLOxRbiDxknShHU/bvwgyPt8P7Qw7/uMhz+L0YA+7PuVDIIMZgLaomjud8VyiVJ6ZSMIfnx+q9VtwB5hQJAfZ8JgcUoc7iXwpN/IdzUfyd5ZE/A0XlZVWroJENW2XDL0UUGvW+G+UVeAjJo7KMdRigieKLIhzpwUh8aDmcUag==";
   private String personPubKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnIZpC42a4R5TdVtOF/F3bfZoHMmf6CQTuEfmYVgeCiVGv8fI1o+gLJ4x2M2JsGeSSU9GQLHD+KzUR8/4AmhjDpju8hiBxkPa/UpLWObJ+DK+7CJwf8TAPyCSfYuZ6yb8VNyvaw5jceke4SF2Q2wY9457Ar7RipEcpwWlZk3VPIwIDAQAB";

    @Test
    public void wrapSign() throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, SignatureException, InvalidKeyException {
        ApolloSdk apolloSdk=new ApolloSdk();
        Shop shop=new Shop();
        shop.setAppid("rr333sss7788");
        shop.setShopName("apos门店");
        shop.setShopLocation("深圳南山新区西丽街道");
        Member member=new Member();
        member.setMemberId("11");
        member.setMemberName("张三");
        shop.setMember(member);
        System.out.println(JSONObject.toJSONString(shop));
        Shop realShop = apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), shop);
        System.out.println(realShop.getSign().length());
    }
    @Test
    public void wrapJsonSign() throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, SignatureException, InvalidKeyException {
        ApolloSdk apolloSdk=new ApolloSdk();
        String json="{\"appid\":\"rr333sss7788\",\"member\":{\"memberId\":\"11\",\"memberName\":\"张三\"},\"shopLocation\":\"深圳南山新区西丽街道\",\"shopName\":\"apos门店\"}";
        System.out.println(apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), json));
    }
    @Test
    public void validateSign() throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, SignatureException, InvalidKeyException {
        ApolloSdk apolloSdk=new ApolloSdk();
        String json="{\"shopLocation\":\"深圳南山新区西丽街道\",\"appid\":\"rr333sss7788\",\"member\":{\"memberName\":\"张三\",\"memberId\":\"11\"},\"sign\":\"PB0y894H1GYbUOdQdLHjiZ73u/dlgqg+2v5s0Wp8TJqX+p7s+Z3YRU/DdCJJEvYIkjTwODnfjNQ60EGobRBL3pnLKNoKSrdDQFRPpbFsQSUam/PlEYTXeeFTlZhxF8o7j5wagfX65bt7GYuxLdsmwwW2NbpLargzmubEe0cLB00=\",\"shopName\":\"apos门店\"}";
        System.out.println(apolloSdk.validateSign(SecurityUtils.getPubKey(personPubKey), json));
    }
    @Test
    public void appid(){
        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").toLowerCase().length());;
    }
}