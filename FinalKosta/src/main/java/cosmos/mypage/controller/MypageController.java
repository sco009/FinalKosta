package cosmos.mypage.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cosmos.login.domain.LoginVO;
import cosmos.mypage.domain.UserVO;
import cosmos.mypage.service.MypageService;

@Controller
@RequestMapping("/signup/*")
public class MypageController {
	@Inject
	MypageService mypageService;
	
	@RequestMapping(value = "/update_form",method=RequestMethod.GET)
	public void updateGET(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO)session.getAttribute("login");
		
		String memberID = loginVO.getMemberID();
		String memberPw = loginVO.getMemberPw();
		String memberName = loginVO.getMemberName();
		String memberPhoneNum = loginVO.getMemberPhoneNum();
		String memberEmail = loginVO.getMemberEmail();
		
		model.addAttribute("memberPhoneNum", memberPhoneNum);
		model.addAttribute("memberID", memberID);
		model.addAttribute("memberName", memberName);
		model.addAttribute("memberPw", memberPw);
		model.addAttribute("memberEmail", memberEmail);
		
	}
	
	@RequestMapping(value = "/update_form",method=RequestMethod.POST)
	public String updatePOST(UserVO user) throws Exception{
		mypageService.update(user);
		
		return "/login/log_main";
	}
	
}
