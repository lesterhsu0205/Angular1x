package com.lester.support.model;

import java.io.Serializable;

/**
 * Created by lesterhsu on 2017/5/4.
 */
public class TransactionResult<T> implements Serializable {

    private T result;

    private boolean isCommitted;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isCommitted() {
        return isCommitted;
    }

    public void setCommitted(boolean isCommitted) {
        this.isCommitted = isCommitted;
    }

}
