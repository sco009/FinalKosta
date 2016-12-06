package cosmos.ranking.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.ranking.domain.MultipleRankingVO;
import cosmos.ranking.domain.RankingVO;
import cosmos.ranking.domain.SubjectRankingVO;

@Repository
public class RankingDAOImpl implements RankingDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "cosmos.mappers.rankingMapper";

	@Override
	public List<MultipleRankingVO> allMultipleRanking() {
		return sqlSession.selectList(namespace+".allMultipleRanking");
	}

	@Override
	public List<SubjectRankingVO> allSubjectRanking() {
		return sqlSession.selectList(namespace+".allSubjectRanking");
	}

	@Override
	public void updateMultipleRanking(MultipleRankingVO multiVO) {
		sqlSession.update(namespace+".updateMultipleRanking", multiVO);
	}

	@Override
	public void updateSubjectRanking(SubjectRankingVO subVO) {
		sqlSession.update(namespace+".updateSubjectRanking", subVO);
	}

	@Override
	public RankingVO selectRanking(String memberID) {
		return sqlSession.selectOne(namespace+".selectRanking",memberID);
	}
}
