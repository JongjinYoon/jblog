package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.security.Auth;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets|images).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = { "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" }, method = RequestMethod.GET)
	public String index(@PathVariable String id, @PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2, ModelMap modelMap, Model model) {

		Long categoryNo = 3L;
		Long postNo = 5L;
		BlogVo blogVo = blogService.getId(id);
		model.addAttribute("vo", blogVo);
		if (pathNo2.isPresent()) {
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}

		modelMap.putAll(blogService.getAll(id, categoryNo, postNo));
		return "blog/blog-main";
	}

	@Auth
	@RequestMapping(value = "/blog-admin-basic", method = RequestMethod.GET)
	public String basic(@PathVariable String id, Model model) {
		BlogVo blogVo = blogService.getId(id);
		model.addAttribute("vo", blogVo);
		return "blog/blog-admin-basic";
	}

	@RequestMapping(value = "/blog-admin-basic", method = RequestMethod.POST)
	public String basic(@PathVariable("id") String id, String title,
			@RequestParam(value = "logo", required = true) MultipartFile multipartFile) {

		BlogVo vo = new BlogVo();
		String url = blogService.restore(multipartFile);
		vo.setLogo(url);

		vo.setTitle(title);
		vo.setId(id);

		blogService.update(vo);
		return "redirect:/{id}";
	}
	
	@Auth
	@RequestMapping(value = "/blog-admin-category", method = RequestMethod.GET)
	public String category(HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		BlogVo blogVo = blogService.getId(authUser.getId());
		model.addAttribute("vo", blogVo);
		return "blog/blog-admin-category";
	}

	@ResponseBody
	@RequestMapping(value = "/blog-admin-category", method = RequestMethod.POST)
	public String category(HttpSession session, @RequestBody CategoryVo vo) {
		blogService.insert(vo);
		return "redirect:/blog-admin-category";
	}

	@Auth
	@RequestMapping(value = "/blog-admin-write", method = RequestMethod.GET)
	public String write(@PathVariable("id") String id, Model model) {
		List<CategoryVo> list = categoryService.getList(id);
		model.addAttribute("list", list);
		return "blog/blog-admin-write";
	}

	@RequestMapping(value = "/blog-admin-write", method = RequestMethod.POST)
	public String write(@PathVariable("id") String id, @ModelAttribute PostVo vo, String category) {
		System.out.println(vo);
		System.out.println(category);
		CategoryVo caVo = new CategoryVo();
		caVo.setBlogId(id);
		caVo.setName(category);
		vo.setCategoryNo(categoryService.getNo(caVo));
		postService.insert(vo);
		return "redirect:/{id}";
	}

	@ResponseBody
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable String id) {
		return "id:" + id;
	}

}
