package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class PostDao {
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(PostVo vo) {
		int count = sqlSession.insert("post.insert", vo);
		return count == 1;
	}

	public PostVo viewPost(Long postNo) {
		
		return sqlSession.selectOne("post.viewPost", postNo);
	}

	public List<PostVo> getPostList(Long categoryNo) {
		List<PostVo> list = sqlSession.selectList("post.getList", categoryNo);
		return list;
	}

}
