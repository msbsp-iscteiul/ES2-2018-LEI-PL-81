package pt.iscte.es2.client_jar_loader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Alternative loader to limit the client's jar operations
 */
public class SecureClientClassLoader extends URLClassLoader {
	/**
	 * Constructor
	 * @param jarFileUrl the loader
	 */
	public SecureClientClassLoader(URL jarFileUrl) {
		super(new URL[]{jarFileUrl});
	}
}
