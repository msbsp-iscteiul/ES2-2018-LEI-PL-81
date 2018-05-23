package pt.iscte.es2.dto;

/**
 * DTO of OptimizationConfigurationRestrictions
 *
 * Restrictions DTO that contains the name
 */
public class OptimizationConfigurationRestrictions {

	private String name;

	public OptimizationConfigurationRestrictions() {

	}

	public OptimizationConfigurationRestrictions(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
