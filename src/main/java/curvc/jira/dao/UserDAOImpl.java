package curvc.jira.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import curvc.jira.model.Config;
import curvc.jira.model.FileVO;
import curvc.jira.model.Issue;
import curvc.jira.model.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SqlSessionTemplate session;


	@Override
	public int insertUser(User user) throws Exception {
		return session.insert("user.insertUser", user);
	}

	@Override
	public int updateUser(User user) throws Exception {
		return session.update("user.updateUser", user);
	}

	@Override
	public int deleteUser(String user_id) throws Exception {
		return session.delete("user.deleteUser", user_id);
	}
	
	@Override
	public User selectOne(User user) {
		return session.selectOne("user.selectOne", user);
	}


	@Override
	public int selectIdCount(String user_id) {
		return session.selectOne("user.selectIdCount", user_id);
	}

	@Override
	public User selectOneList(User user) {
		return session.selectOne("user.selectOneList", user);
	}

	@Override
	public List<User> selectUserInfo(User user) {
		return session.selectList("user.selectUserInfo");
	}

	@Override
	public int insertIssue(Issue issue) {
		return session.insert("issue.insertIssue", issue);
	}

	@Override
	public List<Issue> selectIssueList() {
		return session.selectList("issue.selectIssueList");
	}

	@Override
	public Issue selectIssue(Issue issue) {
		return session.selectOne("issue.selectIssue", issue);
	}

	@Override
	public int insertConfig(Config config) {
		return session.insert("config.insertConfig", config);
	}

	@Override
	public List<Config> selectConfigList() {
		return session.selectList("config.selectConfigList");
	}

	@Override
	public List<User> selectList(int cPage, int numPerPage) {
		return session.selectList("user.selectList",null, new RowBounds((cPage-1)*numPerPage, numPerPage));
	}
	
	@Override
	public int selectUserCountOne() {
		return session.selectOne("user.selectUserCountOne");
	}

	@Override
	public List<Issue> selectIssueInfo() {
		return session.selectList("issue.selectIssueInfo");
	}

	@Override
	public int updateIssue(Issue issue) {
		return session.update("user.updateIssue", issue);
	}

	@Override
	public void insertFile(Map<String, Object> map) {
		session.insert("file.insertFile", map);
	}

	@Override
	public String getUserPw(String id) {
		return session.selectOne("user.getUserPw",id);
	}

	/*
	 * @Override public List<FileVO> selectOneFile(int file_id) { return
	 * session.selectOne("file.selectOneFile", file_id); }
	 */

	
	
}
