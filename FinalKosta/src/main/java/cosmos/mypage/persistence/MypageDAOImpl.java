package cosmos.mypage.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.mypage.domain.UserVO;

@Repository
public class MypageDAOImpl implements MypageDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "cosmos.mappers.MypageMapper";
	
	
	@Override
	public void update(UserVO vo) throws Exception {
		sqlSession.update(namespace+".update", vo);
	}

}
