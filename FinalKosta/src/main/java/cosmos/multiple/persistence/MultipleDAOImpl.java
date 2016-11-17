/*package cosmos.multiple.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.multiple.domain.MultipleChoice;
import cosmos.multiple.domain.MultiplePoint;
import cosmos.multiple.domain.MultipleVO;

@Repository
public class MultipleDAOImpl implements MultipleDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="cosmos.mappers.multipleMapper";
<<<<<<< HEAD
	
=======

	@Override
	public List<MultipleVO> selectMultiple(MultipleVO vo) throws Exception {
		return sqlSession.selectList(namespace+".selectMultiple",vo);
	}

	@Override
	public MultipleChoice selectMultipleChoice(String multipleChoiceId) throws Exception {
		return sqlSession.selectOne(namespace+".selectMultipleChoice",multipleChoiceId);
	}

	@Override
	public int selectMultipleCount(MultipleVO vo) throws Exception {
			return sqlSession.selectOne(namespace+".selectMultipleCount",vo);
	}

//	@Override
//	public int pointInsert(MultiplePoint multiplePoint) throws Exception {
//		// TODO Auto-generated method stub
//		return sqlSession.selectOne(namespace+".pointInsert",multiplePoint);
//	}
>>>>>>> refs/remotes/kab/kbj

}
*/