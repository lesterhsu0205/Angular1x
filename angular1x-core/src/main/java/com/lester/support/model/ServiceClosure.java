package com.lester.support.model;

import com.lester.support.handler.TransactionManager;

public interface ServiceClosure {

	interface isRetrun<T> {

		T run(TransactionManager txManager) throws Exception;
		
	}

	interface isNotReturn {

		void run(TransactionManager txManager) throws Exception;
		
	}
	
}

