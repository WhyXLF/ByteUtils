package com.xlf.utils.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description: 字节读取注解类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ByteReadService {
    public String path();
}
