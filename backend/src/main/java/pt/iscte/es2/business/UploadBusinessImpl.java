package pt.iscte.es2.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.algorithm_finder.AlgorithmFinder;
import pt.iscte.es2.client_jar_loader.LoadClientJarProblem;
import pt.iscte.es2.datamanager.UploadDatamanager;
import pt.iscte.es2.dto.UploadFile;
import pt.iscte.es2.dto.serviceview.upload.UploadResult;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
@Transactional
public class UploadBusinessImpl implements UploadBusiness {

	@Autowired
	private UploadDatamanager uploadDatamanager;

	/**
	 * @see UploadBusiness#uploadFile(String, MultipartFile)
	 */
	public UploadResult uploadFile(@RequestParam("sessionId") String sessionId, @RequestParam("file") MultipartFile file) {
		String filePath = null;
		UploadResult result = new UploadResult();
		if (file.isEmpty()) {
			return null;
		}
		try {
			Path path = Paths.get(ApplicationConstants.JARS_PATH);
			if (!Files.exists(path)) {
				new File(ApplicationConstants.JARS_PATH).mkdirs();
			}
			byte[] bytes = file.getBytes();
			filePath = ApplicationConstants.JARS_PATH + file.getOriginalFilename();
			path = Paths.get(filePath);
			Files.write(path, bytes);
			UploadFile uploadFile = uploadDatamanager.saveUploadFile(sessionId, filePath);
			if (uploadFile != null && uploadFile.getId() > 0) {
				try {
					Problem<Solution<?>> problem = new LoadClientJarProblem().loadProblemFromJar(filePath);
					result.setVariables(problem.getNumberOfVariables());
					result.setObjectives(problem.getNumberOfObjectives());
					result.setAlgorithms(new AlgorithmFinder(problem).execute().getAlgorithms());
					// TODO
					result.setVariable_type("Python POWA");
				} catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
