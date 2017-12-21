package com.lester.support.model;


import org.hibernate.Session;

import java.sql.Connection;

/**
 * Created by lesterhsu on 2017/5/4.
 *
 * @param <T> 自定義 DAO 回傳值
 * @param <D> query data
 */
public interface DaoClosure<T, D> {
    /**
     * Run closure result.
     *
     * @param conn    connection for jdbc [option]
     * @param session session for hibernate [option]
     * @return the closure result
     * @throws Exception the exception
     */
    ClosureResult<T, D> run(Connection conn, Session session) throws Exception;
}
