package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(CategoryVo vo) {
		int count = sqlSession.insert("category.insert", vo);
		return count == 1;
	}

	public List<CategoryVo> getList(String id) {
		List<CategoryVo> result = sqlSession.selectList("category.getList", id);
		return result;
	}

	public Long getNo(CategoryVo vo) {
		return sqlSession.selectOne("category.getNo", vo);
	}

	public List<CategoryVo> list(String id) {
		List<CategoryVo> result = sqlSession.selectList("category.list", id);
		return result;
	}

	public Boolean delete(Long no) {
		int count =sqlSession.delete("category.delete", no);
		return count == 1;
	}

	public List<CategoryVo> getCategoryList(String id) {
		List<CategoryVo> result = sqlSession.selectList("category.getCategoryList", id);
		return result;
	}

}
