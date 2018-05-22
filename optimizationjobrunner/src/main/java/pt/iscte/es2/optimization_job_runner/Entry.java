package pt.iscte.es2.optimization_job_runner;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import pt.iscte.es2.optimization_job_runner.jobs.BackendGateway;
import pt.iscte.es2.optimization_job_runner.mail.MailSender;
import pt.iscte.es2.optimization_job_runner.post_processing.*;

import java.util.Arrays;

@SpringBootApplication
@EnableTransactionManagement
public class Entry {

	static final String TOPIC_EXCHANGE_NAME = "optimization-job-executions-exchange";
	private static final String QUEUE_NAME = "optimization-job-executions";
	private static final String ROUTING_KEY = "pt.iscte.#";

	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	@Bean
	SimpleMessageListenerContainer container(
		ConnectionFactory connectionFactory,
		MessageListenerAdapter listenerAdapter
	) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		container.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(
			receiver,
			"receiveMessage"
		);
	}

	@Value("${spring.mail.port}")
	private int mailPort;
	@Value("${spring.mail.host}")
	private String mailHost;
	@Value("${spring.mail.username}")
	private String mailUsername;
	@Value("${spring.mail.password}")
	private String mailPassword;

	@Bean
	public JavaMailSender getJavaMailSender() {
		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setPort(mailPort);
		mailSender.setHost(mailHost);
		mailSender.setUsername(mailUsername);
		mailSender.setPassword(mailPassword);
		return mailSender;
	}

	@Value("${backend.base_url}")
	private String backendBaseUrl;

	@Bean
	public BackendGateway getBackendGateway() {
		return new BackendGateway(new RestTemplate(), backendBaseUrl);
	}

	@Bean
	public PostProblemProcessor getPostProblemProcessorComposite(
		BackendGateway gateway,
		MailSender mailSender
	) {
		return new PostProblemProcessorComposite(Arrays.asList(
			new LatexToPdfProcessor(),
			new RToEpsProcessor(),
			new BestSolutionsProcessor(),
			new SubmissionProcessor(gateway),
			new NotifyClientFinishedProcessor(mailSender)
		));
	}

	public static void main(String[] args) {
		SpringApplication.run(Entry.class, args);
	}
}
