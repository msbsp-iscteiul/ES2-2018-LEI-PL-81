package pt.iscte.es2.dto;

/**
 * DTO of OptimizationConfigurationVariables
 *
 * Variables DTO that contains the name
 */
public class OptimizationConfigurationVariables {

	private String name;

	public OptimizationConfigurationVariables() {

	}

	public OptimizationConfigurationVariables(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
