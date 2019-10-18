package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;

	public void join(UserVo vo) {
		userDao.insert(vo);
		blogDao.insert(vo);//경로는 나중에 업데이트로 넣어줌
		
	}

	public UserVo getUser(UserVo vo) {
		
		return userDao.get(vo);
	}
	
	public void update(UserVo vo) {
		userDao.update(vo);
		
	}

	public UserVo getUser(Long no) {
		return userDao.get(no);
	}

	public Boolean existUser(String id) {
		return userDao.get(id) != null;
	}

	
	
	
}
