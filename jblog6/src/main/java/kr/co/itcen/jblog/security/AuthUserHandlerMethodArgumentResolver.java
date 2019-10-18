package kr.co.itcen.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import kr.co.itcen.jblog.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		if (supportsParameter(parameter) == false) {// 내가 지원하는 아규먼트가 아님 넘기셈
			return WebArgumentResolver.UNRESOLVED;
		}

		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

		HttpSession session = request.getSession();
		if (session == null) {
			return null;
		}
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);

		// @AuthUser가 없으면 실행안함
		if (authUser == null) {
			return false;
		}

		// 파라미터 타입이 UserVo 인지 확인
		if (parameter.getParameterType().equals(UserVo.class) == false) {
			return false;
		}
		return true;
	}
}
