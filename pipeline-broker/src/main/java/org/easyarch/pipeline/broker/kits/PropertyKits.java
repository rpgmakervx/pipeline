package org.easyarch.pipeline.broker.kits;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by xingtianyu on 17-4-20
 * 下午1:41
 * description:
 */

public class PropertyKits {


    public static Properties loadProperties(String configPath){
        Properties properties = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(configPath);
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

}
