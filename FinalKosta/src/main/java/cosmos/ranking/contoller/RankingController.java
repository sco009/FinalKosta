package cosmos.ranking.contoller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cosmos.ranking.domain.AlgoRankingVO;
import cosmos.ranking.domain.CodeRankingVO;
import cosmos.ranking.service.RankingService;

@Controller
public class RankingController {
	@Inject
	private RankingService rankingService;
	
	@RequestMapping(value="/ranking_main", method=RequestMethod.GET)
	public String rankingGet(Model model){
		List<AlgoRankingVO> algoList = rankingService.allAlgoRanking();
		List<CodeRankingVO> codeList = rankingService.allCodeRanking();
		
		model.addAttribute("algoList", algoList);
		model.addAttribute("codeList", codeList);
		
		for(int i=0;i<codeList.size();i++){
			System.out.println("codePoint : " + codeList.get(i).getCode_point());
		}
		
		return "ranking/ranking_main";
	}
	
	@RequestMapping(value="/ranking",method=RequestMethod.GET)
	public String updateRanking(){
		List<AlgoRankingVO> algoRanking = rankingService.allAlgoRanking();
		List<CodeRankingVO> codeRanking = rankingService.allCodeRanking();
		int ranking = 1;
		
		for (int i = 0; i < algoRanking.size(); i++) {
			algoRanking.get(i).setAlgo_ranking(ranking);
			rankingService.updateAlgoRanking(algoRanking.get(i));
		}

		for (int i = 1; i < algoRanking.size(); i++) {
			if (algoRanking.get(i).getAlgo_point() == algoRanking.get(i-1).getAlgo_point()) {
				ranking = algoRanking.get(i-1).getAlgo_ranking();
				algoRanking.get(i).setAlgo_ranking(ranking);
			} else {
				algoRanking.get(i).setAlgo_ranking(
						algoRanking.get(i-1).getAlgo_ranking() + 1);
			}
			rankingService.updateAlgoRanking(algoRanking.get(i));
		}
		
		//ranking 1로 초기화
		ranking = 1;
		
		for (int i = 0; i < codeRanking.size(); i++) {
			codeRanking.get(i).setCode_ranking(ranking);
			rankingService.updateCodeRanking(codeRanking.get(i));
		}

		for (int i = 1; i < codeRanking.size(); i++) {
			if (codeRanking.get(i).getCode_point() == codeRanking.get(i-1).getCode_point()) {
				ranking = codeRanking.get(i-1).getCode_point();
				codeRanking.get(i).setCode_ranking(ranking);
			} else {
				codeRanking.get(i).setCode_ranking(
						codeRanking.get(i-1).getCode_ranking() + 1);
			}
			rankingService.updateCodeRanking(codeRanking.get(i));
		}
		return "redirect:/ranking_main";
	}

}
