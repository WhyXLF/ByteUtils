package com.xlf.utils.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: xiaoliufu
 * date:   2017/9/14.
 * description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ByteReadList {
    /**
     * 分成几个element
     * @return
     */
    public int part();

    /**
     * 开始位置
     * @return
     */
    public int start();

    /**
     * 结束位置
     * @return
     */
    public int end();
}
