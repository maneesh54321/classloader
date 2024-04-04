package com.ms.ioc.container;

import java.util.List;

public class Constructor {
	private List<ConstructorArg> arguments;

	public Constructor() {
	}

	public List<ConstructorArg> getArguments() {
		return arguments;
	}

	public void setArguments(List<ConstructorArg> arguments) {
		this.arguments = arguments;
	}
}
