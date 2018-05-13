package pt.iscte.es2.dto;

/**
 * DTO of the OptimizationConfigurationAlgorithms
 */
public class OptimizationConfigurationAlgorithms {

	private Integer id;
	private String name;

	public OptimizationConfigurationAlgorithms() {

	}

	public OptimizationConfigurationAlgorithms(String name) {
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
