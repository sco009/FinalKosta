package cosmos.login.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.login.domain.LoginVO;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace="cosmos.mappers.LoginMapper";
	
	@Override
	public void check(LoginVO loginVO) throws Exception {
		
		String name = sqlSession.selectOne(namespace+".check",loginVO);
		System.out.println(name);
	}
}
