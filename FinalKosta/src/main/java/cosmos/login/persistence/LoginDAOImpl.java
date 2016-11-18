package cosmos.login.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.login.domain.LoginVO;
import cosmos.login.dto.LoginDTO;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace="cosmos.mappers.LoginMapper";
	
	@Override
	public LoginVO login(LoginDTO dto) throws Exception {
		return sqlSession.selectOne(namespace+".login", dto);
	}

	@Override
	public String currentMemberCheck(LoginDTO dto) throws Exception {
		return sqlSession.selectOne(namespace+".currentLoginMember", dto);
	}

	@Override
	public void insertCurrentMember(LoginDTO dto) throws Exception {
		sqlSession.insert(namespace+".insertCurrentMember", dto);
		
	}

	@Override
	public void currentLogoutMember(LoginVO dto) throws Exception {
		sqlSession.delete(namespace+".currentLogoutMember", dto);
	}

	@Override
	public void keepLogin(String memberID, String sessionId, Date next) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("memberID", memberID);
		paramMap.put("sessionId", sessionId);
		paramMap.put("next", next);
		
		sqlSession.update(namespace+".keepLogin", paramMap);
	}

	@Override
	public LoginVO checkUserWithSessionKey(String value) {
		return sqlSession.selectOne(namespace+".checkUserWithSessionKey", value);
	}
	
}
