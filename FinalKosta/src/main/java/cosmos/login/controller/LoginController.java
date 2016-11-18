package cosmos.login.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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
@RequestMapping("/login/*")
public class LoginController {
	
	@Inject
	private LoginService service;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGET(@ModelAttribute("dto") LoginDTO dto) {
		System.out.println("접속");
		return "/login/login";
	}
	
	@RequestMapping(value="/loginCheck", method=RequestMethod.POST)
	public void loginCheck(LoginDTO dto, HttpSession session, Model model) throws Exception{
		LoginVO vo = service.login(dto);
		String name = service.currentMemberCheck(dto);
		System.out.println(name);
		if(name != null){//이미 로그인되어있구나
			System.out.println("이미 로그인중");
			return;
		}else{//로그인이 안되어있을때
			dto.setMemberName(vo.getMemberName());
			service.insertCurrentMember(dto);
		}
		
		if(vo == null) {
			return;
		}
		
		model.addAttribute("loginVO", vo);
	}
	
	@RequestMapping(value="/log_main", method=RequestMethod.GET)
	public String logMain()throws Exception {
		return "/login/log_main";
	}

}