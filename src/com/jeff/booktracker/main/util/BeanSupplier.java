package com.jeff.booktracker.main.util;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanSupplier<T> implements Supplier<List<T>>, ApplicationContextAware {

	private Class<T> classType;
	private ApplicationContext applicationContext;

	public BeanSupplier(Class<T> classType) {
		this.classType = classType;
	}

	@Override
	public List<T> get() {
		Map<String, T> map = applicationContext.getBeansOfType(classType);
		return map.values().stream().collect(Collectors.toList());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
