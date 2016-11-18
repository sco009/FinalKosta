package cosmos.ranking.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.ranking.domain.AlgoRankingVO;
import cosmos.ranking.domain.CodeRankingVO;
import cosmos.ranking.persistence.RankingDAO;

@Service
public class RankingServiceImpl implements RankingService {
	@Inject
	private RankingDAO dao;
	
	@Override
	public List<AlgoRankingVO> allAlgoRanking() {
		return dao.allAlgoRanking();
	}

	@Override
	public List<CodeRankingVO> allCodeRanking() {
		return dao.allCodeRanking();
	}

	@Override
	public void updateAlgoRanking(AlgoRankingVO algoVO) {
		dao.updateAlgoRanking(algoVO);
	}

	@Override
	public void updateCodeRanking(CodeRankingVO codeVO) {
		dao.updateCodeRanking(codeVO);
	}


}
