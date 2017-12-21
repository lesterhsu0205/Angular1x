package com.lester.support.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class BeanUtil {

    /**
     * FIXME 多層物件要調整成遞迴
     * <p>
     * 驗證物件裡面各屬性是否皆不是null和空值
     *
     * @param <T> the type parameter
     * @param t   the t
     * @return the boolean
     * @throws Exception the exception
     */
    @Deprecated
    public static <T> boolean isIntactObj(T t) throws Exception {
        if (t != null) {
            Field[] fields = t.getClass().getDeclaredFields();

            for (Field f : fields) {
                f.setAccessible(true);
                Object obj = f.get(t);
                if (obj == null) {
                    return false;
                }

                if (StringUtils.isBlank(obj.toString())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
