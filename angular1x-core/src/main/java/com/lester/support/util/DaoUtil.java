package com.lester.support.util;

import com.google.common.collect.Lists;
import com.lester.support.model.ClosureResult;
import com.lester.support.model.DaoClosure;
import com.lester.support.model.TransactionResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

/**
 * Created by lesterhsu on 2017/3/24.
 */
public class DaoUtil {

    private static final Logger log = Logger.getLogger(DaoUtil.class);

    /**
     * JDBC 交易處理 - 交易內批次修改資料
     *
     * @param <T>     自定義 DAO 回傳值
     * @param <D>     query data
     * @param ds      the dataSource
     * @param closure the DAO 動作
     * @return 交易回傳值 transaction result
     */
    public static <T, D> TransactionResult<T>
    doTransaction(DataSource ds, DaoClosure<T, D> closure) throws Exception {
        TransactionResult<T> result = new TransactionResult<>();
        boolean isCommitted = false;
        Connection conn = ds.getConnection();
        conn.setAutoCommit(false);
        ClosureResult<T, D> d = null;
        try {
            d = closure.run(conn, null);
            conn.commit();
            isCommitted = true;
            result.setResult(d.getResult());
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            if (d != null && d.getPss().size() > 0) {
                List<PreparedStatement> pss = d.getPss();
                if (pss != null && pss.size() > 0) {
                    for (PreparedStatement ps : pss) {
                        if (ps != null) {
                            ps.close();
                        }
                    }
                }
            }

            if (conn != null) {
                conn.close();
            }
        }

        result.setCommitted(isCommitted);
        return result;
    }

    /**
     * Hibernate 交易處理 - 交易內單筆修改資料
     *
     * @param <T>            自定義 DAO 回傳值
     * @param <D>            query data
     * @param sessionFactory the session factory
     * @param closure        the DAO 動作
     * @return the transaction result
     */
    public static <T, D> TransactionResult<T>
    doHibernateTx(SessionFactory sessionFactory, DaoClosure<T, D> closure) throws Exception {
        TransactionResult<T> result = new TransactionResult<>();
        ClosureResult<T, D> d = null;
        boolean isCommitted = false;
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            d = closure.run(null, session);
            tx.commit();
            isCommitted = true;
            result.setResult(d.getResult());
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        result.setCommitted(isCommitted);
        return result;
    }

    /**
     * Batch update list.
     *
     * @param <T>     the type parameter
     * @param <I>     the type parameter
     * @param data    the data
     * @param daoImpl the dao
     * @param daoName the dao name
     * @param conn    the conn
     * @return the list
     * @throws Exception the exception
     */
    public static <T, I> List<PreparedStatement> batchUpdate(List<T> data,
                                                             I daoImpl,
                                                             String daoName,
                                                             Connection conn) throws Exception {

        List<PreparedStatement> result = new ArrayList<>();
        if (data != null && data.size() > 0) {
            // 切資料
            List<List<T>> datas = Lists.partition(data, 999);
            // 找方法
            Method m = getMethod(daoImpl, daoName);
            // 執行
            if (m != null) {
                for (List<T> partition : datas) {
                    Object res = m.invoke(daoImpl, partition, conn);
                    result.add((PreparedStatement) res);
                }
            }
        }
        return result;
    }

    /**
     * Select list. 加入多個 condition 會有 bug 暫時不建議使用
     *
     * @param sessionFactory the session factory
     * @param sql            the sql
     * @param paramMap       the param map
     * @param condition      the condition
     * @return the list
     * @throws Exception the exception
     */
    @Deprecated
    public static List<Object[]> select(
            SessionFactory sessionFactory,
            String sql,
            Map<String, Object> paramMap,
            String condition) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query sqlQuery = null;
            if (paramMap != null && paramMap.size() > 0 && StringUtils.isNotBlank(condition)) {
                sql += condition;
                for (String key : paramMap.keySet()) {
                    if (paramMap.get(key) instanceof Collection) {
                        Collection c = (Collection) paramMap.get(key);
                        if (c.size() > 0) {
                            sqlQuery = session.createSQLQuery(sql);
                            sqlQuery.setParameterList(key, (Collection) paramMap.get(key));
                        } else {
                            sql = sql.replace(condition, "");
                            sqlQuery = session.createSQLQuery(sql);
                        }
                    } else if (paramMap.get(key) instanceof String) {
                        sqlQuery = session.createSQLQuery(sql);
                        sqlQuery.setString(key, paramMap.get(key).toString());
                    }
                    // 如果參數還有其他形態這邊必須增加
                }
            } else {
                sqlQuery = session.createSQLQuery(sql);
            }
            return sqlQuery.list();
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 如果 qurey 出只有一筆資料，可用此 method 過濾
     *
     * @param <T>     DAO 客制 result
     * @param <D>     query 出來的資料
     * @param closure the closure
     * @return the unique data
     * @throws Exception the exception
     */
    public static <T, D> D getUniqueData(DaoClosure<T, D> closure) throws Exception {
        ClosureResult<T, D> result = closure.run(null, null);
        List<D> datas = result.getDatas();
        if (datas != null && datas.size() > 0) {
            return datas.get(0);
        }
        return null;
    }

    /**
     * Get hibernate data type type.
     *
     * @param cls the cls
     * @return the type
     */
    public static Type getHibernateDataType(Class cls) {
        Type type = StandardBasicTypes.STRING;
        if ((cls == long.class) || (cls == Long.class)) {
            type = StandardBasicTypes.LONG;
        } else if ((cls == int.class) || (cls == Integer.class)) {
            type = StandardBasicTypes.INTEGER;
        } else if ((cls == char.class) || (cls == Character.class)) {
            type = StandardBasicTypes.CHARACTER;
        } else if ((cls == short.class) || (cls == Short.class)) {
            type = StandardBasicTypes.SHORT;
        } else if ((cls == double.class) || (cls == Double.class)) {
            type = StandardBasicTypes.DOUBLE;
        } else if ((cls == float.class) || (cls == Float.class)) {
            type = StandardBasicTypes.FLOAT;
        } else if ((cls == boolean.class) || (cls == Boolean.class)) {
            type = StandardBasicTypes.BOOLEAN;
        } else if (cls == String.class) {
            type = StandardBasicTypes.STRING;
        } else if (cls == Date.class) {
            type = StandardBasicTypes.DATE;
        }
        return type;
    }

    private static <I> Method getMethod(I impl, String methodName) {
        Method[] ms = impl.getClass().getDeclaredMethods();
        for (Method m : ms) {
            if (methodName.equalsIgnoreCase(m.getName())) {
                return m;
            }
        }
        return null;
    }

}
