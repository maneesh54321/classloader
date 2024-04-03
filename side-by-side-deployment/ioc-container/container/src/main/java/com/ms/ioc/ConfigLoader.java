package com.ms.ioc;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class ConfigLoader {

	private ObjectMapper objectMapper;

	public ConfigLoader() {
		objectMapper = new ObjectMapper();
	}

	public List<Bean> loadConfig(){
		try {
			List<Bean> beans = objectMapper.reader().readValue(ConfigLoader.class.getResourceAsStream("config.json"), List.class);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
