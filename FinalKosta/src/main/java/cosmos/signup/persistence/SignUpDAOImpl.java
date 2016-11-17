package cosmos.signup.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
/*import org.springframework.beans.factory.annotation.Autowired;*/

import cosmos.signup.domain.SignUpVO;

@Repository
public class SignUpDAOImpl implements SignUpDAO {
	/*@Autowired
	private SignUpDAO signupDao;
	
	public void insertMember(SignUpVO vo)throws Exception{
		signupDao.insertMember(vo);
	}*/
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "cosmos.mappers.signupMapper";
	
	@Override
	public void insertMember(SignUpVO vo)throws Exception{
		sqlSession.insert(namespace+".insertMember",vo);
	}

	@Override
	public SignUpVO selectMember(String memberID) throws Exception {
		return sqlSession.selectOne(namespace+".selectMember", memberID);
	}

	/*@Override
	public int chkId(String memberID)throws Exception {
		return sqlSession.selectOne(namespace+".chkId", memberID);
		
	}*/
}
