package cosmos.ranking.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.ranking.domain.MultipleRankingVO;
import cosmos.ranking.domain.RankingVO;
import cosmos.ranking.domain.SubjectRankingVO;
import cosmos.ranking.persistence.RankingDAO;

@Service
public class RankingServiceImpl implements RankingService {
	@Inject
	private RankingDAO dao;

	@Override
	public List<MultipleRankingVO> allMultipleRanking() {
		return dao.allMultipleRanking();
	}

	@Override
	public List<SubjectRankingVO> allSubjectRanking() {
		return dao.allSubjectRanking();
	}

	@Override
	public void updateMultipleRanking(MultipleRankingVO multiVO) {
		dao.updateMultipleRanking(multiVO);
	}

	@Override
	public void updateSubjectRanking(SubjectRankingVO subVO) {
		dao.updateSubjectRanking(subVO);
	}

	@Override
	public RankingVO selectRanking(String memberID) {
		return dao.selectRanking(memberID);
	}


}
