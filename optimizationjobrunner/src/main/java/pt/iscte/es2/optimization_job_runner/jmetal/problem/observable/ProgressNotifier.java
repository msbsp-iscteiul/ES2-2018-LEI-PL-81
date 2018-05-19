package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.solution.Solution;
import pt.iscte.es2.optimization_job_runner.mail.MailSender;

import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Listener that notifies a user at given progress points
 */
public class ProgressNotifier implements SolutionEvaluationListener {
	private static final Logger LOGGER = Logger.getLogger(ProgressNotifier.class.getName());

	private final String problemName;
	private final MailSender mailSender;
	private final long totalEvaluations;
	private LongAdder counter = new LongAdder();

	/**
	 * Constructor
	 * @param problemName the problem name
	 * @param mailSender the mail sender
	 * @param totalEvaluations total evaluations to compare
	 */
	public ProgressNotifier(String problemName, MailSender mailSender, long totalEvaluations) {
		this.problemName = problemName;
		this.mailSender = mailSender;
		this.totalEvaluations = totalEvaluations;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * @return number of evaluations counted
	 */
	public long getCount() {
		return counter.sum();
	}

	/**
	 * Notifies client for the progress point
	 * @param percentage point of notification
	 */
	private void notifyClient(String percentage) {
		mailSender.send(
			String.format("Optimização em curso: %s, %s%%", problemName, percentage),
			String.format("Actualmente a %s%%", percentage),
			"msbsp@iscte-iul.pt" // TODO email
		);
		LOGGER.log(Level.INFO, String.valueOf(counter));
	}
}
