package com.xlf.utils;

import com.xlf.utils.inter.ByteReadFunction;

import java.lang.reflect.Field;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description:字节读取策略
 */
public interface ByteReadStrategy {
    /**
     * 字节读取策略
     *
     * @param obj
     */
    void read(Object obj) throws IllegalAccessException, InstantiationException;

}
