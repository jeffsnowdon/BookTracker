package com.jeff.booktracker.util;

import java.util.function.Supplier;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanSupplier<T> implements Supplier<T>, ApplicationContextAware {

	private String beanName;
	private ApplicationContext applicationContext;

	public BeanSupplier(String beanName) {
		this.beanName = beanName;
	}

	/**
	 * New reference to the bean with this supplier's beanName.
	 * 
	 * @return bean or null if does not exist
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T get() {
		return (T) applicationContext.getBean(beanName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
