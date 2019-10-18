package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(UserVo vo) throws UserDaoException {
		int count = sqlSession.insert("blog.insert", vo);
		return count == 1;

	}

	public Boolean update(BlogVo vo) {
		
		int count = sqlSession.update("blog.update", vo);

		return count == 1;
	}

	public BlogVo getId(String id) {
		return sqlSession.selectOne("blog.getById", id);
	}

	public BlogVo getBlogInfo(String id) {
		return sqlSession.selectOne("blog.getBlogInfo", id);
	}
}
