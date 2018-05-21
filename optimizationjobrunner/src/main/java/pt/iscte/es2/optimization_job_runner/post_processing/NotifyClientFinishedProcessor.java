package pt.iscte.es2.optimization_job_runner.post_processing;

import pt.iscte.es2.optimization_job_runner.mail.MailSender;

/**
 * Notifies the client when a job finishes
 */
public class NotifyClientFinishedProcessor implements PostProblemProcessor {
	private final MailSender mailSender;

	/**
	 * Constructor
	 * @param mailSender mail sender
	 */
	public NotifyClientFinishedProcessor(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Notifies the client when a job finishes
	 * @param result the subject for processing
	 */
	@Override
	public void process(OptimizationJobResult result) {
		mailSender.send(
			String.format(
				"Optimização terminada %s",
				result.getContext().getJob().getProblemName()
			),
			"O processo de optimização foi terminado com sucesso!",
			result.getContext().getJob().getUserEmail()
		);
	}
}
