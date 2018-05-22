package pt.iscte.es2;

import org.dozer.spring.DozerBeanMapperFactoryBean;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(ApplicationConstants.APPLICATION_ROOT_PACKAGE)
@EnableJpaRepositories(basePackages = ApplicationConstants.DAO_PACKAGE)
@EnableTransactionManagement
@EnableJpaAuditing
public class Application {

	static final String topicExchangeName = "optimization-job-executions-exchange";

	static final String queueName = "optimization-job-executions";

	@Bean
	public Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("pt.iscte.#");
	}

	@Bean
	public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean() {
		return new DozerBeanMapperFactoryBean();
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
