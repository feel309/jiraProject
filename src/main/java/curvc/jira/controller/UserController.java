package curvc.jira.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import curvc.jira.model.Config;
import curvc.jira.model.FileVO;
import curvc.jira.model.Issue;
import curvc.jira.model.User;
import curvc.jira.paging.Paging;
import curvc.jira.service.UserService;

@Controller
public class UserController {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String dashboard(HttpSession session, User user) {

		// 로그인 세션이 없으면 로그인 화면으로 이동
		if (session.getAttribute("loginUser") == null) {
			return "/Pages/login";
		}


		return "/Dashboard/index";
	}

	// 회원가입 등록폼
	@RequestMapping("/enrollpage")
	public String enrollpage() {
		return "/Pages/register";
	}

	// 회원가입 완료
	@RequestMapping("/signup")
	public String signup(User user) throws Exception {

		String pw = user.getPw();
		System.out.println(pw);
		String enPw = encoder.encode(pw);
		user.setPw(enPw);
		System.out.println("암호화된 비밀번호 : " + user.getPw());

		// userName 출력해봄
		int result = userService.insertUser(user);
		System.out.println(result);

		return "/Pages/login";
	}

	// 로그인 등록폼
	@RequestMapping("/loginpage")
	public String loginpage() {
		return "/Pages/login";
	}

	// 로그인 완료
	@RequestMapping("/login")
	public String login(User user, HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		String id = user.getUser_id();
		String pw = userService.getUserPw(id);
		
		String rawPw = user.getPw();
		System.out.println("db pW  : "+pw);
        System.out.println("입력Pw:"+rawPw);
        System.out.println(encoder.matches(rawPw, pw));
		
       
        if(pw == null) {//아이디가 틀릴 때
        	//서버 단에서 alert창 띄우기
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디 불일치!'); history.go(-1);</script>");
            out.flush();
        	
        	return "/Pages/login";
		}
        else {//아이디가 맞을 때
        	if(encoder.matches(rawPw, pw)) {
                System.out.println("비밀번호 일치");
                
                session.setAttribute("loginUser", user);
                return "/Dashboard/index";
            }else {
                System.out.println("비밀번호 불일치!");
                
                //서버 단에서 alert창 띄우기
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('비밀번호 불일치!'); history.go(-1);</script>");
                out.flush();

                return "/Pages/login";
            }
        	
        }
        

	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "/Pages/login";
	}

	// 아이디 중복확인
	@ResponseBody
	@RequestMapping("/checkId")
	public String checkId(String user_id) {

		int count = userService.selectIdCount(user_id);
		String result = "";
		if (count == 0) {
			result = "0";
		} else {
			result = "1";
		}
		return result;
	}

	// 사용자 리스트
	@RequestMapping("/userList")
	public String userList(HttpSession session, Model model, User user,
			@RequestParam(value = "cPage", required = false, defaultValue = "1") int cPage) {

		if (session.getAttribute("loginUser") == null) {
			return "/Pages/login";
		}

		// 5개 리스트 화면에 출력
		int numPerPage = 5;

		List<User> list = userService.selectList(cPage, numPerPage);
		int total = userService.selectUserCountOne();
		System.out.println(list);

		model.addAttribute("list", list);
		model.addAttribute("total", total);
		model.addAttribute("pageBar", Paging.getPageBar(total, cPage, numPerPage));

		return "/User/userList";
	}

	// 사용자 수정 페이지
	@RequestMapping("/userUpdatePage")
	public String userUpdatePage(HttpSession session, User user, Model model) {

		if (session.getAttribute("loginUser") == null) {
			return "/Pages/login";
		}

		User result = userService.selectOneList(user);
		System.out.println(result);

		model.addAttribute("result", result);
		return "/User/userUpdatePage";
	}

	// 사용자 수정 완료
	@RequestMapping("/userUpdate")
	public String userUpdate(HttpSession session, User user, Model model) throws Exception {
		// System.out.println(user);

		if (session.getAttribute("loginUser") == null) {
			return "/Pages/login";
		}

		List<User> list = userService.selectUserInfo(user);
		System.out.println(list);

		if (list != null) {
			int result = userService.updateUser(user);
			System.out.println(result);

			model.addAttribute("result", result);
			model.addAttribute("list", list);
			// model.addAttribute("user", user);

			// return "/User/userList2";
			// return "redirect: /User/userList?user_id=" + user.getUser_id();
			return "/User/userList";
		}

		else {
			return "/Dashboard/index";
		}

		// return "/User/userList";
	}

	// 이슈 등록폼
	@RequestMapping("/issue/enrollpage")
	public String issueEnrollpage() {
		return "/Issue/enroll";
	}

