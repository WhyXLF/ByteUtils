package com.xlf.utils.impl;

import com.xlf.utils.ByteCharUtils;
import com.xlf.utils.inter.ByteReadFunction;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description: 测试使用方法
 */
public class TempByteReadFunctionImpl implements ByteReadFunction {
    @Override
    public void setPath(String path) {
    }

    @Override
    public byte[] read(int size) {
        char chars[] = new char[size];
        chars[0]= 'a';
        chars[1]= 'b';
        chars[2]= 'c';
        chars[3]= '1';
        chars[4]= '2';

        return ByteCharUtils.getBytes(chars);
    }

    @Override
    public int hasNext() {
        return 5;
    }
}
