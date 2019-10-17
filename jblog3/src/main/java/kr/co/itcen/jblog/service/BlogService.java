package kr.co.itcen.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.exception.FileuploadException;
import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class BlogService {
	private static final String SAVE_PATH = "/uploads";
	private static final String URL_PREFIX = "/images";

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;

	public void update(BlogVo vo) {
		blogDao.update(vo);
	}

	public BlogVo getId(String id) {
		return blogDao.getId(id);
	}

	public String restore(MultipartFile multipartFile) {

		String url = "";
		try {
			if (multipartFile == null) {
				return url;
			}

			String originalFilename = multipartFile.getOriginalFilename();
			String saveFileName = generateSaveFilename(
					originalFilename.substring(originalFilename.lastIndexOf('.') + 1));
			long fileSize = multipartFile.getSize();

			System.out.println("###############" + originalFilename);
			System.out.println("###############" + saveFileName);
			System.out.println("###############" + fileSize);

			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();

			url = URL_PREFIX + "/" + saveFileName;

		} catch (IOException e) {
			throw new FileuploadException();
		}

		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";

		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

	public void insert(CategoryVo vo) {
		categoryDao.insert(vo);

	}

	public Map<String, Object> getAll(String id, Long categoryNo, Long postNo) {

		BlogVo blogVo = blogDao.getBlogInfo(id);
		PostVo postVo = postDao.viewPost(postNo);
		System.out.println(postVo);
		List<PostVo> listPostVo = postDao.getPostList(categoryNo);
		List<CategoryVo> listCategoryVo = categoryDao.getCategoryList(id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogId", id);
		map.put("blog", blogVo);
		map.put("post", postVo);
		map.put("postList", listPostVo);
		map.put("categoryList", listCategoryVo);
		map.put("currentPost", postNo);
		map.put("currentCategory", categoryNo);

		return map;
	}
}
