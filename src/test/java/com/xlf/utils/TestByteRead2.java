package com.xlf.utils;

import com.xlf.utils.annotate.ByteReadField;

/**
 * author: xiaoliufu
 * date:   2017/9/13.
 * description:
 */
public class TestByteRead2 {
    @ByteReadField(start = 1,end = 4)
    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "TestByteRead2{" +
                "a='" + a + '\'' +
                '}';
    }
}
