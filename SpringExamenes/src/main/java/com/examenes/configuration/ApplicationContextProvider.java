package com.examenes.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext context;

	public static synchronized ApplicationContext getApplicationContext() {
		return context;
	}

	@Override
	public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;

	}

	/*
	 * Nota: el getter y el setter del contexto deben estar sincronizados. 
	 * Evitará muchos dolores de cabeza especialmente para las pruebas unitarias/de integración
	 * 
	 * */
}