package pt.iscte.es2.optimization_job_runner.post_processing;

import pt.iscte.es2.optimization_job_runner.mail.MailSender;

public class NotifyClientFinishedProcessor implements PostProblemProcessor {
	private final MailSender mailSender;

	public NotifyClientFinishedProcessor(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void process(OptimizationJobResult result) {
		mailSender.send(
			String.format(
				"Optimização terminada %s",
				"Nome do problema" // TODO nome do problema
			),
			"O processo de optimização foi terminado com sucesso. ",
			"mauro.s.pinto@gmail.com" // TODO email
		);
	}
}
