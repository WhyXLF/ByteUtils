package com.xlf.utils;

import com.xlf.utils.impl.FileByteReadFunctionImpl;
import org.junit.Test;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description: 字节读取测试类
 */
public class ByteReadUtilsTest {
    @Test
    public void invoke() throws Exception {
        ByteReadUtils byteReadUtils = new ByteReadUtils(new FileByteReadFunctionImpl());
        TestByteRead testByteRead = new TestByteRead();
        byteReadUtils.invoke(testByteRead);
        System.out.println(testByteRead.toString());
    }

}