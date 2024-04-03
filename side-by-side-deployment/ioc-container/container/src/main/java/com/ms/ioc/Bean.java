package com.ms.ioc;

public class Bean {
	private String type;

	private String mapTo;

	private Constructor constructorArgs;

	private boolean singleton;

	public Bean() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMapTo() {
		return mapTo;
	}

	public void setMapTo(String mapTo) {
		this.mapTo = mapTo;
	}

	public Constructor getConstructorArgs() {
		return constructorArgs;
	}

	public void setConstructorArgs(Constructor constructorArgs) {
		this.constructorArgs = constructorArgs;
	}

	public boolean isSingleton() {
		return singleton;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}
}
