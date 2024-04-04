package com.ms.ioc.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class Container {

	private List<Bean> beans;

	private Map<Class<?>, Function<String, Object>> converters;

	public void init() {
		beans = new ConfigLoader().loadConfig();
		this.converters = new HashMap<>();
		registerConverters();
	}

	private void registerConverters() {
		converters.put(int.class, Integer::parseInt);
		converters.put(float.class, Float::parseFloat);
		converters.put(double.class, Double::valueOf);
		converters.put(long.class, Long::valueOf);
		converters.put(boolean.class, Boolean::valueOf);
		converters.put(byte.class, Byte::valueOf);
		converters.put(char.class, c -> c);
		converters.put(String.class, t -> t);
	}

	public <T> T resolve(Class<T> clazz) throws IOCException {
		Optional<Bean> maybeBean = beans.stream()
				.filter(bean -> bean.getType().equals(clazz.getCanonicalName()))
				.findFirst();
		if(maybeBean.isPresent()){
			Bean bean = maybeBean.get();
			try {
				Class<?> beanClass = Class.forName(bean.getMapTo());
				Optional<Constructor<?>> longestConstructor = Arrays.stream(beanClass.getConstructors())
						.max(Comparator.comparing(Constructor::getParameterCount));
				Optional<?> object = longestConstructor
						.map(constructor -> {
							Class<?>[] parameterTypes = constructor.getParameterTypes();
							Object[] args = new Object[parameterTypes.length];
							int i = 0;
							for (Class<?> parameterType : parameterTypes) {
								if (parameterType.isPrimitive() || parameterType.isAssignableFrom(String.class)) {
									args[i] = converters.get(parameterType)
											.apply(bean.getConstructorArgs().getArguments().get(i).getValue());
								} else {
									try {
										args[i] = resolve(parameterType);
									} catch (IOCException e) {
										throw new RuntimeException(e);
									}
								}
								i++;
							}
							try {
								return constructor.newInstance(args);
							} catch (InstantiationException | IllegalAccessException |
							         InvocationTargetException e) {
								throw new RuntimeException(e);
							}
						});
				return (T) object.orElse(null);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
}
