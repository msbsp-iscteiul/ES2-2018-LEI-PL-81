package pt.iscte.es2.optimization_job_runner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pt.iscte.es2.optimization_job_runner.jobs.BackendGateway;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	private final BackendGateway backendGateway;

	@JsonDeserialize(using = QuoteDeserializer.class)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Quote {
		private String type;
		private Value value;

		public Quote(String type, Value value) {
			this.type = type;
			this.value = value;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Value getValue() {
			return value;
		}

		public void setValue(Value value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Quote{" +
				"type='" + type + '\'' +
				", value=" + value +
				'}';
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Value {
		private Long id;
		private String quote;

		public Value(Long id, String quote) {
			this.id = id;
			this.quote = quote;
		}

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "Value{" +
				"id=" + id +
				", quote='" + quote + '\'' +
				'}';
		}
	}

	public static class QuoteDeserializer extends StdDeserializer<Quote> {

		public QuoteDeserializer() {
			this(null);
		}

		protected QuoteDeserializer(Class<?> vc) {
			super(vc);
		}

		@Override
		public Quote deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
			final JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);
			String type = treeNode.get("type").asText();
			long valueId = treeNode.get("value").get("id").longValue();
			String valueQuote = treeNode.get("value").get("quote").asText();
			return new Quote(type, new Value(valueId, valueQuote));
		}
	}

	public Runner(RabbitTemplate rabbitTemplate, BackendGateway backendGateway) {
		this.rabbitTemplate = rabbitTemplate;
		this.backendGateway = backendGateway;
	}

	@Override
	public void run(String... args) throws Exception {

//		String file = backendGateway.downloadProblem(1);
//		System.out.println(file);

//		Object[] idEmail = new Object[]{10L, "coiso@coiso.com"};
//		rabbitTemplate.convertAndSend(Entry.TOPIC_EXCHANGE_NAME, "pt.iscte.#", idEmail);
//		System.out.println(idEmail);

//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setAccept(Collections.singletonList(
//			MediaType.APPLICATION_OCTET_STREAM
//		));
//		final MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
//		multiValueMap.add("id", "4");
//		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(multiValueMap, httpHeaders);
//		// jobid
//		ResponseEntity<byte[]> response = new RestTemplate()
//			.exchange(
//				"http://172.17.10.175:8080/api/optimization/searchattachmentbyjobexecution/",
//				HttpMethod.POST,
//				entity,
//				byte[].class
//			);
//		System.out.println(Arrays.toString(response.getBody()));

//		http://172.17.7.147:8080/api/optimization/fileUpload

//		ObjectMapper objectMapper = new ObjectMapper();
//		SimpleModule module = new SimpleModule();
//		module.addDeserializer(Quote.class, new QuoteDeserializer())

//		final RestTemplate restTemplate = new RestTemplate();
//		final List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
//		final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//		messageConverters.add(jsonConverter);
//		restTemplate.setMessageConverters(messageConverters);
//		Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//		System.out.println(quote);
	}

}
