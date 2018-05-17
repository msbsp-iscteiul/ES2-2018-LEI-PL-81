package pt.iscte.es2.optimization_job_runner;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pt.iscte.es2.optimization_job_runner.post_processing.*;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableJpaAuditing
public class Entry {

	static final String TOPIC_EXCHANGE_NAME = "spring-boot-exchange";

	private static final String QUEUE_NAME = "spring-boot";

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
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
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

	@Value("${backend.host}")
	private String backendHost;
	@Value("${backend.port}")
	private String backendPort;

	@Bean
	public PostProblemProcessor getPostProblemProcessorComposite() {
		return new PostProblemProcessorComposite(Arrays.asList(
			new LatexToPdfProcessor(),
			new RToEpsProcessor(),
			new BestSolutionsProcessor(),
			new SubmissionProcessor(backendHost, backendPort)
		));
	}

	public static void main(String[] args) {
		SpringApplication.run(Entry.class, args);
	}
}
