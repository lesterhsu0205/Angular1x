package com.lester.support.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lesterhs on 2017/5/5.
 */
public class ClosureResult<T, D> implements Serializable {

    private T result;                       // 自定義回傳值
    private List<D> datas;                  // select 時可用

    private List<PreparedStatement> pss;    // 增刪改的 PreparedStatement 要回收用

    public ClosureResult() {
    }

    public ClosureResult(List<PreparedStatement> pss) {
        this.pss = pss;
    }

    public ClosureResult(PreparedStatement... pss) {
        List<PreparedStatement> list = new ArrayList<PreparedStatement>();
        for (PreparedStatement ps : pss) {
            list.add(ps);
        }
        this.pss = list;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<PreparedStatement> getPss() {
        return pss;
    }

    public List<D> getDatas() {
        return datas;
    }

    public void setDatas(List<D> datas) {
        this.datas = datas;
    }
}
