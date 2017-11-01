package com.lester.support.util;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final public class CommonUtil {
	private static Gson GSON;
	
	static {
		GSON = new Gson();//thread safe
	}
	
	/**
	 * object to json, power by gson
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		return GSON.toJson(object); 
	}
	
	/**
	 * json string to obj, power by gson
	 * @param jsonStr
	 * @param c
	 * @return
	 */
	public static<T> T toObject(String jsonStr, Class<T> c) {
		return GSON.fromJson(jsonStr, c);
	}
	
	/**
	 * json string to obj, power by gson
	 * @param jsonStr
	 * @param c
	 * @return
	 */
	public static<T> T toObject(String jsonStr, Class<T> c, Gson gson) {
		GSON = gson;
		return toObject(jsonStr, c);
	}
	
	/**
	 * 100000 -> 100,000
	 * @param input
	 * @return
	 */
	public String toMoney(String input) {
		return NumberFormat.getInstance().format(Long.parseLong(input));
	}
	
	/**
	 * 回傳 c 的 instance，c 必須要有無參數的公開建構子
	 * @param c
	 * @return
	 */
	public static<T> T createClass(Class<T> c) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<T> constructor = c.getDeclaredConstructor();
		constructor.setAccessible(true);
		T bean = constructor.newInstance();
		return bean;
	}
	
	/**
	 * 轉BigDecimal 保存有小數點的字串
	 * @param src
	 * @return
	 * @throws ParseException
	 */
	public static BigDecimal toBigDecimal(String src) throws ParseException {
		return (BigDecimal) getDecimalFormat().parse(src);
	}
	
	/** Action 噴掉時 記錄 log
	 * @param <T>
	 * @param e
	 */
	public static <T> void doFailLog(Exception e, Class<T> clazz) {
		Log logger = LogFactory.getLog(clazz);
		logger.info(e.getStackTrace()[0].getMethodName(), e);
		logger.error(e.getMessage(), e);
	}
	
	private static long copy(InputStream source, OutputStream sink) throws IOException {
		long nread = 0L;
		byte[] buf = new byte[8192];
		int n;
		while ((n = source.read(buf)) > 0) {
			sink.write(buf, 0, n);
			nread += n;
		}
		return nread;
	}
	
	/**
	 * non thread safe，之後記憶體吃太兇再丟到threadlocal
	 * @return
	 */
	private static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
		return decimalFormat;
	}
	
	/**
	 * map key是colunName 轉 駝峰命名
	 * @author Mark.Huang
	 * @param dbColunDatas SQL取回的DB TABLE MAP
	 */
	public static List<Map<String, Object>> colunNameToBeanName(List<Map<String, Object>> dbColunDatas){
		if(dbColunDatas == null){
			return null;
		}
		//要轉成 bean field名稱 欄位值 所需list
		List<Map<String, Object>> formatBeanNameDataList = new ArrayList<Map<String,Object>>();
		
		for(Map<String, Object> dataMap:dbColunDatas){
			Map<String, Object> beanMap = columnNameToBeanName(dataMap);
			formatBeanNameDataList.add(beanMap);
		}
		
		return formatBeanNameDataList;
	}
	
	/**
	 * map key是colunName 轉 駝峰命名
	 * @author Mark.Huang
	 */
	public static Map<String, Object> columnNameToBeanName(Map<String, Object> dataMap) {
		if(dataMap == null){
			return null;
		}
		Map<String, Object> beanMap = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
			//keyName 轉換 欄位名稱 變成 field名
			StringBuilder fieldName = new StringBuilder();
			String colunName = String.valueOf(entry.getKey()).toLowerCase();
			String[] names = colunName.split("_");
			boolean firstTimeFlag = true;
			for(String name :names){
				if(firstTimeFlag){
					fieldName = fieldName.append(name);
					firstTimeFlag = false;
				}else{
					fieldName = fieldName.append(name.substring(0,1).toUpperCase());
					if(name.length() >= 2){
						fieldName = fieldName.append(name.substring(1,(name.length())));
					}	
				}									
			}
			beanMap.put(fieldName.toString(), entry.getValue());
		}
		return beanMap;
	}
	
	/**
	 * map 轉 bean 
	 * map key要 = bean field name
	 * @author Mark.Huang
	 * @param <T>
	 * @param beanName
	 * @param beanNameDataList
	 * @return
	 */
	public static <T> List<T> mapToBean(Class<T> beanName, List<Map<String, Object>> beanNameDataList){
		if(beanNameDataList == null || beanName == null){
			return null;
		}
		List<T> beanList = null;
		
		for(Map<?, ?> datas:beanNameDataList){
			T ob = null;
				try {
					ob = beanName.newInstance();
					BeanInfo info = Introspector.getBeanInfo(beanName);
					PropertyDescriptor[] pds = info.getPropertyDescriptors();
					
					if (pds != null && pds.length > 0) {
					    for (PropertyDescriptor p : pds) {
					        try {
								// 取得屬性的名稱, getter, setter
								String pName = p.getName();
								Method read = p.getReadMethod();
								Method write = p.getWriteMethod();
								Object value = null;

								// 如果 getter 和 setter 都存在再處理
								if (read != null && write != null) {
 
								    String k = pName;
								    if (k != null) {
								        // 從 map 取出值, 並呼叫 setter 把值寫入
										value = datas.get(k);

//					                if (Boolean.class.isAssignableFrom(read.getReturnType()) 
//					                        || boolean.class.isAssignableFrom(read.getReturnType())) {
//					                    if (value != null) {
//					                        write.invoke(ob, parseBooelan(value));
//					                    }
//					                }else 
								        if(Long.class.isAssignableFrom(read.getReturnType()) 
								                || long.class.isAssignableFrom(read.getReturnType())){
								        	if (value != null) {
								                write.invoke(ob, parseLong(value));
								            }
								        }else if(Integer.class.isAssignableFrom(read.getReturnType()) 
								                || int.class.isAssignableFrom(read.getReturnType())){
								        	if(value != null){
								        		write.invoke(ob, parseInteger(value));
								        	}
								        }else {
								            write.invoke(ob, value);
								        }
								    }
								}
							} catch (Exception e) {
								continue;
							}
					    }
					}
				} catch (Exception e) {
					
				}
             
				if(beanList == null){
					beanList = new ArrayList<T>();
				}
				
				beanList.add(ob);
		}
		
		return beanList;
	}
	
	public static Boolean parseBooelan(Object value){
		Boolean result = false;
		Long v = parseLong(value);
		if(v != null){
			if(v.equals(1L)){
				result = true;
			}
		}
		
		return result;
	}
	
	public static Long parseLong(Object value){
		Long result = null;
		if(value.getClass().equals(BigDecimal.class)){
			BigDecimal bd =  (BigDecimal)value ;
			result = bd.longValue();
		}else{
			result = Long.parseLong(String.valueOf(value));
		}
		
		return result;
	}
	
	public static int parseInteger(Object value){
		int result = 0;
		if(value.getClass().equals(BigDecimal.class)){
			BigDecimal bd =  (BigDecimal)value ;
			result = bd.intValue();
		}else{
			result =Integer.parseInt(String.valueOf(value)); 
		}
		
		return result;
	}
	
}
