package cosmos.codefight.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.codefight.domain.CodeFightVO;

@Repository
public class CodeFightDAOImpl implements CodeFightDAO {
	@Inject 
	private SqlSession sqlSession;
	
	private static final String namespace="cosmos.mappers.codefightMapper";
	
	@Override
	public List<CodeFightVO>codefightList(String memberId){
		return sqlSession.selectList(namespace+".codefightList", memberId);
	}
	
}
