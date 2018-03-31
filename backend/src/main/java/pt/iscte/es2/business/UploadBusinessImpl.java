package pt.iscte.es2.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.datamanager.UploadDatamanager;
import pt.iscte.es2.dto.serviceview.upload.UploadResult;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Transactional
public class UploadBusinessImpl implements UploadBusiness {

	@Autowired
	private UploadDatamanager uploadDatamanager;

	public UploadResult uploadFile(@RequestParam("sessionId") String sessionId, @RequestParam("file") MultipartFile file) {
		String filePath = null;
		if (file.isEmpty()) {
			return null;
		}
		try {
			Path path = Paths.get(ApplicationConstants.JARS_PATH);
			if (!Files.exists(path)) {
				new File(ApplicationConstants.JARS_PATH).mkdirs();
			}
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			filePath = ApplicationConstants.JARS_PATH + file.getOriginalFilename();
			path = Paths.get(filePath);
			Files.write(path, bytes);
			uploadDatamanager.saveUploadFile(sessionId, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new UploadResult(3,4,5);
	}
}
