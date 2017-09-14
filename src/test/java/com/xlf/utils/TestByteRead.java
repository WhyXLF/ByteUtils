package com.xlf.utils;

import com.xlf.utils.annotate.ByteReadField;
import com.xlf.utils.annotate.ByteReadList;
import com.xlf.utils.annotate.ByteReadService;

import java.util.List;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description:
 */
@ByteReadService(path = "./record1.txt")
public class TestByteRead {
    @ByteReadField(start = 1,end = 3)
    private String a;
    @ByteReadField(start = 4,end = 5)
    private int b;
    @ByteReadField(start = 6,end = 6)
    private char c;
    @ByteReadField(start = 7,end = 9)
    private long d;
    @ByteReadField(start = 10,end = 20)
    private TestByteRead2 testByteRead2;
    @ByteReadList(part = 2,start = 1,end = 20)
    private List<TestByteRead2> listStr;

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

    public List<TestByteRead2> getListStr() {
        return listStr;
    }

    public void setListStr(List<TestByteRead2> listStr) {
        this.listStr = listStr;
    }

    @Override
    public String toString() {
        return "TestByteRead{" +
                "a='" + a + '\'' +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", testByteRead2=" + testByteRead2 +
                ", listStr=" + listStr +
                '}';
    }
}
