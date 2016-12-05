package cosmos.ranking.persistence;

import java.util.List;

import cosmos.ranking.domain.AlgoRankingVO;
import cosmos.ranking.domain.CodeRankingVO;

public interface RankingDAO {
	public List<AlgoRankingVO> allAlgoRanking();
	
	public List<CodeRankingVO> allCodeRanking();
	
	public void updateAlgoRanking(AlgoRankingVO algoVO);
	
	public void updateCodeRanking(CodeRankingVO codeVO);
}
