package curvc.jira.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import curvc.jira.dao.UserDAO;
import curvc.jira.model.Config;
import curvc.jira.model.FileVO;
import curvc.jira.model.Issue;
import curvc.jira.model.User;
import curvc.jira.util.FileUtils;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public int insertUser(User user) throws Exception {
		return userDAO.insertUser(user);
	}

	@Override
	public int updateUser(User user) throws Exception {
		 return userDAO.updateUser(user);
	}

	@Override
	public void deleteUser(String user_id) throws Exception {
		userDAO.deleteUser(user_id);
	}

	@Override
	public User selectOne(User user) {
		return userDAO.selectOne(user);
	}


	@Override
	public int selectIdCount(String user_id) {
		return userDAO.selectIdCount(user_id);
	}


	@Override
	public User selectOneList(User user) {
		return userDAO.selectOneList(user);
	}

	@Override
	public List<User> selectUserInfo(User user) {
		return userDAO.selectUserInfo(user);
	}

	@Override
	public int insertIssue(Issue issue) {
		return userDAO.insertIssue(issue);
	}

	@Override
	public List<Issue> selectIssueList() {
		return userDAO.selectIssueList();
	}

	@Override
	public Issue selectIssue(Issue issue) {
		return userDAO.selectIssue(issue);
	}

	@Override
	public int insertConfig(Config config) {
		return userDAO.insertConfig(config);
	}

	@Override
	public List<Config> selectConfigList() {
		return userDAO.selectConfigList();
	}

	@Override
	public List<User> selectList(int cPage, int numPerPage) {
		return userDAO.selectList(cPage, numPerPage);
	}

	@Override
	public int selectUserCountOne() {
		return userDAO.selectUserCountOne();
	}

	@Override
	public List<Issue> selectIssueInfo(Issue issue) {
		return userDAO.selectIssueInfo();
	}

	@Override
	public int updateIssue(Issue issue) {
		return userDAO.updateIssue(issue);
	}

	

	// 게시글 작성
		@Override
		public void insertFile(Issue issue, MultipartHttpServletRequest req) throws Exception {
			//userDAO.wirte(issue); 
			
			System.out.println("파일서비스");
			
			List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(issue, req); 
			int size = list.size();
			for(int i=0; i<size; i++){ 
				userDAO.insertFile(list.get(i)); 
			}
		}

		@Override
		public String getUserPw(String id) {
			return userDAO.getUserPw(id);
		}

	/*
	 * @Override public List<FileVO> selectOneFile(int file_id) { return
	 * userDAO.selectOneFile(file_id); }
	 */



	
}
