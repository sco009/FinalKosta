package cosmos.login.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.databind.util.JSONPObject;

import cosmos.group.domain.CurrentMemberVO;
import cosmos.group.domain.InviteVO;
import cosmos.group.service.GroupService;
import cosmos.login.domain.LoginVO;
import cosmos.login.dto.LoginDTO;
import cosmos.login.service.LoginService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	@Inject
	private LoginService service;
	@Inject
	private GroupService gr_service;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGET(@ModelAttribute("dto") LoginDTO dto) {
		System.out.println("접속성공");
		return "/login/login";
	}
	
	@RequestMapping(value="/loginCheck", method=RequestMethod.POST)
	public void loginCheck(LoginDTO dto, HttpSession session, Model model) throws Exception{
		System.out.println("입력한 비밀번호 : " + dto.getMemberPw());
		// 화면에서 입력한 dto값을 가져와서 복호화시킨뒤에 service.login에 넣어주기

		String spass = service.password(dto); // DB에 암호화되어있는 PW
		
		String rm_Random = ""; // 짝수자리의 랜덤숫자를 제거한 비밀번호
		String originPass = ""; // 복호화된 원래의 비밀번호
		cosmos.login.controller.XOR test = new cosmos.login.controller.XOR();
		
		String decode = test.XOR(spass);
		
		for(int i=0; i<=decode.length()-1; i++) {
			if(i%2==0) {
				rm_Random += decode.substring(i, i+1);
			}
		}
		
		char ch2[] = rm_Random.toCharArray();
		
		// 아스키코드로 변환해서 -7
		for (int i = 0; i < ch2.length; i++) {
			originPass += (char)(ch2[i]-7);
		}
		
		dto.setMemberPw(originPass);
		
		if(dto.getMemberPw()==originPass) {
			dto.setMemberPw(spass);
			
			LoginVO vo = service.login(dto);
			System.out.println();
			if(vo == null) {
				
				return;
			}
		
			String name = service.currentMemberCheck(dto);
			if(name != null){//이미 로그인되어있구나
				System.out.println("이미 로그인중");
				return;
			}else{//로그인이 안되어있을때
				dto.setMemberName(vo.getMemberName());
				service.insertCurrentMember(dto);
			}
			
			model.addAttribute("loginVO", vo);
			
			if(dto.isUseCookie()) {
				int amount = 60*60*24*7;
				Date sessionLimit = new Date(System.currentTimeMillis()+(1000*amount));
				service.keepLogin(vo.getMemberID(), session.getId(), sessionLimit);
			}
		}
		
	}
	
	@RequestMapping(value="/log_main", method=RequestMethod.GET)
	public String logMain(HttpSession session, Model model)throws Exception {
		
		LoginVO vo = (LoginVO) session.getAttribute("login");
		System.out.println("접속한 사람 : " + vo.getMemberName());
		
		List<InviteVO> ivo = gr_service.inviteListPrintService(vo);
		model.addAttribute("InviteVO", ivo);
		
		return "/login/log_main";
	}
	
	@ResponseBody
	@RequestMapping(value="/invite", method=RequestMethod.GET)
	public int logMain2(HttpSession session, Model model)throws Exception {
		
		LoginVO vo = (LoginVO) session.getAttribute("login");
		
		int icount = gr_service.inviteCountService(vo);
		model.addAttribute("icount", icount);
		
		List<InviteVO> ivo = gr_service.inviteListPrintService(vo);
		model.addAttribute("InviteVO", ivo);

		return icount;
	}
	
	@ResponseBody
	@RequestMapping(value="/invitelist", method=RequestMethod.GET)
	public List<InviteVO> logMain3(HttpSession session, Model model)throws Exception {
		
		LoginVO vo = (LoginVO) session.getAttribute("login");
		
		List<InviteVO> ivo = gr_service.inviteListPrintService(vo);
		model.addAttribute("InviteVO", ivo);

		return ivo;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)throws Exception {
		LoginVO vo = (LoginVO) session.getAttribute("login");
		
		if(vo != null) {
			session.removeAttribute("login");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getMemberID(), session.getId(), new Date());
			}
		}

	      service.currentLogoutMember(vo);
	      model.addAttribute("loginVO", vo);
	      
	      
	      return "/login/logout";
	   }

	@RequestMapping(value="/acceptInvite", method=RequestMethod.GET)
	public void acceptInvite(HttpSession session, @RequestParam("inviteID") String inviteID)throws Exception {
		LoginVO vo = (LoginVO) session.getAttribute("login");
		String memberID = vo.getMemberID();
		gr_service.acceptInvite(inviteID, memberID);
		
	}
	
	@RequestMapping(value="/rejectInvite", method=RequestMethod.GET)
	public void rejectInvite(HttpSession session, @RequestParam("inviteID") String inviteID)throws Exception {
		LoginVO vo = (LoginVO) session.getAttribute("login");
		String memberID = vo.getMemberID();
		gr_service.rejectInvite(inviteID, memberID);
	}
	
	@RequestMapping(value="/joinGroup", method=RequestMethod.GET)
	public void joinGroup(HttpSession session, @RequestParam("inviteID") String inviteID,
												@RequestParam("groupID") String groupID)throws Exception {
		LoginVO vo = (LoginVO) session.getAttribute("login");
		String memberID = vo.getMemberID();
		gr_service.acceptInvite(inviteID, memberID);
		gr_service.joinGroup(groupID, memberID);
	}
}