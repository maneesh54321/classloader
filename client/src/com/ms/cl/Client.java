package com.ms.cl;

import java.lang.reflect.InvocationTargetException;

public class Client {

	public static void main(String[] args) {
		DatabaseClassLoader dbClassLoader =
				new DatabaseClassLoader("jdbc:postgresql://localhost/postgres?user=maneesh&password=");
		try {
			Class<?> aClass = Class.forName("com.ms.cl.Quote", true, dbClassLoader);
			IQuote quote = (IQuote) aClass.getConstructor().newInstance();
			quote.say();
		} catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
		         IllegalAccessException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
}
