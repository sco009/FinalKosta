package cosmos.group.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cosmos.group.domain.GroupVO;
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
	public Object currentMember() throws Exception{
		List<GroupVO> list = GroupService.currentLoginMemberPrintService();
		
		return list;
	}
}
