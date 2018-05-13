package pt.iscte.es2.optimization_job_runner.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class MailSenderProvider {

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setPort(2525);
		mailSender.setHost("smtp.mailtrap.io");
		mailSender.setUsername("894d31b3ee68c2");
		mailSender.setPassword("23b8415d5dea92");
		return mailSender;
	}
}
