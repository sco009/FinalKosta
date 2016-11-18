package cosmos.multiple.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cosmos.multiple.domain.MultipleChoice;
import cosmos.multiple.domain.MultipleVO;
import cosmos.multiple.service.MultipleService;

@Controller
@RequestMapping("/multiple/*")
public class MultipleController {
	static int index = 0; // 문제 를 담은 리스트의 인덱스
	static List<MultipleVO> multiplelist = null;
	static MultipleVO multipleSelect;
	static MultipleChoice multipleChoiceSelect;
	static int multipleSelectCount = 0;
	static String mulquestCategori = null;
	static String mulquestLevel = null;
	static ArrayList<String> failList = new ArrayList<String>();
	static ArrayList<String> successList = new ArrayList<String>();
	static ArrayList<MultipleVO> reMultiple = new ArrayList<MultipleVO>();
	static ArrayList<MultipleVO> pointMultiple = new ArrayList<MultipleVO>();
	static int successCount = 0;
	static int failCount = 0;

	@Inject
	private MultipleService service;

	@RequestMapping(value = "/multiple", method = RequestMethod.GET)
	public String mainGET(MultipleVO vo) throws Exception {
		return "multiple/multiple_main";
	}

	@RequestMapping(value = "/multiple", method = RequestMethod.POST)
	public String mainPOST(MultipleVO vo, Model model) throws Exception {
		mulquestCategori = vo.getMulquestCategori();
		mulquestLevel = vo.getMulquestLevel();
		if (index != 0) {
			failList = new ArrayList<String>();
			successList = new ArrayList<String>();
			index = 0;
		}
		if (mulquestCategori.equals("sort-list")) {
			JOptionPane.showMessageDialog(null, "카테고리 선택해라");
			return "multiple/multiple_main";
		} else if (mulquestLevel.equals("sort-list")) {
			JOptionPane.showMessageDialog(null, "난이도 선택해라");
			return "multiple/multiple_main";
		} else {

			multiplelist = service.selectMultiple(vo);
			multipleSelectCount = service.selectMultipleCount(vo);

			multipleSelect = multiplelist.get(index);
			multipleChoiceSelect = service.selectMultipleChoice(multipleSelect.getMultipleChoiceId());
			model.addAttribute("multipleSelect", multipleSelect);
			model.addAttribute("multipleChoiceSelect", multipleChoiceSelect);

			model.addAttribute("multipleSelectCount", multipleSelectCount);
			/*
			 * if (checkCount < multipleSelectCount) { checkCount++; // 문제를 불러오기
			 * 위해 카운트값을 증가 시킨다.. index++; }
			 */
			//index++;
			return "multiple/multiple_main";
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	/*
	 * public String test(@RequestParam("solveFailId") String solveFailId,
	 * 
	 * @RequestParam("solveSuccessId") String solveSuccessId, Model model)
	 * throws Exception {
	 */
	public String test(String solveFailId, String solveSuccessId, Model model) throws Exception {
		
		
		if (solveFailId != null) {
			failList.add(solveFailId); // 틀린 문제 번호를 리스트에 담기.
		} else if (solveSuccessId != null) {
			successList.add(solveSuccessId); // 맞은 문제 번호를 리스트에 담기.
		}
		multipleSelect = multiplelist.get(index);
		multipleChoiceSelect = service.selectMultipleChoice(multipleSelect.getMultipleChoiceId());
		model.addAttribute("multipleSelect", multipleSelect);
		model.addAttribute("multipleChoiceSelect", multipleChoiceSelect);

		model.addAttribute("multipleSelectCount", multipleSelectCount);
		if (index < multipleSelectCount) {
			++index;
		}
		if (index == multipleSelectCount) { // 모든 문제를 풀었다면 넘어가라
			model.addAttribute("signal", index);
			model.addAttribute("failList", failList);
			model.addAttribute("successList", successList);
			//return "redirect:/result";
			return "redirect:result";
		} // 모든문제 관한if end

		return "multiple/multiple_main";
	}

	
	
	
	
	
	
	
	// @RequestMapping(value = "/result", method = RequestMethod.GET)
	@RequestMapping("/result")
	public String resultGET( Model model) throws Exception {
		/*
		 * if (solveFailId == null) { successList.add(solveSuccessId); // 마지막 문제
		 * // 맞았는지 // 틀렸는지 // 체크해서 // 리스트에 // 추가 } else if (solveSuccessId ==
		 * null) { failList.add(solveFailId); // 마지막 문제 맞았는지 // 틀렸는지 체크해서 //
		 * 리스트에 추가 }
		 */
		if (failList.size() > 0) {
			for (int i = 0; i < failList.size(); i++) {
				reMultiple.add(service.reMultiple(failList.get(i)));
			}
		}

//		if (successList.size() > 0) {
//			for (int i = 0; i < successList.size(); i++) {
//				pointMultiple.add(service.reMultiple(successList.get(i)));
//			}
//			int successPoint = (pointMultiple.get(0).getMulquestPoint() * successList.size()); // 정답인
																								// 문제의
																								// Point값*맞춘갯수
																								// 뽑아오기.

			
//			  if (session.getAttribute("memberID") != null) { String memberId =
//			  (String) session.getAttribute("memberID"); // 회원포인트에 // 넘기기위해 //el태그로 // 만들어 // 준다.
//			 
//				  model.addAttribute("successPoint", successPoint);
//				  model.addAttribute("memberId", memberId); }
				 
//		}

	model.addAttribute("reMultiple", reMultiple);

	failCount = failList.size(); // 틀린갯수 변수에 담기
	successCount = successList.size(); // 맞은갯수 변수에 담기
	int totalProgress = failCount + successCount; // 진행바를 위해 총갯수 변수에 담기
	int successProgress = (100 * successCount) / totalProgress; // 진행바를 위해
																// 총갯수에 대한
																// 퍼센티지 변수에
																// 담기

	model.addAttribute("successProgress", successProgress);
	model.addAttribute("failList", failList);
	model.addAttribute("successList", successList);

	pointMultiple = new ArrayList<MultipleVO>();
	reMultiple = new ArrayList<MultipleVO>(); // 저장된 오답들을 초기화한다.
	failList = new ArrayList<String>(); // 마지막 문제 까지 재설정없이 다 풀면 static
	successList = new ArrayList<String>();// list 변수들을 초기화 해준다.
	return "multiple/multiple_result";
	}

}
