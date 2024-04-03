import com.ms.cl.IQuote;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

	public static void main(String[] args) {
		URLClassLoader urlClassLoader = null;
		try {
			URI uri = new URI("file:///Users/maneesh/Work/classloader-tut/implementations/out/artifacts/implementations/implementations.jar");
			urlClassLoader = new URLClassLoader(new URL[]{uri.toURL()});
			Class<IQuote> clazz = (Class<IQuote>) urlClassLoader.loadClass("com.ms.cl.Quote");
			IQuote obj = clazz.getConstructor().newInstance();
			obj.say();
		} catch (URISyntaxException | MalformedURLException | ClassNotFoundException |
		         InvocationTargetException | InstantiationException | IllegalAccessException |
		         NoSuchMethodException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				assert urlClassLoader != null;
				urlClassLoader.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}