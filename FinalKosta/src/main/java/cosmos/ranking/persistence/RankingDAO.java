package cosmos.ranking.persistence;

import java.util.List;

import cosmos.ranking.domain.MultipleRankingVO;
import cosmos.ranking.domain.RankingVO;
import cosmos.ranking.domain.SubjectRankingVO;

public interface RankingDAO {
	public List<MultipleRankingVO> allMultipleRanking();
	
	public List<SubjectRankingVO> allSubjectRanking();
	
	public void updateMultipleRanking(MultipleRankingVO multiVO);
	
	public void updateSubjectRanking(SubjectRankingVO subVO);
	
	public RankingVO selectRanking(String memberID);
}
