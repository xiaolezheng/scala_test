package com.lxz.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiaolezheng on 17/8/10.
 */
public class Util {
    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    public static String trim(String source) {
        if (source == null) {
            return "";
        }

        return source.trim();
    }

    public static void main(String[] args){
        String v = " hello ";
        logger.info("{},{}",v,1);
        logger.info("{},{}",Util.trim(v),1);
    }
}
