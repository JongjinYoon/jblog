package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	public List<CategoryVo> getList(String id) {
		return categoryDao.getList(id);
	}

	public Long getNo(CategoryVo vo) {
		
		return categoryDao.getNo(vo);
	}

	public List<CategoryVo> list(String id) {
		return categoryDao.list(id);
	}

	public Boolean delete(Long no) {
		return categoryDao.delete(no);
	}

	
	
	
}
