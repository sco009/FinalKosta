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
		System.out.println("DB PW : " + user.getMemberPw());
		
		String inputPass = user.getMemberPw();
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
		user.setMemberPw(encode);
		
		mypageService.update(user);
		
		return "/login/log_main";
	}
	
}
