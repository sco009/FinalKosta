package cosmos.signup.controller;

import javax.inject.Inject;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cosmos.signup.domain.SignUpVO;
import cosmos.signup.service.SignUpService;

@Controller
public class SignUpController {
	
	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);
	
	@Inject
	private SignUpService service;
	
	@RequestMapping(value="signup/signup_form", method=RequestMethod.GET)
	public void signup_formGET(SignUpVO vo, Model model)throws Exception{
		logger.info("register get....");
		System.out.println("signup GET");
		
	}
	
	@RequestMapping(value="signup/signup_form", method=RequestMethod.POST)
	public String signup_formPOST(SignUpVO vo, Model model)throws Exception{
		logger.info("regist post....");
		logger.info(vo.toString());
		
		service.insertMember(vo);
		System.out.println("signup POST");
		model.addAttribute("result", "success");
		
		return "../login/login";
	}
	
	
	@RequestMapping(value="signup/confirmId", method=RequestMethod.GET)
	public void confirmIdGET(@RequestParam("memberID") String id,SignUpVO vo, Model model)throws Exception{		
		String result = service.selectMember(id);
		
		model.addAttribute("memberID", id);
		model.addAttribute("result", result);
	}
	

	@RequestMapping(value="signup/confirmId",method=RequestMethod.POST)
	public String confirmIdPOST(@RequestParam("memberID") String ID, Model model)throws Exception{		
		
		return "/signup/confirmId";
	}
	
	/*@RequestMapping(value="signup/update_form", method=RequestMethod.GET)
	public void update_formGET(@ModelAttribute("vo") SignUpVO vo, Model model)throws Exception{
		logger.info("update get....");
	}
	
	@RequestMapping(value="signup/update_form", method=RequestMethod.POST)
	public String update_formPOST(SignUpVO vo, RedirectAttributes rttr)throws Exception{
		logger.info("update post....");
		logger.info(vo.toString());
		
		service.updateMember(vo);
		//model.addAttribute("result", "success");
		rttr.addAttribute("memberID", vo.getMemberID());
		rttr.addAttribute("memberPw", vo.getMemberPw());
		rttr.addAttribute("memberName", vo.getMemberName());
		rttr.addAttribute("memberPhoneNum", vo.getMemberPhoneNum());
		rttr.addAttribute("memberEmail", vo.getMemberEmail());
		
		return "/main/signup/update_form";
	}*/
		
	
}
