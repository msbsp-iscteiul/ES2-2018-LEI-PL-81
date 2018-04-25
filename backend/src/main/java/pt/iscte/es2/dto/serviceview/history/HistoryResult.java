package pt.iscte.es2.dto.serviceview.history;

import pt.iscte.es2.dto.HistoryNode;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO History Result
 */
public class HistoryResult {

	private List<HistoryNode> nodes;

	public HistoryResult() {

	}

	public HistoryResult(List<HistoryNode> nodes) {
		this.nodes = nodes;
	}

	public List<HistoryNode> getNodes() {
		if (nodes == null) {
			nodes = new ArrayList<>();
		}
		return nodes;
	}

	public void setNodes(List<HistoryNode> nodes) {
		this.nodes = nodes;
	}
}
