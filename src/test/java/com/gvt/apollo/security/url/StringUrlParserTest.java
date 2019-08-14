package com.gvt.apollo.security.url;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jiaozi<liaomin @ gvt861.com>
 * @since JDK8
 * Creation time：2019/8/14 9:10
 */
public class StringUrlParserTest {

    @Test
    public void testUrl(){
        StringUrlParser stringUrlParser=new StringUrlParser("sign=123&version=111&appid=545&gg=333");
        System.out.println(stringUrlParser.parse());
    }
}