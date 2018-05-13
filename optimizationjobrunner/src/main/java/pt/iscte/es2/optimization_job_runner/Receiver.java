package pt.iscte.es2.optimization_job_runner;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
	}
}
