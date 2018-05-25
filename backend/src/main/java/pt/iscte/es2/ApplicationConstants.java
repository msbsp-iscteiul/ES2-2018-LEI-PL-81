package pt.iscte.es2;

/**
 * Application Constants to be used in the Application
 */
public class ApplicationConstants {
	/**
	 * Root package for the application. Used for Spring annotation configuration, etc.
	 */
	public static final String APPLICATION_ROOT_PACKAGE = "pt.iscte.es2";

	/**
	 * Location of BeanConfig class
	 */
	public static final String ANNOTATION_CONFIG_PACKAGE = "pt.iscte.es2";

	/**
	 * Location of Spring-Data JPA repositories
	 */
	public static final String DAO_PACKAGE = "pt.iscte.es2.dao";

	/**
	 * Root path for the service endpoints
	 */
	public static final String APPLICATION_PATH = "api";
	public static final String OPTIMIZATION_PATH = APPLICATION_PATH + "/optimization";
	public static final String EXECUTION_PATH = "backend/target/";
	public static final String JARS_PATH = EXECUTION_PATH + "jars/";
	public static final String CSV_PATH = EXECUTION_PATH + "csv/";
	public static final String LATEX_PATH = EXECUTION_PATH + "latex/";
	public static final String R_PATH = EXECUTION_PATH + "r/";
}
