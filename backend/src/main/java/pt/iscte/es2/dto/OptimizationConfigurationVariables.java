package pt.iscte.es2.dto;

/**
 * DTO of the OptimizationConfigurationVariables
 */
public class OptimizationConfigurationVariables {

	private Integer id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
