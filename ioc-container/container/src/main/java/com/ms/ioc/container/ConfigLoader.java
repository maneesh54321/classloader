package com.ms.ioc.container;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class ConfigLoader {

	private final ObjectMapper objectMapper;

	public ConfigLoader() {
		objectMapper = new ObjectMapper();
	}

	public List<Bean> loadConfig(){
		try {
			return objectMapper.readValue(
					ConfigLoader.class.getResourceAsStream("/config.json"),
					new TypeReference<>() {
					}
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
