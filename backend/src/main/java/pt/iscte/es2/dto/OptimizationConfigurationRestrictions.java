package pt.iscte.es2.dto;

/**
 * DTO of the OptimizationConfigurationRestrictions
 */
public class OptimizationConfigurationRestrictions {

	private Integer id;
	private String name;

	public OptimizationConfigurationRestrictions() {

	}

	public OptimizationConfigurationRestrictions(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
