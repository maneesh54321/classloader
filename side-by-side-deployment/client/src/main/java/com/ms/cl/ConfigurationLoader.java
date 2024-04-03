package com.ms.cl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {
	public static Properties loadConfigs(String configLocation) throws IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(configLocation));
		return properties;
	}
}