	// 이슈등록 완료
	@RequestMapping(value = "/issue/enroll", method = RequestMethod.POST)
	public String issueEnroll(HttpSession session, Issue issue, Model model,
			MultipartHttpServletRequest req) throws Exception {

		if (session.getAttribute("loginUser") == null) {
			return "/Pages/login";
		}
		
		  
		// 파일업로드 화면출력
		// 파일 태그
		String fileTag = "ori_name";
		// 파일 이름
		MultipartFile file = req.getFile(fileTag);
		String fileName = file.getOriginalFilename();
		System.out.println("오리지널" + fileName);
		// 파일 전송
		try {
			file.transferTo(new File(fileName));
		} catch (Exception e) {
			System.out.println("업로드 오류");
		}

		model.addAttribute("fileName", fileName);

		System.out.println("파일333");
		
		// 이슈추가 - 이슈번호생성
		int result = userService.insertIssue(issue);
		System.out.println(result);
		
		// file DB에 insert - 추가한 이슈번호 가져옴
		userService.insertFile(issue, req);
		System.out.println("파일2");
		

		List<Issue> isList = userService.selectIssueList();
		System.out.println(isList);

		model.addAttribute("list", isList);

		return "/Issue/issueList";

	}

	// 이슈 리스트
	@RequestMapping("/issueList")
	public String issueList(HttpSession session, Model model, Issue issue, User user, FileVO file2) {

		if (session.getAttribute("loginUser") == null) {
			return "/Pages/login";
		}
		
		
		List<Issue> isList = userService.selectIssueList();
		System.out.println(isList);

		model.addAttribute("list", isList);
		return "/Issue/issueList";
	}

	// 이슈 수정 페이지
	@RequestMapping("/issueUpdatePage")
	public String issueUpdatePage(HttpSession session, Issue issue, Model model) {

		if (session.getAttribute("loginUser") == null) {
			return "/Pages/login";
		}

		Issue result = userService.selectIssue(issue);
		System.out.println(result);

		model.addAttribute("result", result);
		return "/Issue/issueUpdatePage";
	}

	// 이슈 수정 완료
	@RequestMapping("/issueUpdate")
	public String issueUpdate(HttpSession session, Issue issue, Model model) throws Exception {
		// System.out.println(user);

		if (session.getAttribute("loginUser") == null) {
			return "/Pages/login";
		}

		List<Issue> list = userService.selectIssueInfo(issue);
		System.out.println(list);

		if (list != null) {
			int result = userService.updateIssue(issue);
			System.out.println(result);

			model.addAttribute("result", result);
			model.addAttribute("list", list);
			return "/Issue/issueList";
		}

		else {
			return "/Dashboard/index";
		}

	}

	@RequestMapping("/jiraList")
	public String jiraList(HttpSession session, User user, Config config) {

		if (session.getAttribute("loginUser") == null) {
			return "/Pages/login";
		}

		return "Jira/jiraList";
	}

	// Jira 저장 완료
	@RequestMapping("/jiraSave")
	public String jiraSave(HttpSession session, User user, Model model, HttpServletRequest req) throws Exception {

		if (session.getAttribute("loginUser") == null) {
			return "/Pages/login";
		}

		// form data Parameter를 꺼내서

		// String baseUrl = "";
		// String userId = "";
		// String password = "";
		// String projectKey = "";
		// String issueTypeId = "";
		// String linkId = "";

		String baseUrl = req.getParameter("baseUrl");
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String projectKey = req.getParameter("projectKey");
		String issueTypeId = req.getParameter("issueTypeId");
		String linkId = req.getParameter("linkId");

		List<Config> configs = new ArrayList<>();

		Config config = new Config();
		config.setKey("baseUrl");
		config.setValue(baseUrl);

		configs.add(config);
		// baseUrl DB 저장

		Config config1 = new Config();
		config1.setKey("userId");
		config1.setValue(userId);

		configs.add(config1);
		// userId DB 저장

		Config config2 = new Config();
		config2.setKey("password");
		config2.setValue(password);

		configs.add(config2);
		// password DB 저장

		Config config3 = new Config();
		config3.setKey("projectKey");
		config3.setValue(projectKey);

		configs.add(config3);
		// projectKey DB 저장

		Config config4 = new Config();
		config4.setKey("issueTypeId");
		config4.setValue(issueTypeId);

		configs.add(config4);
		// issueTypeId DB 저장

		Config config5 = new Config();
		config5.setKey("linkId");
		config5.setValue(linkId);

		configs.add(config5);
		// linkId DB 저장

		System.out.println("리스트" + configs.toString());

		int resConf = userService.insertConfig(config); // baseUrl DB insert
		System.out.println(resConf);

		int resConf1 = userService.insertConfig(config1); // userId DB insert
		System.out.println(resConf1);

		int resConf2 = userService.insertConfig(config2); // password DB insert
		System.out.println(resConf2);

		int resConf3 = userService.insertConfig(config3); // projectKey DB insert
		System.out.println(resConf3);

		int resConf4 = userService.insertConfig(config4); // issueTypeId DB insert
		System.out.println(resConf4);

		int resConf5 = userService.insertConfig(config5); // linkId DB insert
		System.out.println(resConf5);

		// model.addAttribute("config",config);

		System.out.println("키" + config1.getKey());
		System.out.println("값" + config1.getValue());

		configs.add(config);
		configs.add(config1);
		configs.add(config2);
		configs.add(config3);
		configs.add(config4);
		configs.add(config5);

		System.out.println("리스트" + configs);

		model.addAttribute("config", config);
		model.addAttribute("config1", config1);
		model.addAttribute("config2", config2);
		model.addAttribute("config3", config3);
		model.addAttribute("config4", config4);
		model.addAttribute("config5", config5);

		return "Jira/jiraList2";

	}

}
