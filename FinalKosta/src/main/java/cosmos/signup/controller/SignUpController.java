package cosmos.signup.controller;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cosmos.signup.domain.SignUpVO;
import cosmos.signup.service.SignUpService;

@Controller
@RequestMapping("/main/*")
public class SignUpController {
	
	/*@RequestMapping("/a")
	public String signup_form(SignUpVO vo)throws Exception{
		
		return "/main/signup/signup_form";
	}*/
	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);
	
	@Inject
	private SignUpService service;
	
	@RequestMapping(value="/signup/signup_form", method=RequestMethod.GET)
	public void signup_formGET(SignUpVO vo, Model model)throws Exception{
		//System.out.println("ok");
		logger.info("register get....");
		
	}
	
	@RequestMapping(value="/signup/signup_form", method=RequestMethod.POST)
	public String signup_formPOST(SignUpVO vo, Model model)throws Exception{
		logger.info("regist post....");
		logger.info(vo.toString());
		
		service.insertMember(vo);
		
		model.addAttribute("result", "success");
		
		return "/main/signup/signup_form";
	}
	
	/*@RequestMapping("/signup/chkId")
	@ResponseBody
	 public Map<String, String> chkId(String memberID) throws Exception {
	  
	  Map<String, String> resultMap = new HashMap<String, String>();
	  
	  int resultCnt = service.chkId(memberID);
	  String result = "";
	  String resultMsg = "";
	  if ( resultCnt == 0 ){
	   result = "success";
	   resultMsg = "사용가능한 아이디입니다.";
	  } else {
	   result = "failure";
	   resultMsg = "이미 사용중인 아이디입니다.";
	  }
	  
	  resultMap.put("result", result);
	  resultMap.put("resultMsg", resultMsg);
	  
	  return resultMap;
	 } */
	
	@RequestMapping(value="/signup/confirmId", method=RequestMethod.GET)
	public void confirmIdGET(SignUpVO vo, Model model, String memberID)throws Exception{
		//System.out.println("ok");
		logger.info("register get....");
		
	}
	
	@RequestMapping(value="/signup/confirmId", method=RequestMethod.POST)
	public String confirmIdPOST(SignUpVO vo, Model model, String memberID)throws Exception{
		logger.info("regist post....");
		logger.info(vo.toString());
		
		service.selectMember(memberID);
		
		model.addAttribute("result", "success");
		
		return "redirect:/main/signup/signup_form";
	}
}
