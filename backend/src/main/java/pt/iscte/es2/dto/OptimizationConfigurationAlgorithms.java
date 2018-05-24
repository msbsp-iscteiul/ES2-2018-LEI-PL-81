package pt.iscte.es2.dto;

/**
 * DTO of OptimizationConfigurationAlgorithms
 *
 * Algorithm DTO that contains the name
 */
public class OptimizationConfigurationAlgorithms {

	private String name;

	public OptimizationConfigurationAlgorithms() {

	}

	public OptimizationConfigurationAlgorithms(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
