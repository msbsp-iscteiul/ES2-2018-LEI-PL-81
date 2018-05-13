package pt.iscte.es2.dto;

/**
 * DTO of the OptimizationConfigurationObjectives
 */
public class OptimizationConfigurationObjectives {

	private Integer id;
	private String name;

	public OptimizationConfigurationObjectives() {

	}

	public OptimizationConfigurationObjectives(String name) {
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
