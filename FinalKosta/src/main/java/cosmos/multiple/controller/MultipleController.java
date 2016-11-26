package cosmos.multiple.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cosmos.multiple.domain.MultipleChoice;
import cosmos.multiple.domain.MultiplePoint;
import cosmos.multiple.domain.MultipleVO;
import cosmos.multiple.service.MultipleService;

@Controller
@RequestMapping("/multiple/*")
public class MultipleController {
	static int index = 0; // 문제 를 담은 리스트의 인덱스
	static int checkcount = 0;
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
	static int successPoint=0;
	static String memberId="";
	static int totalProgress =0;
	static int successProgress=0;

	@Inject
	private MultipleService service;

	@RequestMapping(value = "/multiple", method = RequestMethod.GET)
	public String mainGET(MultipleVO vo) throws Exception {
		return "multiple/multiple_main";
	}

	@RequestMapping(value = "/multiple", method = RequestMethod.POST)
	public String mainPOST(MultipleVO vo, Model model) throws Exception {
		mulquestCategori = vo.getMulquestCategori();//선택된 카테고리 값
		mulquestLevel = vo.getMulquestLevel();//선택된 난이도 값
		
			model.addAttribute("signal", 0); //다음, 결과보기 버튼 활성화를 위한 시그널
			failList = new ArrayList<String>();
			successList = new ArrayList<String>();
			index = 0;
			multipleSelectCount = 0;
			checkcount = 0;
			successPoint=0;
		
		if (mulquestCategori.equals("sort-list")) {
			JOptionPane.showMessageDialog(null, "카테고리 선택해라");
			return "multiple/multiple_main";
		} else if (mulquestLevel.equals("sort-list")) {
			JOptionPane.showMessageDialog(null, "난이도 선택해라");
			return "multiple/multiple_main";
		} else {

			multiplelist = service.selectMultiple(vo);
			multipleSelectCount = multiplelist.size();

			multipleSelect = multiplelist.get(index);
			multipleChoiceSelect = service.selectMultipleChoice(multipleSelect.getMultipleChoiceId());
			model.addAttribute("multipleSelect", multipleSelect);
			model.addAttribute("multipleChoiceSelect", multipleChoiceSelect);
			model.addAttribute("multipleSelectCount", multipleSelectCount);

			index = 1;

			return "multiple/multiple_main";
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(String solveFailId, String solveSuccessId, Model model, HttpSession session) throws Exception {
		session.setAttribute("memberId", "HSB");
		if (solveFailId != null) {
			failList.add(solveFailId); // 틀린 문제 번호를 리스트에 담기.
		} else if (solveSuccessId != null) {
			successList.add(solveSuccessId); // 맞은 문제 번호를 리스트에 담기.
		}

		 
		if (checkcount >= 1) {
			if (index < multipleSelectCount) {
				index++;
			}
		}

		if (index == multipleSelectCount) { // 모든 문제를 풀었다면 넘어가라

			model.addAttribute("failList", failList);
			model.addAttribute("successList", successList);
			// return "redirect:/result";
			return "redirect:result";
		} // 모든문제 관한if end
		else if (index != multipleSelectCount) {
			checkcount++;

			multipleSelect = multiplelist.get(index);
			multipleChoiceSelect =service.selectMultipleChoice(multipleSelect.getMultipleChoiceId());
			if (index == multipleSelectCount - 1) {
				model.addAttribute("signal", 1);
			} else {
				model.addAttribute("signal", 0);
			}
			model.addAttribute("multipleSelect", multipleSelect);
			model.addAttribute("multipleChoiceSelect", multipleChoiceSelect);
		}
		return "multiple/multiple_main";
	}

	// @RequestMapping(value = "/result", method = RequestMethod.GET)
	@RequestMapping("/result")
	public String resultGET(Model model, HttpSession session) throws Exception {

		if (failList.size() > 0) {
			for (int i = 0; i < failList.size(); i++) {
				reMultiple.add(service.reMultiple(failList.get(i)));
			}
		}

		if (successList.size() > 0) {
			for (int i = 0; i < successList.size(); i++) {
				pointMultiple.add(service.reMultiple(successList.get(i)));
			}
			successPoint = (pointMultiple.get(0).getMulquestPoint() * successList.size()); // 정답인
			// 문제의
			// Point값*맞춘갯수
			// 뽑아오기.

			if (session.getAttribute("memberId") != null) { //접속된 회원을 넘겨주기위해
				memberId = (String) session.getAttribute("memberId");
				model.addAttribute("successPoint", successPoint);
				model.addAttribute("memberId", memberId);
			}//포인트+회원ID값 넘기기 위한 if END

		}

		model.addAttribute("reMultiple", reMultiple);

		failCount = failList.size(); // 틀린갯수 변수에 담기
		successCount = successList.size(); // 맞은갯수 변수에 담기
		totalProgress = failCount + successCount; // 진행바를 위해 총갯수 변수에 담기
		successProgress = (100 * successCount) / totalProgress; // 진행바를 위해
																	// 총갯수에 대한
																	// 퍼센티지 변수에
																	// 담기

		model.addAttribute("successProgress", successProgress);
		model.addAttribute("failList", failList);//틀린문제 리스트
		model.addAttribute("successList", successList);//맞은문제 리스트

//		pointMultiple = new ArrayList<MultipleVO>();
//		reMultiple = new ArrayList<MultipleVO>(); // 저장된 오답들을 초기화한다.
//		failList = new ArrayList<String>(); // 마지막 문제 까지 재설정없이 다 풀면 static
//		successList = new ArrayList<String>();// list 변수들을 초기화 해준다.
		return "multiple/multiple_result";
	}

	
	@RequestMapping(value="/multiplePoint", method=RequestMethod.POST)
	public String point(MultiplePoint multiplePoint)throws Exception{
		System.out.println("mulPoint : "+ multiplePoint.getMemberId());
		
		service.pointInsert(multiplePoint);
		pointMultiple = new ArrayList<MultipleVO>();
		reMultiple = new ArrayList<MultipleVO>(); // 저장된 오답들을 초기화한다.
		failList = new ArrayList<String>(); // 마지막 문제 까지 재설정없이 다 풀면 static
		successList = new ArrayList<String>();// list 변수들을 초기화 해준다.
		return "multiple/multiple_main"; 
	}
	
}
