package pt.iscte.es2.optimization_job_runner.jobs;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

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
		JsonNode result = treeNode.get("result");
		String email = result.get("email").asText();
		int waitingTime = result.get("executionMaxWaitingTime").asInt();
		String problemName = result.get("problemName").asText();
		return new OptimizationConfiguration(email, waitingTime, problemName);
	}
}
