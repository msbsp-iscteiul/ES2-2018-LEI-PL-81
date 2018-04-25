package pt.iscte.es2.business;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import pt.iscte.es2.dto.HistoryNode;
import pt.iscte.es2.dto.serviceview.history.HistoryResult;

import javax.transaction.Transactional;
import java.util.Date;

@Component
@Transactional
public class HistoryBusinessImpl implements HistoryBusiness {

	/**
	 * @see HistoryBusiness#searchOptimizationJobsByEmail(String)
	 */
	public HistoryResult searchOptimizationJobsByEmail(@RequestParam("email") String email) {
		System.out.println("EMAIL IS: " + email);
		HistoryResult historyResult = new HistoryResult();
		HistoryNode node = new HistoryNode();
		node.setProblemName("Name1");
		node.setStartDate(new Date(2018-1-1));
		node.setEndDate(new Date(2018-1-31));
		historyResult.getNodes().add(node);
		return historyResult;
	}
}
