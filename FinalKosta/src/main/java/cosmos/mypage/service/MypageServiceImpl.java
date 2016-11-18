package cosmos.mypage.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.mypage.domain.UserVO;
import cosmos.mypage.persistence.MypageDAO;

@Service
public class MypageServiceImpl implements MypageService {
	@Inject
	private MypageDAO mypageDao;
	
	
	@Override
	public void update(UserVO vo) throws Exception {
		mypageDao.update(vo);
	}

}
