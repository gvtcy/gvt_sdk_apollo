package com.gvt.apollo.bean;

import lombok.Data;

/**
 * @author jiaozi<liaomin @ gvt861.com>
 * @since JDK8
 * Creation time：2019/8/8 10:23
 */
@Data
public class EncrptData<T> {
    private T data;
    private String hash;
}
