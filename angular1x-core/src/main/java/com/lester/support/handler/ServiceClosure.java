package com.lester.support.handler;

public interface ServiceClosure {
	
	public interface isRetrun<T> {

		T run(TransactionManager txManager) throws Exception;
		
	}
	
	public interface isNotReturn {

		void run(TransactionManager txManager) throws Exception;
		
	}
	
}

