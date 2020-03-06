package curvc.jira.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import curvc.jira.model.Config;
import curvc.jira.model.FileVO;
import curvc.jira.model.Issue;
import curvc.jira.model.User;

public interface UserService {
	
	public int insertUser(User user) throws Exception;
	public int updateUser(User user) throws Exception;
	public void deleteUser(String user_id) throws Exception;
	public User selectOne(User user);
	public int selectIdCount(String user_id);
	public List<User> selectList(int cPage, int numPerPage);
	public User selectOneList(User user);
	public List<User> selectUserInfo(User user);
	public int insertIssue(Issue issue);
	public List<Issue> selectIssueList();
	public Issue selectIssue(Issue issue);
	
	public int insertConfig(Config config);
	public List<Config> selectConfigList();
	public int selectUserCountOne();
	public List<Issue> selectIssueInfo(Issue issue);
	public int updateIssue(Issue issue);
	public void insertFile(Issue issue, MultipartHttpServletRequest req) throws Exception;
	public String getUserPw(String id);
	
	//public List<FileVO> selectOneFile(int file_id);
	
}
