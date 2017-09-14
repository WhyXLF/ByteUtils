package com.xlf.utils.props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * author: xiaoliufu
 * date:   2017/9/13.
 * description:
 */
public class PropertyUtils {
    public static Properties read() throws IOException {
        Properties properties = new Properties();
        InputStream resourceAsStream = PropertyUtils.class.getClassLoader().getResourceAsStream("./app.properties");
        properties.load(resourceAsStream);
        resourceAsStream.close();
        return properties;
    }
}
