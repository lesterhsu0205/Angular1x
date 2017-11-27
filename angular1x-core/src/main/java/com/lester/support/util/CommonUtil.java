package com.lester.support.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lester.config.SysConst;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Common util.
 */
public final class CommonUtil {

    private final static Logger log = Logger.getLogger(CommonUtil.class);

    private static Base64 base64 = null;

	private static Gson GSON;

	static {
		GSON = new Gson();//thread safe
	}

	/**
	 * object to json, power by gson
	 *
	 * @param object the object
	 * @return string
	 */
	public static String toJson(Object object) {
		return GSON.toJson(object);
	}

	/**
	 * json string to obj, power by gson
	 *
	 * @param <T>     the type parameter
	 * @param jsonStr the json str
	 * @param c       the c
	 * @return t
	 */
	public static<T> T toObject(String jsonStr, Class<T> c) {
		return GSON.fromJson(jsonStr, c);
	}

	/**
	 * json string to obj, power by gson
	 *
	 * @param <T>     the type parameter
	 * @param jsonStr the json str
	 * @param c       the c
	 * @param gson    the gson
	 * @return t
	 */
	public static<T> T toObject(String jsonStr, Class<T> c, Gson gson) {
		GSON = gson;
		return toObject(jsonStr, c);
	}

	/**
	 * 100000 ~ 100,000
	 *
	 * @param input the input
	 * @return string
	 */
	public String toMoney(String input) {
		return NumberFormat.getInstance().format(Long.parseLong(input));
	}

	/**
	 * 回傳 c 的 instance，c 必須要有無參數的公開建構子
	 *
	 * @param <T> the type parameter
	 * @param c   the c
	 * @return t
	 * @throws NoSuchMethodException     the no such method exception
	 * @throws SecurityException         the security exception
	 * @throws InstantiationException    the instantiation exception
	 * @throws IllegalAccessException    the illegal access exception
	 * @throws IllegalArgumentException  the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public static<T> T createClass(Class<T> c) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<T> constructor = c.getDeclaredConstructor();
		constructor.setAccessible(true);
		T bean = constructor.newInstance();
		return bean;
	}

	/**
	 * 轉BigDecimal 保存有小數點的字串
	 *
	 * @param src the src
	 * @return big decimal
	 * @throws ParseException the parse exception
	 */
	public static BigDecimal toBigDecimal(String src) throws ParseException {
		return (BigDecimal) getDecimalFormat().parse(src);
	}

	/**
	 * Action 噴掉時 記錄 log
	 *
	 * @param <T>   the type parameter
	 * @param e     the e
	 * @param clazz the clazz
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
	 *
	 * @param dbColunDatas SQL取回的DB TABLE MAP
	 * @return the list
	 * @author Mark.Huang
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
	 *
	 * @param dataMap the data map
	 * @return the map
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
	 *
	 * @param <T>              the type parameter
	 * @param beanName         the bean name
	 * @param beanNameDataList the bean name data list
	 * @return list
	 * @author Mark.Huang
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
									if (Long.class.isAssignableFrom(read.getReturnType())
											|| long.class.isAssignableFrom(read.getReturnType())) {
										if (value != null) {
											write.invoke(ob, parseLong(value));
										}
									} else if (Integer.class.isAssignableFrom(read.getReturnType())
											|| int.class.isAssignableFrom(read.getReturnType())) {
										if (value != null) {
											write.invoke(ob, parseInteger(value));
										}
									} else {
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

			if (beanList == null) {
				beanList = new ArrayList<T>();
			}

			beanList.add(ob);
		}

		return beanList;
	}

	/**
	 * Parse booelan boolean.
	 *
	 * @param value the value
	 * @return the boolean
	 */
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

	/**
	 * Parse long long.
	 *
	 * @param value the value
	 * @return the long
	 */
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

	/**
	 * Parse integer int.
	 *
	 * @param value the value
	 * @return the int
	 */
	public static int parseInteger(Object value){
		int result = 0;
		if(value.getClass().equals(BigDecimal.class)){
			BigDecimal bd =  (BigDecimal)value ;
			result = bd.intValue();
		} else {
			result = Integer.parseInt(String.valueOf(value));
		}

		return result;
	}

    public static String base64Encode(String source) throws Exception {
        initBase64();
        return base64.encodeToString(source.getBytes("UTF-8"));
    }

    public static String base64Decode(String source) throws Exception {
        initBase64();
        return new String(base64.decode(source));
    }

    public static String callWebService(String path, String method, String contentType,
                                        Map<String, Object> paramMap) {
        try {
            log.info("url : " + path);
            log.info("param : " + JsonUtil.toJson(paramMap));

            // 如果是 get 必須先組字串
            if ("get".equalsIgnoreCase(method)) {
                if (paramMap != null && paramMap.size() > 0) {
                    path += "?";
                    for (String key : paramMap.keySet()) {
                        path += key + "=" + paramMap.get(key) + "&";
                    }
                    path = path.substring(0, path.length() - 1);
                }
            }

            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if ("get".equalsIgnoreCase(method)) {
                conn.setRequestMethod("GET");
                //WXS token ip檢核用
                InetAddress localhost = InetAddress.getLocalHost();
                conn.setRequestProperty("Client_IP", localhost.getHostAddress());
            } else if ("post".equalsIgnoreCase(method) && SysConst.formType.equals(contentType)) {
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", contentType);

                String param = "";
                for (String key : paramMap.keySet()) {

                    param += key + "=" + paramMap.get(key) + "&";
                }
                //清除最後的&
                param = param.substring(0, param.length() - 1);

                OutputStream os = conn.getOutputStream();
                os.write(param.getBytes());
                os.flush();

            } else if ("post".equalsIgnoreCase(method) && SysConst.jsonType.equals(contentType)) {
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", contentType);
                JsonObject json = new JsonObject();

                for (String key : paramMap.keySet()) {
                    json.addProperty(key, paramMap.get(key).toString());
                }

                OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
                os.write(json.toString());
                os.flush();

            } else {
                throw new RuntimeException("http method 需要為 get 或 post");
            }

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));

            String output = "";
            String temp;
            while ((temp = br.readLine()) != null) {
                output += temp;
            }

            conn.disconnect();
            log.info("resp : " + output);
            return output;
        } catch (Exception e) {
            log.error(path + "，API fail : " + e.getMessage(), e);
            return "{}";
        }
    }

    private static void initBase64() {
        if (base64 == null) {
            synchronized (CommonUtil.class) {
                if (base64 == null) {
                    base64 = new Base64();
                }
            }
        }
    }

}
