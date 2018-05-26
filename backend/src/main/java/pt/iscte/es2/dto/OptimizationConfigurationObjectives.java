package pt.iscte.es2.dto;

/**
 * DTO of OptimizationConfigurationObjectives
 *
 * Contains the name of the Objective
 */
public class OptimizationConfigurationObjectives {

	private String name;

	public OptimizationConfigurationObjectives() {

	}

	public OptimizationConfigurationObjectives(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
