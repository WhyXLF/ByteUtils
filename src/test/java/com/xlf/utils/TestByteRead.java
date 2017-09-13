package com.xlf.utils;

import com.xlf.utils.annotate.ByteReadField;
import com.xlf.utils.annotate.ByteReadService;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description:
 */
@ByteReadService(path = "http://www.baidu.com")
public class TestByteRead {
    @ByteReadField(start = 1,end = 3)
    private String a;
    @ByteReadField(start = 4,end = 4)
    private int b;
    @ByteReadField(start = 1,end = 1)
    private char c;
    @ByteReadField(start = 4,end = 5)
    private long d;
    private TestByteRead2 testByteRead2;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public long getD() {
        return d;
    }

    public void setD(long d) {
        this.d = d;
    }

    public TestByteRead2 getTestByteRead2() {
        return testByteRead2;
    }

    public void setTestByteRead2(TestByteRead2 testByteRead2) {
        this.testByteRead2 = testByteRead2;
    }

    @Override
    public String toString() {
        return "TestByteRead{" +
                "a='" + a + '\'' +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", testByteRead2=" + testByteRead2 +
                '}';
    }
}
