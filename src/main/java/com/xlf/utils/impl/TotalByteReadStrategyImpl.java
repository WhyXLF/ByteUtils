package com.xlf.utils.impl;

import com.google.common.collect.Lists;
import com.xlf.utils.ByteCharUtils;
import com.xlf.utils.ByteReadStrategy;
import com.xlf.utils.annotate.ByteReadField;
import com.xlf.utils.annotate.ByteReadList;
import com.xlf.utils.inter.ByteReadFunction;
import jodd.bean.BeanUtil;
import sun.tools.java.ClassType;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public void read(Object target) throws IllegalAccessException, InstantiationException {
        int size;
        //读取总数
        byte[] bytes = new byte[0];
        if ((size = byteReadFunction.hasNext()) > 0) {
            bytes = byteReadFunction.read(size);
        }
        parseObject(target, bytes);

    }

    /**
     * 解析单个对象
     *
     * @param object
     * @param bytes
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void parseObject(Object object, byte[] bytes) throws InstantiationException, IllegalAccessException {
        Class<?> objClass = object.getClass();
        List<Field> fields = getAllFields(objClass);
        for (Field field : fields) {
            checkAndSetField(object, field, bytes);
        }
    }

    /**
     * 解析list对象
     * @param field
     * @param bytes
     * @return
     */
    private List parseList(Field field, byte[] bytes) {
        ByteReadList annotation = field.getAnnotation(ByteReadList.class);
        //没有注解则直接结束
        if (annotation == null) {
            return Lists.newArrayList();
        }
        int start = annotation.start();
        int end = annotation.end();
        int part = annotation.part();
        int partSize = (end - start + 1) / part;
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericType;
            //得到泛型里的class类型对象
            Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
            int index = start;

            if (genericClazz.equals(String.class)) {
                List<String> list = Lists.newArrayList();
                for (int i = 0; i < part; i++) {
                    list.add(new String(ByteCharUtils.getChars(getBytes(bytes, index, index + partSize - 1))));
                    index = index + partSize;
                }
                return list;
            } else if (genericClazz.equals(Integer.class)) {
                List<Integer> list = Lists.newArrayList();
                for (int i = 0; i < part; i++) {
                    String tmpStr = new String(ByteCharUtils.getChars(getBytes(bytes, index, index + partSize - 1)));
                    int intResult = Integer.parseInt(tmpStr);
                    list.add(intResult);
                    index = index + partSize;
                }
                return list;
            } else if (genericClazz.equals(Boolean.class)) {
                List<Boolean> list = Lists.newArrayList();
                for (int i = 0; i < part; i++) {
                    String tmpStr = new String(ByteCharUtils.getChars(getBytes(bytes, index, index + partSize - 1)));
                    boolean booleanResult = Boolean.parseBoolean(tmpStr);
                    list.add(booleanResult);
                    index = index + partSize;
                }
                return list;
            } else if (genericClazz.equals(Character.class)) {
                List<Character> list = Lists.newArrayList();
                for (int i = 0; i < part; i++) {
                    String tmpStr = new String(ByteCharUtils.getChars(getBytes(bytes, index, index + partSize - 1)));
                    char charResult = tmpStr.charAt(0);
                    list.add(charResult);
                    index = index + partSize;
                }
                return list;
            } else if (genericClazz.equals(Double.class)) {
                List<Double> list = Lists.newArrayList();
                for (int i = 0; i < part; i++) {
                    String tmpStr = new String(ByteCharUtils.getChars(getBytes(bytes, index, index + partSize - 1)));
                    double doubleResult = Double.parseDouble(tmpStr);
                    list.add(doubleResult);
                    index = index + partSize;
                }
                return list;
            } else if (genericClazz.equals(Long.class)) {
                List<Long> list = Lists.newArrayList();
                for (int i = 0; i < part; i++) {
                    String tmpStr = new String(ByteCharUtils.getChars(getBytes(bytes, index, index + partSize - 1)));
                    long longResult = Long.parseLong(tmpStr);
                    list.add(longResult);
                    index = index + partSize;
                }
                return list;
            } else {
                try {
                    List<Object> list = Lists.newArrayList();
                    for (int i = 0; i < part; i++) {
                        Object o = genericClazz.newInstance();
                        byte[] innerBytes = getBytes(bytes, index, index + partSize - 1);
                        parseObject(o, innerBytes);
                        list.add(o);
                        index = index + partSize;
                    }
                    return list;
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return Lists.newArrayList();
    }


    private void checkAndSetField(Object obj, Field field, byte[] bytes) throws IllegalAccessException, InstantiationException {
        Class<?> fieldType = field.getType();
        String fieldName = field.getName();
        if (fieldType.equals(Integer.class) || fieldType == int.class) {
            intProperty(obj, field, bytes);
        } else if (fieldType.equals(Long.class) || fieldType == long.class) {
            longProperty(obj, field, bytes);
        } else if (fieldType.equals(Boolean.class) || fieldType == boolean.class) {
            booleanProperty(obj, field, bytes);
        } else if (fieldType.equals(Character.class) || fieldType == char.class) {
            charProperty(obj, field, bytes);
        } else if (fieldType.equals(String.class)) {
            stringProperty(obj, field, bytes);
        } else if (fieldType.equals(Double.class) || fieldType == double.class) {

        } else if (fieldType.getName().startsWith("com.xlf")) {
            Object o = fieldType.newInstance();
            parseObject(o, bytes);
            BeanUtil.pojo.setProperty(obj, fieldName, o);
        } else if (fieldType.equals(List.class)) {
            BeanUtil.pojo.setProperty(obj, fieldName, parseList(field, bytes));
        } else if (fieldType.equals(Map.class)) {
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                throw new UnsupportedOperationException("暂时不支持对Map类型处理！");
            }
        }
    }


    /**
     * 设置string值
     *
     * @param obj
     * @param field
     * @param bytes
     */
    private void stringProperty(Object obj, Field field, byte[] bytes) {
        ByteReadField annotation = field.getAnnotation(ByteReadField.class);
        if (annotation != null) {
            int start = annotation.start();
            int end = annotation.end();
            String resultString = new String(ByteCharUtils.getChars(getBytes(bytes, start, end)));
            BeanUtil.pojo.setProperty(obj, field.getName(), resultString);
        }
    }

    /**
     * 设置int值
     *
     * @param obj
     * @param field
     * @param bytes
     */
    private void intProperty(Object obj, Field field, byte[] bytes) {
        ByteReadField annotation = field.getAnnotation(ByteReadField.class);
        int start = annotation.start();
        int end = annotation.end();
        String tmp = new String(ByteCharUtils.getChars(getBytes(bytes, start, end)));
        int resultInt = Integer.parseInt(tmp);
        BeanUtil.pojo.setProperty(obj, field.getName(), resultInt);
    }

    /**
     * 设置long值
     *
     * @param obj
     * @param field
     * @param bytes
     */
    private void longProperty(Object obj, Field field, byte[] bytes) {
        ByteReadField annotation = field.getAnnotation(ByteReadField.class);
        int start = annotation.start();
        int end = annotation.end();
        String tmp = new String(ByteCharUtils.getChars(getBytes(bytes, start, end)));
        long resultLong = Long.parseLong(tmp);
        BeanUtil.pojo.setProperty(obj, field.getName(), resultLong);
    }

    /**
     * 设置double值
     *
     * @param obj
     * @param field
     * @param bytes
     */
    private void doubleProperty(Object obj, Field field, byte[] bytes) {
        ByteReadField annotation = field.getAnnotation(ByteReadField.class);
        int start = annotation.start();
        int end = annotation.end();
        String tmp = new String(ByteCharUtils.getChars(getBytes(bytes, start, end)));
        double resultDouble = Double.parseDouble(tmp);
        BeanUtil.pojo.setProperty(obj, field.getName(), resultDouble);
    }

    /**
     * 设置boolean值
     *
     * @param obj
     * @param field
     * @param bytes
     */
    private void booleanProperty(Object obj, Field field, byte[] bytes) {
        ByteReadField annotation = field.getAnnotation(ByteReadField.class);
        int start = annotation.start();
        int end = annotation.end();
        String tmp = new String(ByteCharUtils.getChars(getBytes(bytes, start, end)));
        boolean resultBoolean = Boolean.parseBoolean(tmp);
        BeanUtil.pojo.setProperty(obj, field.getName(), resultBoolean);
    }

    /**
     * 设置char值
     *
     * @param obj
     * @param field
     * @param bytes
     */
    private void charProperty(Object obj, Field field, byte[] bytes) {
        ByteReadField annotation = field.getAnnotation(ByteReadField.class);
        int start = annotation.start();
        int end = annotation.end();
        char[] chars = ByteCharUtils.getChars(getBytes(bytes, start, end));
        char resultChar = chars[0];
        BeanUtil.pojo.setProperty(obj, field.getName(), resultChar);
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
        for (int i = start - 1; i < end; i++) {
            resultByte[index++] = bytes[i];
        }
        return resultByte;
    }

    private List<Field> getAllFields(Class<?> objClass) {
        List<Field> fields = Lists.newArrayList();
        for (Class<?> clazz = objClass; clazz != Object.class; clazz = clazz.getSuperclass()) {
            if (!clazz.getName().equals(Object.class.getName())) {
                Field[] declaredFields = clazz.getDeclaredFields();
                fields.addAll(Arrays.asList(declaredFields));
            }
        }
        return fields;
    }

}
