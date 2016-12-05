package cosmos.ranking.service;

import java.util.List;

import cosmos.ranking.domain.AlgoRankingVO;
import cosmos.ranking.domain.CodeRankingVO;

public interface RankingService {
	public List<AlgoRankingVO> allAlgoRanking();
	
	public List<CodeRankingVO> allCodeRanking();
	
	public void updateAlgoRanking(AlgoRankingVO algoVO);
	
	public void updateCodeRanking(CodeRankingVO codeVO);
}
