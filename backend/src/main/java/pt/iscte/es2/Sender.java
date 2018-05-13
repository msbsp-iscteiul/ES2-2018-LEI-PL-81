package pt.iscte.es2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	private final RabbitTemplate rabbitTemplate;

	public Sender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(Integer id) {
		rabbitTemplate.convertAndSend(Application.topicExchangeName, "pt.iscte.es2", id);
	}
}
