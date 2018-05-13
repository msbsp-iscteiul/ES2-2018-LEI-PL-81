package pt.iscte.es2.business;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import pt.iscte.es2.dto.HistoryNode;
import pt.iscte.es2.dto.service.history.HistoryResult;

import javax.transaction.Transactional;
import java.util.Date;

@Component
@Transactional
public class HistoryBusinessImpl implements HistoryBusiness {

	/**
	 * @see HistoryBusiness#searchOptimizationJobsByEmail(String)
	 */
	public HistoryResult searchOptimizationJobsByEmail(@RequestParam("email") String email) {
		System.out.println("Email is: " + email);
		HistoryResult historyResult = new HistoryResult();
		HistoryNode node1 = new HistoryNode();
		node1.setId(1);
		node1.setProblemName("Name1");
		node1.setStartDate(new Date());
		node1.setEndDate(new Date());
		historyResult.getNodes().add(node1);
		HistoryNode node2 = new HistoryNode();
		node2.setId(2);
		node2.setProblemName("Name2");
		node2.setStartDate(new Date());
		node2.setEndDate(new Date());
		historyResult.getNodes().add(node2);
		HistoryNode node3 = new HistoryNode();
		node3.setId(3);
		node3.setProblemName("Name3");
		node3.setStartDate(new Date());
		node3.setEndDate(new Date());
		historyResult.getNodes().add(node3);
		HistoryNode node4 = new HistoryNode();
		node4.setId(4);
		node4.setProblemName("Name4");
		node4.setStartDate(new Date());
		node4.setEndDate(new Date());
		historyResult.getNodes().add(node4);
		return historyResult;
	}
}
