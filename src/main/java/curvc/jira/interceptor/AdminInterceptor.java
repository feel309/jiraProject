package curvc.jira.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import curvc.jira.model.User;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	/*
	 * @Override public boolean preHandle(HttpServletRequest req,
	 * HttpServletResponse res, Object obj) throws Exception {
	 * 
	 * HttpSession session = req.getSession(); User user =
	 * (User)session.getAttribute("user");
	 * 
	 * 
	 * if(user == null) { res.sendRedirect("/"); return false; }
	 * 
	 * 
	 * return true; }
	 * 
	 */
	
	}