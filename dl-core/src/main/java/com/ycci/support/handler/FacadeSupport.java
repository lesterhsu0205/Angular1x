package com.ycci.support.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@Transactional
public class FacadeSupport {
	
	@Autowired
	private DataSourceTransactionManager transactionManager;

	protected <T> T doTransaction(ServiceClosure.isRetrun<T> closure) {
		T result = null;
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		TransactionManager txManager = new TransactionManager(transactionManager, status);
		try {

			result = closure.run(txManager);
			if(!txManager.isCommit()){
				txManager.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(!txManager.isRollback()){
				txManager.rollback();
			}
		}
		return result;
	}

	protected void doTransaction(ServiceClosure.isNotReturn closure) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		TransactionManager txManager = new TransactionManager(transactionManager, status);
		try {

			closure.run(txManager);
			if(!txManager.isCommit()){
				txManager.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(!txManager.isRollback()){
				txManager.rollback();
			}
		}
	}
	
	
}
