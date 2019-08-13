package com.gvt.apollo.security.url;

/**
 * @author jiaozi《liaomin艾特gvt861.com》
 * @since JDK8
 * Creation time：2019/8/13 13:12
 */
public interface UrlParser<T> {
    public String parse(T obj);
}
