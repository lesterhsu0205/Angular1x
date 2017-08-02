package com.ycci.support.handler;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

public class TransactionManager {
	
	private DataSourceTransactionManager transactionManager;
	private TransactionStatus status;
	private boolean isRollback;
	private boolean isCommit;
	
	public TransactionManager(DataSourceTransactionManager transactionManager, TransactionStatus status){
		this.transactionManager = transactionManager;
		this.status = status;
	}

	public void rollback(){
		transactionManager.rollback(status);
		isRollback = true;
	}
	
	public void commit(){
        transactionManager.commit(status);
        isCommit = true;
	}

	protected boolean isRollback() {
		return isRollback;
	}

	protected boolean isCommit() {
		return isCommit;
	}
	
}
