package cosmos.login.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cosmos.login.domain.LoginVO;
import cosmos.login.dto.LoginDTO;
import cosmos.login.service.LoginService;

@Controller
@RequestMapping("/user/*")
public class LoginController {
	
	@Inject
	private LoginService service;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGET(@ModelAttribute("dto") LoginDTO dto) {
		System.out.println("접속");
		return "/login/login";
	}
	
	/*@RequestMapping(value="/check", method=RequestMethod.POST)
	public void loginCheck(@RequestParam("memberID") String memberID, @RequestParam("memberPw") String memberPw){
		System.out.println(memberID);
		System.out.println(memberPw);
		
		
	}*/
	
	@RequestMapping(value="/check", method=RequestMethod.POST)
	public void loginCheck(LoginVO vo) throws Exception{
		System.out.println(vo.getMemberID());
		System.out.println(vo.getMemberPw());
		
		service.check(vo);
	}
	
	/*@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, Model model) throws Exception {
		
		LoginVO vo = service.login(dto);
		if(vo == null) {
			return;
		}
		
		model.addAttribute("userVO", vo);
	}*/
}