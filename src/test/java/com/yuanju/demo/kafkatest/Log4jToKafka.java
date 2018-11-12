package com.yuanju.demo.kafkatest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;



/**
 * INFO: info User: xuchao Date: 2017/3/17 Version: 1.0 History:
 * <p>
 * 如果有修改过程，请记录
 * </P>
 */

public class Log4jToKafka {
    private static final Logger LOG = Logger.getLogger(Log4jToKafka.class);
    public static void main(String[] args) {
        LOG.setLevel(Level.INFO);
        LOG.debug("这是一条debug级别的日志！");
        LOG.info("这是一条info级别的日志！");
        LOG.error("这是一条error级别的日志！");
        LOG.fatal("这是一条fatal级别的日志！");
    }
}