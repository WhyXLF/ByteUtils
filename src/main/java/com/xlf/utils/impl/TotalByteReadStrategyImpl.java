package com.xlf.utils.impl;

import com.xlf.utils.ByteCharUtils;
import com.xlf.utils.ByteReadStrategy;
import com.xlf.utils.annotate.ByteReadField;
import com.xlf.utils.inter.ByteReadFunction;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description: 完全读入后注入
 */
public class TotalByteReadStrategyImpl implements ByteReadStrategy {

    private final ByteReadFunction byteReadFunction;

    public TotalByteReadStrategyImpl(ByteReadFunction byteReadFunction) {
        this.byteReadFunction = byteReadFunction;
    }

    @Override
    public void read(Field[] fields, Object target) throws IllegalAccessException {
        int size;
        //读取总数
        byte[] total = new byte[0];
        if ((size = byteReadFunction.hasNext()) > 0) {
            total = byteReadFunction.read(size);
        }
        for (Field field : fields) {
            ByteReadField fieldAnnotation = field.getAnnotation(ByteReadField.class);
            //与文件格式对应从1开始计数
            int start = fieldAnnotation.start() - 1;
            int end = fieldAnnotation.end() - 1;
            if (start < 0 || end > size) {
                throw new IndexOutOfBoundsException("数组不在范围内！start=" + start + ",end=" + end + ",totalSize=" + size);
            }
            int byteSize = end - start;
            byte[] bytes = new byte[byteSize];
            int index = 0;
            for (int i = start; i < end; i++) {
                bytes[index++] = total[i];
            }
            char[] array = ByteCharUtils.getChars(bytes);

            field.setAccessible(true);
            if (field.getGenericType().toString().equals("class java.lang.String")) {
                field.set(target, new String(array));
            } else {
                throw new UnsupportedOperationException("暂时不支持其他数据类型！");
            }
        }
    }
}
