package com.ms.cl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

public class Client {

	public static void main(String[] args) throws IOException {
		Properties properties = ConfigurationLoader.loadConfigs(args[0]);
		URLClassLoader ucl = null;
		try {
			String type = properties.getProperty("factory.type");
			String location = properties.getProperty("factory.location");
			URI uri = new URI(location);
			ucl = new URLClassLoader(new URL[]{uri.toURL()});
			Class<?> clazz = Class.forName(type, true, ucl);
			ICameraFactory cameraFactory = (ICameraFactory) clazz.getConstructor().newInstance();
			ICamera camera = cameraFactory.createCamera();
			camera.takePhoto();
		} catch (URISyntaxException | ClassNotFoundException | NoSuchMethodException |
		         InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		} finally {
			assert ucl != null;
			ucl.close();
		}
	}
}
