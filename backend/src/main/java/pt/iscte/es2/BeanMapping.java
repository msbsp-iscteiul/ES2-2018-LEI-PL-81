package pt.iscte.es2;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.stereotype.Component;
import pt.iscte.es2.dto.OptimizationConfiguration;
import pt.iscte.es2.dto.OptimizationJobExecutions;
import pt.iscte.es2.jpa.OptimizationConfigurationEntity;
import pt.iscte.es2.jpa.OptimizationJobExecutionsEntity;

@Component
public class BeanMapping extends BeanMappingBuilder {
	@Override
	protected void configure() {
		mapping(OptimizationConfigurationEntity.class, OptimizationConfiguration.class, TypeMappingOptions.mapNull(false))
			.fields("optimizationConfigurationVariables", "variables")
			.fields("optimizationConfigurationObjectives", "objectives")
			.fields("optimizationConfigurationRestrictions", "restrictions")
			.fields("optimizationConfigurationAlgorithms", "algorithms")
			.fields("optimizationConfigurationUserSolutions", "userSolutions")
			.fields("optimizationJobExecutions", "executions");

		mapping(OptimizationJobExecutionsEntity.class, OptimizationJobExecutions.class, TypeMappingOptions.mapNull(false))
			.fields("optimizationJobSolutions", "solutions");
	}
}
