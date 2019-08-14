package com.gvt.apollo;

import com.alibaba.fastjson.JSONObject;
import com.gvt.apollo.bean.BizBean;
import com.gvt.apollo.security.url.StringUrlParser;
import com.gvt.apollo.security.url.UrlParser;
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
import java.util.*;

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
    private String personPriKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIRfVeYKYQzV5yDd7LnOlobo74nKWinCvBAQ9qtuVeSZCeJxK/DFYj1Wdgga722VqXbZ4i5C2IUazXlloKaPmnFxwZzAUmI17CvdbtDh/ur5ItnDMZnQUkUFxn9dTR0EiqCEmjqGBN9o+qQeL/3ZcKgdbtt8rqP6Pt08qM3lUZFfAgMBAAECgYBolG1n5jFQk7ob5Fk/XvDbuzJsWTssnRY5Vz2aqPwhM6t0hFbjzP3VWfa8ZpNcr44IQRGJ3PP1DPzi+SCYFwI7h3cjLAWXNQluR6PHgOoeKiH0WmUDDwbSDvxPCW4oCsotCy+db1cHFl/XyES4FBrs+x/g7guDX5mqM58+Di2X4QJBAPq4914mavHIH/YgYjMrtdx7tREJQabHxZRbmgdHvoxVR6n8jfWS/8jYQEfUf+ermg0tlh3t5hvDAMHb2XhBze8CQQCHKKHjGFN5UX4lTq8N04iM5HW8Y/SQgt0KCm2JkvN7BUZQ6hmFhUcY8XcvClwMG5+4xEEsPWyp5XKEdF/pn+ORAkEA1FrdiWzYn7h0+a4r/lNDUV+l2KwSYwRJIIMFTq1BgvKoJB24zwqWgrJGn5AoVTxO2mKGBwt8Hn9noMpowyZZGQJAX4wv9ZTq3eboYINhaUrTS5buTIH1EHwSuthoW0tRaPRvox/7btKrUkzRTqXqMH0OytWipR2/RdP4wv5qF4R6oQJAFw+cHi3/8hFtOGgUNuP+kjC3QJq5/nE2sJuyBO42jW4ne6nzxP/k+nQbQB6Rvt3TOwunmGaNLDMCEw15UgLwfw==";
    private String personPubKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCEX1XmCmEM1ecg3ey5zpaG6O+JylopwrwQEParblXkmQnicSvwxWI9VnYIGu9tlal22eIuQtiFGs15ZaCmj5pxccGcwFJiNewr3W7Q4f7q+SLZwzGZ0FJFBcZ/XU0dBIqghJo6hgTfaPqkHi/92XCoHW7bfK6j+j7dPKjN5VGRXwIDAQAB";

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
        String json="{\n" +
                "\"appid\":\"ab6391387ed0410ea5f342bac2fe86b5\",\n" +
                "\"sign\":\"QEQLxPTNGLVgoQL/WD5qvsZPLNUisDR1vqJ/MsJ02eXhhSg9PAztk2Ju7yUrBmnhoWCDt6V3jy2KwifwqfjP5glBf76/5A4tR3CYzRp1Q4M2u0AfSiuI5mWptwqNBRUsg/5IazCeOlKEQn2emYiluaxYM2WF7BbIv1zmvjM5Zw4=\",\n" +
                "\"version\":\"1.0.0\",\n" +
                "    \"logisticsChannelName\":\"威盛国际速递\",\n" +
                "    \"logisticsCompanyName\":null,\n" +
                "    \"logisticsCompanyCode\":\"WHER\",\n" +
                "    \"storeId\":\"1275\",\n" +
                "    \"tenantId\":\"993\",\n" +
                "    \"logisticsData\":{\n" +
                "        \"orderNo\":\"68442fb3-1805-421e-915b-6e6e2447d0bc\",\n" +
                "        \"trackNo\":null,\n" +
                "        \"packType\":\"20\",\n" +
                "        \"packs\":\"1\",\n" +
                "        \"sendSite\":null,\n" +
                "        \"cbm\":null,\n" +
                "        \"clearanceMode\":\"BC\",\n" +
                "        \"payType\":\"1\",\n" +
                "        \"itemDeclareCurrency\":\"AUD\",\n" +
                "        \"threeSegmentCode\":null,\n" +
                "        \"expType\":\"1\",\n" +
                "        \"weight\":\"0.000\",\n" +
                "        \"customerName\":null,\n" +
                "        \"customerPwd\":null,\n" +
                "        \"monthCode\":null,\n" +
                "        \"remark\":null,\n" +
                "        \"goods\":[\n" +
                "            {\n" +
                "                \"brand\":null,\n" +
                "                \"type\":null,\n" +
                "                \"model\":\"\",\n" +
                "                \"name\":\"测试商品2\",\n" +
                "                \"nameZh\":\"测试商品2\",\n" +
                "                \"price\":\"2.0000\",\n" +
                "                \"qty\":\"1\",\n" +
                "                \"sku\":\"100150\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"shipperName\":\"阿飞\",\n" +
                "        \"shipperPhone\":\"13066668888\",\n" +
                "        \"shipperEmail\":null,\n" +
                "        \"shipperState\":\"广东省\",\n" +
                "        \"shipperCity\":\"深圳市\",\n" +
                "        \"shipperSuburb\":\"南山区\",\n" +
                "        \"shipperAddress\":\"桃源街道平山一路新视艺创客公园B栋4楼B408-409\",\n" +
                "        \"shipperPostcode\":null,\n" +
                "        \"consigneeName\":\"罗根\",\n" +
                "        \"consigneePhone\":\"13022225555\",\n" +
                "        \"consigneeEmail\":null,\n" +
                "        \"consigneeCnid\":\"420625199602150024\",\n" +
                "        \"consigneeState\":\"广东省\",\n" +
                "        \"consigneeCity\":\"深圳市\",\n" +
                "        \"consigneeSuburb\":\"南山区\",\n" +
                "        \"consigneeAddress\":\"桃源街道平山一路新视艺创客公园B栋4楼B408-409\",\n" +
                "        \"consigneePostcode\":null\n" +
                "    }\n" +
                "}";
        System.out.println(apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), json));
    }
    @Test
    public void validateSign() throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, SignatureException, InvalidKeyException {
        ApolloSdk apolloSdk=new ApolloSdk();
        String json="{\"logisticsData\":{\"packType\":\"20\",\"goods\":[{\"nameZh\":\"测试商品2\",\"price\":\"2.0000\",\"qty\":\"1\",\"name\":\"测试商品2\",\"model\":\"\",\"sku\":\"100150\"}],\"itemDeclareCurrency\":\"AUD\",\"shipperCity\":\"深圳市\",\"consigneeName\":\"罗根\",\"consigneeCnid\":\"420625199602150024\",\"payType\":\"1\",\"clearanceMode\":\"BC\",\"consigneePhone\":\"13022225555\",\"expType\":\"1\",\"shipperState\":\"广东省\",\"consigneeAddress\":\"桃源街道平山一路新视艺创客公园B栋4楼B408-409\",\"shipperSuburb\":\"南山区\",\"orderNo\":\"68442fb3-1805-421e-915b-6e6e2447d0bc\",\"weight\":\"0.000\",\"consigneeSuburb\":\"南山区\",\"shipperName\":\"阿飞\",\"packs\":\"1\",\"shipperAddress\":\"桃源街道平山一路新视艺创客公园B栋4楼B408-409\",\"shipperPhone\":\"13066668888\",\"consigneeState\":\"广东省\",\"consigneeCity\":\"深圳市\"},\"appid\":\"ab6391387ed0410ea5f342bac2fe86b5\",\"sign\":\"Wce3gzO8JjSFL++N4nGxMCv6QjmSD3KfWKfWV4tQVFXRWc9zHq0LwLl3HpM3vWBwu1pfIYLl2gPzB8HJUIE1DqtmxIf4W6i8fBi4lqb3gT+pYIxQ9vhoLf9g1R8UBT5WQHFzm+HTw1Gjpj3310cfi7pU8Kd+8xGiJtN7yOzFEYs=\",\"tenantId\":\"993\",\"logisticsCompanyCode\":\"WHER\",\"storeId\":\"1275\",\"version\":\"1.0.0\",\"logisticsChannelName\":\"威盛国际速递\"} ";

        System.out.println(apolloSdk.validateSign(SecurityUtils.getPubKey(personPubKey), json));
    }

    @Test
    public void urlSign() throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        String url="version=1.0.0&sign=1&trackNo=TWSEJB00001443&appid=ab6391387ed0410ea5f342bac2fe86b5&trackNo=1111";
        UrlParser urlParser=new StringUrlParser(url);
        String _51PrivateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIRfVeYKYQzV5yDd7LnOlobo74nKWinCvBAQ9qtuVeSZCeJxK/DFYj1Wdgga722VqXbZ4i5C2IUazXlloKaPmnFxwZzAUmI17CvdbtDh/ur5ItnDMZnQUkUFxn9dTR0EiqCEmjqGBN9o+qQeL/3ZcKgdbtt8rqP6Pt08qM3lUZFfAgMBAAECgYBolG1n5jFQk7ob5Fk/XvDbuzJsWTssnRY5Vz2aqPwhM6t0hFbjzP3VWfa8ZpNcr44IQRGJ3PP1DPzi+SCYFwI7h3cjLAWXNQluR6PHgOoeKiH0WmUDDwbSDvxPCW4oCsotCy+db1cHFl/XyES4FBrs+x/g7guDX5mqM58+Di2X4QJBAPq4914mavHIH/YgYjMrtdx7tREJQabHxZRbmgdHvoxVR6n8jfWS/8jYQEfUf+ermg0tlh3t5hvDAMHb2XhBze8CQQCHKKHjGFN5UX4lTq8N04iM5HW8Y/SQgt0KCm2JkvN7BUZQ6hmFhUcY8XcvClwMG5+4xEEsPWyp5XKEdF/pn+ORAkEA1FrdiWzYn7h0+a4r/lNDUV+l2KwSYwRJIIMFTq1BgvKoJB24zwqWgrJGn5AoVTxO2mKGBwt8Hn9noMpowyZZGQJAX4wv9ZTq3eboYINhaUrTS5buTIH1EHwSuthoW0tRaPRvox/7btKrUkzRTqXqMH0OytWipR2/RdP4wv5qF4R6oQJAFw+cHi3/8hFtOGgUNuP+kjC3QJq5/nE2sJuyBO42jW4ne6nzxP/k+nQbQB6Rvt3TOwunmGaNLDMCEw15UgLwfw==";
        System.out.println(Base64.getEncoder().encodeToString(SecurityUtils.sign(SecurityUtils.getPriKey(_51PrivateKey), urlParser.parse().getBytes())));
    }
    @Test
    public void urlValidateSign() throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        String url="appid=ab6391387ed0410ea5f342bac2fe86b5&trackNo=E000000696E&version=1.0.0";
        String _51PublicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCEX1XmCmEM1ecg3ey5zpaG6O+JylopwrwQEParblXkmQnicSvwxWI9VnYIGu9tlal22eIuQtiFGs15ZaCmj5pxccGcwFJiNewr3W7Q4f7q+SLZwzGZ0FJFBcZ/XU0dBIqghJo6hgTfaPqkHi/92XCoHW7bfK6j+j7dPKjN5VGRXwIDAQAB";
        String sign="Xk5KDFTTOv4TnNUorg4MxXJbdA7HnTntI5tkXyNJVRbopoI3EDYMaeHVCs8Wf9p8MZOMVvbkfYXM7F/zWIgw/R3LkJcIVyp3p/f+fcvgK3j0Whg24YgiIIC2YWBBTQcX3aNvyP4QGyDyjblKtyBiwzjnr0xmZvsZ5Jar5OgPvd8=";
        System.out.println(SecurityUtils.validateSign(SecurityUtils.getPubKey(_51PublicKey),
                url.getBytes(),
                Base64.getDecoder().decode(sign)
        ));
    }

    @Test
    public void appid(){
        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").toLowerCase().length());;
    }
}