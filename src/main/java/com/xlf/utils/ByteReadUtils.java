package com.xlf.utils;

import com.xlf.utils.annotate.ByteReadService;
import com.xlf.utils.impl.FileByteReadFunctionImpl;
import com.xlf.utils.impl.TotalByteReadStrategyImpl;
import com.xlf.utils.inter.ByteReadFunction;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description:
 */
public class ByteReadUtils {
    private ByteReadFunction byteReadFunction;

    public ByteReadUtils() {
        byteReadFunction = new FileByteReadFunctionImpl();
    }

    public ByteReadUtils(ByteReadFunction byteReadFunction) {
        this.byteReadFunction = byteReadFunction;
    }

    /**
     * 调用target对象进行属性注入
     *
     * @param target
     * @throws Exception
     */
    public void invoke(Object target) throws Exception {
        ByteReadService byteReadService = target.getClass().getAnnotation(ByteReadService.class);
        //设置请求路径
        String path = byteReadService.path();
        byteReadFunction.setPath(path);
        //设置读取策略
        ByteReadStrategy byteReadStrategy = new TotalByteReadStrategyImpl(byteReadFunction);
        byteReadStrategy.read(target);
    }
}
