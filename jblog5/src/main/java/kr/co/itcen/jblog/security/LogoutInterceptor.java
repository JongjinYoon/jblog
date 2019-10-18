package kr.co.itcen.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogoutInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("authUser") != null) {// 바로 로그아웃으로 들어오는 악질 잡아내기용
			session.removeAttribute("authUser");
			session.invalidate();
		}
		response.sendRedirect(request.getContextPath() + "/");
		return false;
	}

}
