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

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
