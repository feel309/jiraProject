package curvc.jira.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import curvc.jira.model.Issue;

@Component
public class FileUtils {
	
private static final String filePath = "C:\\mp\\file\\"; // 파일이 저장될 위치
	
	public static List<Map<String, Object>> parseInsertFileInfo(Issue issue, 
			MultipartHttpServletRequest mpRequest) throws Exception{
		
		
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null;
		
		int issueNo = issue.getIssue_no();
		
		System.out.println("이슈번호"+issueNo);
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("issue_no", issueNo);
				listMap.put("ori_name", originalFileName);
				listMap.put("st_name", storedFileName);
				listMap.put("file_size", multipartFile.getSize());
				listMap.put("file_type", multipartFile.getContentType());
				list.add(listMap);
				System.out.println("전체 맵리스트"+list);
			}
		}
		return list;
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
