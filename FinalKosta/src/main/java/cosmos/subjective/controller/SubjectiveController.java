package cosmos.subjective.controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cosmos.login.domain.LoginVO;
import cosmos.subjective.domain.SubjectivePointVO;
import cosmos.subjective.domain.SubjectiveVO;
import cosmos.subjective.service.SubjectiveService;
import cosmos.webcompile.service.WebCompileService;

@Controller
@RequestMapping("/subjective")
public class SubjectiveController {
	@Inject
	private SubjectiveService service;
	@Inject
	private WebCompileService compileService;
	
	static List<SubjectiveVO> subjectiveList = new ArrayList<SubjectiveVO>();
	static List<SubjectiveVO> subjectiveSuccessList = new ArrayList<SubjectiveVO>();
	static List<SubjectiveVO> subjectiveFailList = new ArrayList<SubjectiveVO>();
	static int count = 0;

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String subjective(HttpSession session) {
		/*문제를 새로 풀때마다 초기화 해준다.*/
		count = 0;
		subjectiveList = new ArrayList<SubjectiveVO>();
		subjectiveFailList = new ArrayList<SubjectiveVO>();
		subjectiveSuccessList = new ArrayList<SubjectiveVO>();
		return "subjective/subjective_main";
	}

	@RequestMapping(value = "/subjectiveSelect", method = RequestMethod.GET)
	public String subjectiveSelect(Model model, SubjectiveVO vo) throws Exception {
			if (vo.getSubj_Categori().equals("sort-list")) {				//카테고리,난이도 선택안할시 경고창
				JOptionPane.showMessageDialog(null, "카테고리를 선택해주세요");
				return "subjective/subjective_main";
			} else if (vo.getSubj_Level().equals("sort-list")) {
				JOptionPane.showMessageDialog(null, "난이도를 선택해주세요");
				return "subjective/subjective_main";
			}else{
				
				subjectiveList = service.selectSubjective(vo);		//카테고리,난이도 선택할시 첫 문제 보내주기.
				model.addAttribute("subjectiveSelect", subjectiveList.get(count));
				return "subjective/subjective_main";
			}

	}

	@ResponseBody
	@RequestMapping(value = "/successCheck", method = RequestMethod.GET)
	public void successCheckSubjective(@RequestParam("subjectiveQuestId") String subjectiveId) throws Exception {
		SubjectiveVO choiceVO = service.choiceSubjective(subjectiveId);
		subjectiveSuccessList.add(choiceVO);
	}

	@ResponseBody
	@RequestMapping(value = "/failCheck", method = RequestMethod.GET)
	public void failCheckSubjective(@RequestParam("subjectiveQuestId") String subjectiveId) throws Exception {
		SubjectiveVO choiceVO = service.choiceSubjective(subjectiveId);
		subjectiveFailList.add(choiceVO);
	}

	@RequestMapping(value = "/subjectiveNext", method = RequestMethod.GET)	//다음 문제를 받기위한 count 증가
	public String nextSubjective() throws Exception {
			count++;
		return "redirect:/subjective/nextQuest";
	}
	
	@RequestMapping(value="/nextQuest", method=RequestMethod.GET)
	public String nextQeust(Model model) throws Exception{
		int signal = subjectiveList.size();
		if((count+1)==subjectiveList.size()){
			model.addAttribute("signal", signal);
		}
		
		model.addAttribute("subjectiveSelect", subjectiveList.get(count));
		return "subjective/subjective_main";
	}
	
	@RequestMapping("/subjectiveResult")
	public String subjectiveResult(Model model, HttpSession session){
		int successCount = subjectiveSuccessList.size();
		int failCount = subjectiveFailList.size();
		int totalCount = failCount+successCount;
		int successPoint=0;
		if(subjectiveSuccessList.size()!=0){
			successPoint=subjectiveSuccessList.get(0).getSubj_Point();
		}else{
			successPoint=subjectiveFailList.get(0).getSubj_Point();
		}
		int successProgress = (100*successCount)/totalCount;
		LoginVO vo =  (LoginVO)session.getAttribute("login");
		String memberId = vo.getMemberID();
		model.addAttribute("successPoint", successPoint);
		model.addAttribute("memberId", memberId);
		model.addAttribute("successProgress", successProgress);
		model.addAttribute("failList", subjectiveFailList);
		model.addAttribute("successList", subjectiveSuccessList);
		return "subjective/subjective_result";
	}
	
	@RequestMapping("/initialization")
	public String subjectiveInitialization(){
		return "subjective/subjective_main";
	}
	
	@RequestMapping(value="/finishSubjective", method=RequestMethod.POST)
	public String finishSubjective(SubjectivePointVO point)throws Exception{
		service.pointInsert(point);
		
		return "subjective/subjective_main";
	}
	// 컴파일러
	@ResponseBody
	@RequestMapping(value = "/compile", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxCompile(@RequestParam("wc_code") String wc_code, @RequestParam("compileCategori")String compileCategori) throws Exception {
		int wcCount=0;
		
		String copyWC=wc_code;
		int categoriLength = compileCategori.length()-1;		//for,while등 함수를 사용했는지 안했는지를 판별하기위한.
		String categori = compileCategori.substring(0, categoriLength);
		
		int firstString = wc_code.indexOf("print(")+5;
		int lastString = wc_code.indexOf(");")+1;
		String first = wc_code.substring(firstString, firstString+2);
		String last = wc_code.substring(lastString-2, lastString);
		String totalString = first+last;
		
		while(copyWC.indexOf("print(")!=-1){
			copyWC = copyWC.replaceFirst("System.out.print", "");
			wcCount++;
		}
		
		if(wc_code.indexOf(categori)==-1){
			String data = "Please observe the corresponding grammar..";
			return new ResponseEntity<String> (data,HttpStatus.BAD_REQUEST);
		}else if(totalString.equals("(\"\")")){
			String data = "Use variables in the \"print()\" statement.";
			return new ResponseEntity<String>(data,HttpStatus.BAD_REQUEST);
		}else if(wcCount>2){
			String data = "Do not use the \"print()\" statement multiple times.";
			wcCount=0;
			return new ResponseEntity<String>(data,HttpStatus.BAD_REQUEST);
		}
		
		String wc_result = "";
		if (wc_code != null && !(wc_code.equals(""))) {
			wc_result = compileService.compileResult(wc_code);
		}
		return new ResponseEntity<String>(wc_result,HttpStatus.OK);
	}

}
