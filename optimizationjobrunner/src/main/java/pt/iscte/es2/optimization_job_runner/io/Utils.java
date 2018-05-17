package pt.iscte.es2.optimization_job_runner.io;

import org.springframework.mail.SimpleMailMessage;

import java.io.File;

public class Utils {
	public static void deleteDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteDir(f);
			}
		}
		file.delete();
	}

	public static void sendEmail(String subject, String body) {
		final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		simpleMailMessage.setTo("msbsp@iscte-iul.pt");
		simpleMailMessage.setFrom("robot@es2.com");
	}
}
