package kr.co.itcen.jblog.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}

		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(UserVo vo, HttpSession session, Model model) {
		UserVo userVo = userService.getUser(vo);
		if(userVo == null) {
			model.addAttribute("result","fail");
			return "user/login";
		}
		//로그인 처리
		session.setAttribute("authUser", userVo);
		return "redirect:/";
	}

	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		//접근제어(ACL)
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			session.removeAttribute("authUser");
			session.invalidate();
		}
		return "redirect:/";
	}

//	@RequestMapping(value = "/update", method=RequestMethod.GET)
//	public String update(HttpSession session, Model model) {
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
//		UserVo userVo = userService.getUser(authUser.getNo());
//
//		model.addAttribute("vo",userVo);
//		return "user/update";
//	}

//	@Auth("USER")
//	//디폴트값이 유저지만 그냥 명시한거임 값이 두개이상일때 파라미터 명시 value만 생략가능 value말고 딴이름 쓰면 명시해야됨
//	//role=Auth.Role.ADMIN도 가능
//	@RequestMapping(value = "/update", method=RequestMethod.GET)
//	public String update(@AuthUser UserVo authUser, Model model) {
//		
//		authUser = userService.getUser(authUser.getNo());
//		UserVo userVo = userService.getUser(authUser.getNo());
//		model.addAttribute("vo",userVo);
//		return "user/update";
//	}
//	
//	@RequestMapping(value = "/update", method=RequestMethod.POST)
//	public String update(@ModelAttribute UserVo vo,HttpSession session, Model model) {
//		//@ModelAttribute는 addAttribute를 자동으로 해주는거
//		userService.update(vo);
//		session.setAttribute("authUser", vo);
//		return "redirect:/";
//	}

//	@ExceptionHandler(UserDaoException.class)
//	public String handlerException() {
//		return "error/exception";
//	}

}
