package pt.iscte.es2.optimization_job_runner.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Send emails
 */
@Component
public class MailSender {
	@Autowired
	private JavaMailSender mailSender;
	@Value("${mail.admin}")
	private String adminAddress;

	/**
	 * Send email
	 * @param subject the subject
	 * @param body the body
	 * @param to the recipient
	 */
	public void send(String subject, String body, String to) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(adminAddress);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(body);
		mailSender.send(mailMessage);
	}
}
