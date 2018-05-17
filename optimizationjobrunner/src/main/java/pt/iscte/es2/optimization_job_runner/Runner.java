package pt.iscte.es2.optimization_job_runner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Component
public class Runner implements CommandLineRunner {

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Quote {
		private String type;
		private Value value;

		public Quote() {
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

		public Value() {
		}

		public Long getId() {
			return this.id;
		}

		public String getQuote() {
			return this.quote;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setQuote(String quote) {
			this.quote = quote;
		}

		@Override
		public String toString() {
			return "Value{" +
				"id=" + id +
				", quote='" + quote + '\'' +
				'}';
		}
	}


	public Runner() {
	}

	@Override
	public void run(String... args) throws Exception {
//		http://172.17.7.147:8080/api/optimization/fileUpload
//		RestTemplate restTemplate = new RestTemplate();
//		Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//		System.out.println(quote);
//		RestTemplate restTemplate = new RestTemplate();
//		final FileSystemResource file = new FileSystemResource("optimizationjobrunner/target/data/containee-1.0-SNAPSHOT-integer.jar");
//		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
//		MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
//		data.add("name", "um_nome");
//		data.add("description", "descricao");
//		data.add("waiting_time", "123123");
//		multiValueMap.add("sessionId", "coiso");
//		multiValueMap.add("file", file);
//		multiValueMap.add("data", data);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multiValueMap, headers);
//		restTemplate.exchange("http://localhost:8080/api/optimization/fileupload/", HttpMethod.POST, requestEntity, String.class);
	}

}
