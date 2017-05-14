package com.jeff.booktracker.util;

/**
 * Default IProperty implementation.
 */
public class Property<T> implements IProperty<T> {

	private T t;

	public Property() {
	}

	public Property(T t) {
		this.t = t;
	}

	@Override
	public T get() {
		return t;
	}

	@Override
	public void set(T t) {
		this.t = t;
	}

}
