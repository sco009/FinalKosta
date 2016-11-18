package cosmos.ranking.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.ranking.domain.AlgoRankingVO;
import cosmos.ranking.domain.CodeRankingVO;

@Repository
public class RankingDAOImpl implements RankingDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "cosmos.mappers.rankingMapper";

	@Override
	public List<AlgoRankingVO> allAlgoRanking() {
		return sqlSession.selectList(namespace+".allAlgoRanking");
	}

	@Override
	public List<CodeRankingVO> allCodeRanking() {
		return sqlSession.selectList(namespace+".allCodeRanking");
	}

	@Override
	public void updateAlgoRanking(AlgoRankingVO algoVO) {
		sqlSession.update(namespace+".updateAlgoRanking", algoVO);
	}

	@Override
	public void updateCodeRanking(CodeRankingVO codeVO) {
		sqlSession.update(namespace+".updateCodeRanking", codeVO);
	}
}
