package pt.iscte.es2.optimization_job_runner.post_processing;

import java.util.List;

public class OptimizationJobResult {
	private final PostProcessingContext context;
	private Long jobId;
	private List<AlgorithmSolutionQuality> bestSolutions;
	private String latexPdf;
	private String rEps;

	public OptimizationJobResult(PostProcessingContext context) {
		this.context = context;
		jobId = context.getJobId();
	}

	public PostProcessingContext getContext() {
		return context;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public List<AlgorithmSolutionQuality> getBestSolutions() {
		return bestSolutions;
	}

	public void setBestSolutions(List<AlgorithmSolutionQuality> bestSolutions) {
		this.bestSolutions = bestSolutions;
	}

	public String getLatexPdf() {
		return latexPdf;
	}

	public void setLatexPdf(String latexPdf) {
		this.latexPdf = latexPdf;
	}

	public String getrEps() {
		return rEps;
	}

	public void setrEps(String rEps) {
		this.rEps = rEps;
	}
}
