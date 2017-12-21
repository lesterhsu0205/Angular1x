package com.lester.core.viewModel;

import java.io.Serializable;

public class Option<V,T> implements Serializable{
	private static final long serialVersionUID = 2634142554364641928L;
	
	private V value;
	private T text;
	
	public Option(V value, T text) {
		this.value = value;
		this.text = text;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public T getText() {
		return text;
	}

	public void setText(T text) {
		this.text = text;
	}
}
