package com.ycci.support.util;

import org.apache.log4j.Logger;

public class LogUtil {
	
	private static Logger logger = null;
	
	private static Logger getLogger(Class<?> clazz) {
		if (logger == null || !logger.getName().equals(clazz.getName())) {
			logger = Logger.getLogger(clazz);
		} 
		return logger;
	}
	
	public static void trace(Class<?> clazz, Object message) {
		getLogger(clazz).trace(message.toString());
	}

	public static void trace(Class<?> clazz, Object message, Throwable te) {
		getLogger(clazz).trace(message.toString(), te);
	}

	public static void debug(Class<?> clazz, Object message) {
		getLogger(clazz).debug(message.toString());
	}

	public static void debug(Class<?> clazz, Object message, Throwable te) {
		getLogger(clazz).debug(message.toString(), te);
	}

	public static void info(Class<?> clazz, Object message) {
		getLogger(clazz).info(message.toString());
	}

	public static void info(Class<?> clazz, Object message, Throwable te) {
		getLogger(clazz).info(message.toString(), te);
	}

	public static void warn(Class<?> clazz, Object message){
		getLogger(clazz).warn(message.toString());
	}
	
	public static void warn(Class<?> clazz, Object message, Throwable te){
		getLogger(clazz).warn(message.toString(), te);
	}
	
	public static void error(Class<?> clazz, String msgCode, Object message) {
		getLogger(clazz).error(msgCode + message.toString());
	}

	public static void error(Class<?> clazz, String msgCode, Object message, Throwable te) {
		getLogger(clazz).error(msgCode + message.toString(), te);
	}
	
	public static void fatal(Class<?> clazz, Object message){
		getLogger(clazz).fatal(message.toString());
	};
	
	public static void fatal(Class<?> clazz, String msgCode, Object message){
		getLogger(clazz).fatal(msgCode + message.toString());
	};
	
	public static void fatal(Class<?> clazz, String msgCode, Object message, Throwable te){
		getLogger(clazz).fatal(msgCode + message.toString(), te);
	};
	
}
