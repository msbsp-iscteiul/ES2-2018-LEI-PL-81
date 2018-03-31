package pt.iscte.es2.client_jar_loader;

import java.security.*;

public class SecurityPolicy extends Policy {

        /**
         * Get permissions for classes depending on the domain
         *
         * @param domain class domain
         * @return class permission
         */
        @Override
        public PermissionCollection getPermissions(ProtectionDomain domain) {
                if (isPlugin(domain)) {
                        return pluginPermissions();
                }
                return applicationPermissions();
        }

        private boolean isPlugin(ProtectionDomain domain) {
                return domain.getClassLoader() instanceof SecureClientClassLoader;
        }

        /**
         * Give limited permissions to client jar classes
         *
         * @return plugin class permissions
         */
        private PermissionCollection pluginPermissions() {
                return new Permissions(); // No permissions
        }

        /**
         * Give all permissions if application class
         *
         * @return application class permissions
         */
        private PermissionCollection applicationPermissions() {
                final Permissions permissions = new Permissions();
                permissions.add(new AllPermission());
                return permissions;
        }
}
