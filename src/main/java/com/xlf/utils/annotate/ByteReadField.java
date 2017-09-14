package com.xlf.utils.annotate;

import java.lang.annotation.*;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description: 字节读取field
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ByteReadField {
    public int start();
    public int end();
}
