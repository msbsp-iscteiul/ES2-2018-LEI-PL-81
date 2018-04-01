package pt.iscte.es2.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.client_jar_loader.LoadClientJarProblem;
import pt.iscte.es2.client_jar_loader.SecureClientClassLoader;
import pt.iscte.es2.datamanager.UploadDatamanager;
import pt.iscte.es2.dto.UploadFile;
import pt.iscte.es2.dto.serviceview.upload.UploadResult;
import pt.iscte.es2.optimization_job_runner.stub.JMetalConfiguration;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
					result.setId(uploadFile.getId());
					result.setVariables(problem.getNumberOfVariables());
					result.setObjectives(problem.getNumberOfObjectives());
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
