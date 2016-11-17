package cosmos.subjective.controller;

import java.awt.Component;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value="main", method=RequestMethod.GET)
	public String se(){
		return "subjective/subjective_main";
	}
	
	static List<SubjectiveVO>subjectiveList;
	static List<SubjectiveVO>subjectiveSuccessList;
	static List<SubjectiveVO>subjectiveFailList;
	static int count;

	@RequestMapping(value="/subjectiveSelect", method=RequestMethod.POST)
	public String subjectiveSelect(Model model, SubjectiveVO vo)throws Exception{
		if(vo.getSubj_Categori().equals("sort-list")){
			JOptionPane.showMessageDialog(null, "카테고리를 선택해주세요");
			return "subjective/subjective_main";
		}else if(vo.getSubj_Level().equals("sort-list")){
			JOptionPane.showMessageDialog(null, "난이도를 선택해주세요");
			return "subjective/subjective_main";
		}else{
			subjectiveList = service.selectSubjective(vo);
			model.addAttribute("subjectiveSelect", subjectiveList.get(count));
			return "subjective/subjective_main";
		}
	}
	
	@RequestMapping(value="/subjectiveCheck", method=RequestMethod.GET)
	public void ajaxCheckSubjective(@RequestParam("subjectiveQuestId") String subjectiveQuestId)throws Exception{
		
	}
	

	//컴파일러
	@ResponseBody
	@RequestMapping(value = "/compile", method=RequestMethod.POST)
	public String ajaxCompile(@RequestParam("wc_code") String wc_code)throws Exception{
		String wc_result = "";
		if(wc_code !=null && !(wc_code.equals(""))){
			System.out.println(wc_code);
			wc_result = compileService.compileResult(wc_code);
			System.out.println(wc_result);
		}
		return 	wc_result;
	}

}
