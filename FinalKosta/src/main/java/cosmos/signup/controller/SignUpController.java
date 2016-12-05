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
				
		String inputPass = vo.getMemberPw(); // 사용자가 입력한 비밀번호
		String ip_Random =""; // 짝수자리에 랜덤숫자를 삽입한 비밀번호
		String changePass = ""; // 암호와되어 DB에 저장될 비밀번호
		cosmos.login.controller.XOR test = new cosmos.login.controller.XOR();

		// 짝수자리에 랜덤숫자 삽입
		for(int i=0; i<=inputPass.length()-1; i++) {
			int random = (int)(Math.random()*9)+1;
			int max = inputPass.length();
			if(i<max)
				ip_Random += inputPass.substring(i, i+1)+random;
		}
				
		char ch1[] = ip_Random.toCharArray();
		
		// 아스키코드로 변환해서 +7
		for (int i = 0; i < ch1.length; i++) {
			changePass += (char)(ch1[i]+7);
		}
		String encode = test.XOR(changePass);
		vo.setMemberPw(encode);
		service.insertMember(vo);
		
		System.out.println("signup POST");
/*		model.addAttribute("result", "success");
*/		
		return "/login/login";
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
}

