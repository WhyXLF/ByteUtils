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
    @ByteReadField(start = 5,end = 10)
    private String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "TestByteRead2{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}
