package com.xlf.utils.impl;

import com.xlf.utils.inter.ByteReadFunction;
import com.xlf.utils.props.PropertyUtils;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description: 测试使用方法
 */
public class FileByteReadFunctionImpl implements ByteReadFunction {
    private byte[] bytes;
    private int index;

    @Override
    public void setPath(String path) {
        try {
            URL resource = FileByteReadFunctionImpl.class.getClassLoader().getResource(path);
            if (resource != null) {
                StringBuilder stringBuilder = new StringBuilder();
                List<String> lines = Files.readAllLines(Paths.get(resource.getPath()), StandardCharsets.UTF_8);
                for (String line : lines) {
                    stringBuilder.append(line);
                }
                bytes = stringBuilder.toString().getBytes();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] read(int size) {
        byte[] bytes = getBytes(this.bytes, index, index + size);
        index += size;
        return bytes;
    }

    /**
     * 字节读取
     *
     * @param bytes
     * @param start
     * @param end
     * @return
     */
    private byte[] getBytes(byte[] bytes, int start, int end) {
        byte[] resultByte = new byte[end - start + 1];
        int index = 0;
        for (int i = start; i < end; i++) {
            resultByte[index++] = bytes[i];
        }
        return resultByte;
    }

    @Override
    public int hasNext() {
        return bytes.length - index;
    }
}
