package cosmos.group.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String goCreateGroupPage(){
		return "/group/createGroup";
	}
	
	@ResponseBody
	@RequestMapping(value="/currentMember")
	public ResponseEntity<List<GroupVO>> currentMember(@RequestParam("value") String searchVal) throws Exception{
		
		return new ResponseEntity<List<GroupVO>>(GroupService.currentLoginMemberPrintService(searchVal),HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping(value="/inviteMember",method=RequestMethod.POST)
	public  ResponseEntity<String> inviteMember(@RequestParam("userId") String sendPerson,
												  @RequestParam("checkArray[]") String[] receives,
												  @RequestParam("contents") String contents,
												  @RequestParam("groupName") String groupName) throws Exception{
		Invite invite = new Invite();
		
		invite.setSendPerson(sendPerson);
		invite.setContents(contents);
		invite.setReceive(receives);
		GroupService.insertInviteMember(invite);

		//node.js로 초대했다는 url보내는 소스 작성할것
		
		
		
		
		return new ResponseEntity<String>("good",HttpStatus.OK);
	}
}
