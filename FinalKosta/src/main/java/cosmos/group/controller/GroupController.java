package cosmos.group.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cosmos.group.domain.CurrentMemberVO;
import cosmos.group.domain.GroupVO;
import cosmos.group.domain.Invite;
import cosmos.group.service.GroupService;
import cosmos.login.domain.LoginVO;

@Controller
@RequestMapping("/group/*")
public class GroupController {

	@Inject
	private GroupService GroupService;
	
	@RequestMapping(value="/creategroup")
	public String goCreateGroupPage(HttpSession session, Model model){
		
		LoginVO vo = (LoginVO)session.getAttribute("login");
		model.addAttribute("userName",vo.getMemberName());
		model.addAttribute("userID",vo.getMemberID());
		
		return "/group/createGroup";
	}
	
	@ResponseBody
	@RequestMapping(value="/currentMember")
	public ResponseEntity<List<CurrentMemberVO>> currentMember(@RequestParam("value") String searchVal) throws Exception{
		return new ResponseEntity<List<CurrentMemberVO>>(GroupService.currentLoginMemberPrintService(searchVal),HttpStatus.OK);	
	}
	
	@Transactional
	@RequestMapping(value="/inviteMember",method=RequestMethod.POST)
	public  ResponseEntity<String> inviteMember( @RequestParam("userID") String sendPerson,
												  @RequestParam("checkArray[]") String[] receives,
												  @RequestParam("contents") String contents,
												  @RequestParam("groupName") String groupName) throws Exception{
		Invite invite = new Invite();
		GroupVO groupVO = new GroupVO();
		
		invite.setSendPerson(sendPerson);
		invite.setContents(contents);
		invite.setReceive(receives);
				
		groupVO.setLeaderID(sendPerson);
		groupVO.setGroupName(groupName);
		
		GroupService.insertInviteMember(invite);
		GroupService.insertMemberIvitecnt(receives);
		GroupService.insertCreateGroups(groupVO);
		
		return new ResponseEntity<String>("good",HttpStatus.OK);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/groupList")
	public ResponseEntity<List<GroupVO>> groupList(@RequestParam("userID") String userID) throws Exception{
		
		return new ResponseEntity<List<GroupVO>>(GroupService.groupList(userID),HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/goGroupMain")
	public String goGroupMain(@RequestParam("groupID") String groupID, HttpServletRequest request) throws Exception{
		
		try {
			HttpSession session = request.getSession();
			if(groupID != null) {
		         session.setAttribute("groupID", groupID);
		         System.out.println("gid : "+session.getAttribute("groupID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/groupBoard/main";//여기 메인좀 해줏메
		
	}
}
