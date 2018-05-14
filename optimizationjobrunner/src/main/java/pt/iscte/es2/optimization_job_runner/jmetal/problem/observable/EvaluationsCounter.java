package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.uma.jmetal.solution.Solution;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvaluationsCounter implements SolutionEvaluationListener {
	private static final Logger LOGGER = Logger.getLogger(EvaluationsCounter.class.getName());

	private final JavaMailSender mailSender;
	private final int totalEvaluations;
	private int counter = 0;

	public EvaluationsCounter(JavaMailSender mailSender, int totalEvaluations) {
		this.mailSender = mailSender;
		this.totalEvaluations = totalEvaluations;
	}

	@Override
	public void onSolutionEvaluated(Solution solution) {
		counter++;
		if (counter == totalEvaluations * .25
			|| counter == totalEvaluations * .50
			|| counter == totalEvaluations * .75) {
			final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setSubject("Progress");
			simpleMailMessage.setTo("msbsp@iscte-iul.pt");
			simpleMailMessage.setFrom("robot@es2.com");
			simpleMailMessage.setText("Currently at " + counter + " iterations.");
			try {
				mailSender.send(simpleMailMessage);
			} catch (MailException ignore) {
			}
			LOGGER.log(Level.INFO, String.valueOf(counter));
		}
	}

	public int getCount() {
		return counter;
	}
}
