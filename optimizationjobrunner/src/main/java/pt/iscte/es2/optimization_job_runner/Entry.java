package pt.iscte.es2.optimization_job_runner;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import pt.iscte.es2.client_jar_loader.LoadClientJarProblem;

import java.net.MalformedURLException;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableJpaAuditing
public class Entry {

//	static final String TOPIC_EXCHANGE_NAME = "spring-boot-exchange";
//
//	private static final String QUEUE_NAME = "spring-boot";
//
//	@Bean
//	Queue queue() {
//		return new Queue(QUEUE_NAME, false);
//	}
//
//	@Bean
//	TopicExchange exchange() {
//		return new TopicExchange(TOPIC_EXCHANGE_NAME);
//	}
//
//	@Bean
//	Binding binding(Queue queue, TopicExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
//	}
//
//	@Bean
//	SimpleMessageListenerContainer container(
//		ConnectionFactory connectionFactory,
//		MessageListenerAdapter listenerAdapter
//	) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.setQueueNames(QUEUE_NAME);
//		container.setMessageListener(listenerAdapter);
//		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//		return container;
//	}
//
//	@Bean
//	MessageListenerAdapter listenerAdapter(Receiver receiver) {
//		final MessageListenerAdapter receiveMessage = new MessageListenerAdapter(
//			receiver,
//			"receiveMessage");
//		return receiveMessage;
//	}

//	@Bean
//	public Problem<Solution<?>> clientOptimizationProblem() throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
//		return new LoadClientJarProblem()
//			.loadProblemFromJar("optimizationjobrunner/target/data/containee-1.0-SNAPSHOT-double.jar");
//	}

	public static void main(String[] args) {
		SpringApplication.run(Entry.class, args);
	}
}
