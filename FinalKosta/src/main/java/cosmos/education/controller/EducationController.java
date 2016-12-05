package cosmos.education.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cosmos.education.domain.EducationManagerVO;
import cosmos.education.domain.EducationVO;
import cosmos.education.service.EducationService;

@Controller
public class EducationController {
	@Inject
	private EducationService service;
	
	
	@RequestMapping("/education")
	public String education(Model model,@RequestParam("memberID")String memberID) {
		
		model.addAttribute("memberID", memberID);
		
		return "/education/education_Main";
	}

	@RequestMapping(value="educations",method=RequestMethod.GET)
	public String education_Introduce(Model model,@RequestParam("dataClassify")String dataClassify
			,@RequestParam("memberID")String memberID,@RequestParam("pages")int pages) throws Exception {
		model.addAttribute("memberID", memberID);
		model.addAttribute("pages", pages);
		model.addAttribute("list", service.selectmemberID(memberID));
		
		
		
		model.addAttribute("dataClassify", dataClassify);
		return "/education/"+dataClassify;
	}
	
	@RequestMapping(value="educations",method=RequestMethod.POST)
	public String education_Introduce(EducationVO vo,Model model,@RequestParam("memberID")String memberID
			 ,@RequestParam("pages")int pages)throws Exception{
		vo.setedu_c_ID(service.selectEc_edu_c_ID());
		
		System.out.println(vo);
		
		service.inserteducation(vo);
		
		model.addAttribute("pages", pages);
		model.addAttribute("dataClassify", vo.getdataClassify());
		model.addAttribute("edu_c_ID", vo.getedu_c_ID());
		model.addAttribute("memberID",memberID);
	
		return "redirect:/educationmanager";
	}
	
	@RequestMapping("educationmanager")
	public String edcation_manager(EducationManagerVO mvo,@RequestParam("dataClassify")String dataClassify,
			@RequestParam("edu_c_ID")int edu_c_ID,Model model,@RequestParam("memberID")String memberID
			,@RequestParam("pages")int pages)throws Exception{
		mvo.setedu_c_ID(edu_c_ID);
		mvo.setEduManagerId(service.selectEc_managerid());
		service.inserteductionManager(mvo);
		
		model.addAttribute("pages", pages);
		model.addAttribute("memberID", memberID);
		model.addAttribute("dataClassify", dataClassify);
		return "redirect:/educations";
	}
	
	@RequestMapping("education_delete")
	public String education_delete(Model model,@RequestParam("dataClassify")String dataClassify
			,@RequestParam("memberID")String memberID,@RequestParam("pages")int pages,
			@RequestParam("hl_contents")String hl_contents)throws Exception{
		service.education_delete(memberID, hl_contents);
		
		model.addAttribute("memberID", memberID);
		model.addAttribute("pages", pages);
		model.addAttribute("dataClassify", dataClassify);
		return "redirect:/educations";
	}
	
}
