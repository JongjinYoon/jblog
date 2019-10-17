package kr.co.itcen.jblog.controller.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller("categoryApiController")
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping("/getList")
	public List<CategoryVo> getCategoryList(HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		List<CategoryVo> result = categoryService.list(userVo.getId());
		System.out.println(result);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Boolean delete(@RequestParam(value="no", required = true, defaultValue = "") String no) {		
		
		return categoryService.delete(Long.parseLong(no));
	}
}
