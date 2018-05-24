package pt.iscte.es2.optimization_job_runner.jobs;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OptimizationConfigurationDeserializer extends StdDeserializer<OptimizationConfiguration> {

	public OptimizationConfigurationDeserializer() {
		this(null);
	}

	protected OptimizationConfigurationDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public OptimizationConfiguration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);
		JsonNode optimizationConfiguration = treeNode.get("result").get("optimizationConfiguration");
		String email = optimizationConfiguration.get("email").asText();
		int waitingTime = optimizationConfiguration.get("executionMaxWaitTime").asInt();
		String problemName = optimizationConfiguration.get("problemName").asText();
		final List<String> algorithms = optimizationConfiguration.get("algorithms")
			.findValues("name").stream().map(value -> {
				return value.asText();
			}).collect(Collectors.toList());
		return new OptimizationConfiguration(email, waitingTime, problemName, algorithms);
	}
}
