package pt.iscte.es2.client_jar_loader;

import java.net.URL;
import java.net.URLClassLoader;

public class SecureClientClassLoader extends URLClassLoader {
        public SecureClientClassLoader(URL jarFileUrl) {
                super(new URL[]{jarFileUrl});
        }
}
