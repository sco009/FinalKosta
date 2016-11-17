package cosmos.subjective.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.subjective.domain.SubjectiveVO;

@Repository
public class SubjectiveDAOImpl implements SubjectiveDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="cosmos.mappers.subjectiveMapper";
	
	@Override
	public List<SubjectiveVO> selectSubjective(SubjectiveVO VO)throws Exception{
		return sqlSession.selectList(namespace+".selectSubjective", VO);
	}
	
	@Override
	public SubjectiveVO choiceSubjective(String subjectiveId)throws Exception{
		return sqlSession.selectOne(namespace+".choiceSubjective", subjectiveId);
	}
}
