package cosmos.ranking.contoller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cosmos.login.domain.LoginVO;
import cosmos.ranking.domain.MultipleRankingVO;
import cosmos.ranking.domain.RankingVO;
import cosmos.ranking.domain.SubjectRankingVO;
import cosmos.ranking.service.RankingService;

@Controller
@RequestMapping(value="/ranking/*")
public class RankingController {
	@Inject
	private RankingService rankingService;
	
	@RequestMapping(value="/ranking_main", method=RequestMethod.GET)
	public String rankingGet(Model model,HttpSession session){
		LoginVO login = (LoginVO)session.getAttribute("login");
		String memberID = login.getMemberID();
		List<MultipleRankingVO> multiList = rankingService.allMultipleRanking();
		List<SubjectRankingVO> subList = rankingService.allSubjectRanking();
		RankingVO ranking = rankingService.selectRanking(memberID);
		
		model.addAttribute("multiList", multiList);
		model.addAttribute("subList", subList);
		model.addAttribute("ranking", ranking);
		
		return "ranking/ranking_main";
	}
	
	@RequestMapping(value="")
	public String updateRanking(){
		List<MultipleRankingVO> multiRanking = rankingService.allMultipleRanking();
		List<SubjectRankingVO> subRanking = rankingService.allSubjectRanking();
		int ranking = 1;
		
		for (int i = 0; i < multiRanking.size(); i++) {
			multiRanking.get(i).setMultiple_ranking(ranking);
			rankingService.updateMultipleRanking(multiRanking.get(i));
		}

		for (int i = 1; i < multiRanking.size(); i++) {
			if (multiRanking.get(i).getMultiple_point()== multiRanking.get(i-1).getMultiple_point()) {
				ranking = multiRanking.get(i-1).getMultiple_ranking();
				multiRanking.get(i).setMultiple_ranking(ranking);
			} else {
				multiRanking.get(i).setMultiple_ranking(
						multiRanking.get(i-1).getMultiple_ranking() + 1);
			}
			rankingService.updateMultipleRanking(multiRanking.get(i));
		}
		
		//ranking 1로 초기화
		ranking = 1;
		
		for (int i = 0; i < subRanking.size(); i++) {
			subRanking.get(i).setSubject_ranking(ranking);
			rankingService.updateSubjectRanking(subRanking.get(i));
		}

		for (int i = 1; i < subRanking.size(); i++) {
			if (subRanking.get(i).getSubject_point() == subRanking.get(i-1).getSubject_point()) {
				ranking = subRanking.get(i-1).getSubject_point();
				subRanking.get(i).setSubject_ranking(ranking);
			} else {
				subRanking.get(i).setSubject_ranking(
						subRanking.get(i-1).getSubject_ranking() + 1);
			}
			rankingService.updateSubjectRanking(subRanking.get(i));
		}
		return "redirect:/ranking/ranking_main";
	}

}
