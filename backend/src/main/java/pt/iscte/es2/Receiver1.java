package pt.iscte.es2;

import org.springframework.stereotype.Component;

@Component
public class Receiver1 {

	public void receiveMessage(Integer id) {
		System.out.println("Received: <" + id + ">");
	}
}
