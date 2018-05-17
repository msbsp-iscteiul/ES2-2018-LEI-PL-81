package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.solution.Solution;
import pt.iscte.es2.optimization_job_runner.mail.MailSender;

import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgressNotifier implements SolutionEvaluationListener {
	private static final Logger LOGGER = Logger.getLogger(ProgressNotifier.class.getName());

	private final String problemName;
	private final MailSender mailSender;
	private final long totalEvaluations;
	private LongAdder counter = new LongAdder();

	public ProgressNotifier(String problemName, MailSender mailSender, long totalEvaluations) {
		this.problemName = problemName;
		this.mailSender = mailSender;
		this.totalEvaluations = totalEvaluations;
	}

	@Override
	public void onSolutionEvaluated(Solution solution) {
		counter.increment();
		String percentage = null;

		if (counter.sum() == totalEvaluations * .25) {
			percentage = "25";
		} else if (counter.sum() == totalEvaluations * .50) {
			percentage = "50";
		} else if (counter.sum() == totalEvaluations * .75) {
			percentage = "75";
		}

		if (percentage != null) {
			notifyClient(percentage);
		}
	}

	public long getCount() {
		return counter.sum();
	}

	private void notifyClient(String percentage) {
		mailSender.send(
			String.format("Optimização em curso: %s, %s%%", problemName, percentage),
			String.format("Actualmente a %s%%", percentage),
			"msbsp@iscte-iul.pt" // TODO email
		);
		LOGGER.log(Level.INFO, String.valueOf(counter));
	}
}
