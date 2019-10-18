package kr.co.itcen.jblog.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.itcen.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String id = (String)pathVariables.get("id");
		
		// 1. handler 종류(DefaultServletHttpRequestHandler, HandlerMethod)
		// handler 메서드 체크(컨트롤러에 있는 메서드인지 체크한다는 말)
		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. @Auth가 없으면 class type에 있을 수 있으므로...
		
		if (auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		// 과제 : class type에서 @Auth가 있는지 확인해야 한다.
		
		// 5. @Auth가 없으면
		if (auth == null){
			return true;
		}

		// 6. @Auth가 class나 method에 붙어 있기 때문에 인증 여부를 체크한다.
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (session == null || authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		if(!id.equals(authUser.getId())) {
			response.sendRedirect(request.getContextPath()+"/"+authUser.getId());
			return false;
		}
		return true;
	}

}
