package com.xlf.utils.inter;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description: 字节读取接口
 */
public interface ByteReadFunction {
    /**
     * 设置读取路径
     */
    public void setPath(String path);

    /**
     * 读取指定数量
     *
     * @param size 读取文件数量，-1代表读取整个文件
     * @return
     */
    public byte[] read(int size);

    /**
     * 返回剩余数量，-1代表已经读完
     *
     * @return
     */
    public int hasNext();
}
