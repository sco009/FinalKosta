package cosmos.groupboard.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cosmos.groupboard.domain.GroupBoardVO;
import cosmos.groupboard.domain.TeamVO;
import cosmos.groupboard.service.GroupBoardService;
import cosmos.login.domain.LoginVO;

@Controller
@RequestMapping("/groupBoard/*")
public class GroupBoardController {
	@Inject
	private GroupBoardService service;
	
	@RequestMapping("/main")
	public String main(HttpSession session){
		return "redirect:/groupBoard/start";
	}
	
	@RequestMapping("/start")
	public String start(Model model, HttpSession session) throws Exception{
		String groupId = (String)session.getAttribute("groupID");
		LoginVO vo =  (LoginVO)session.getAttribute("login");
		String memberId = vo.getMemberID();
		List<GroupBoardVO>grouplist =  service.groupBoardList(groupId);
		List<TeamVO> groupMember = service.groupMember(groupId);
		
//============================================================================================================		
//카테고리에 대한 카운트 값을 리스트로 뽑아와서 HashMap으로 넣어준다. 단 ToDo, ING, END 중 하나라도 없으면 0값으로 초기화 해서 넣어준다.
		List<GroupBoardVO>scrumList = service.scrumCount(groupId);
		HashMap<String, Integer>scrumMap = new HashMap<String, Integer>();
		for (int i = 0; i < scrumList.size(); i++) {
			scrumMap.put(scrumList.get(i).getgBoardCategori(), scrumList.get(i).getgBoardCount());
		}
		
		if(scrumMap.get("ToDo")==null){
			scrumMap.put("ToDo", 0);
		}
		
		if(scrumMap.get("ING")==null){
			scrumMap.put("ING", 0);
		}
		
		if(scrumMap.get("END")==null){
			scrumMap.put("END", 0);
		}
//============================================================================================================		
		
		TeamVO member = groupMember.get(0);
		
		model.addAttribute("scrumMap", scrumMap);
		model.addAttribute("member", member);
		
		model.addAttribute("list", grouplist);
		model.addAttribute("groupId", groupId);
		model.addAttribute("memberId", memberId);
		
		return "/groupBoard/groupBoard_main";
	}
	
	@RequestMapping("/saveData")
	public String saveData(GroupBoardVO VO, Model model, HttpSession session) throws Exception{
		LoginVO vo =  (LoginVO)session.getAttribute("login");
		String memberId = vo.getMemberID();
		String groupId = (String)session.getAttribute("groupID");
		service.boardInsert(VO);
		List<TeamVO> groupMember = service.groupMember(groupId);
		TeamVO member = groupMember.get(0);
		
		
//============================================================================================================		
//카테고리에 대한 카운트 값을 리스트로 뽑아와서 HashMap으로 넣어준다. 단 ToDo, ING, END 중 하나라도 없으면 0값으로 초기화 해서 넣어준다.
		List<GroupBoardVO> scrumList = service.scrumCount(groupId);
		HashMap<String, Integer> scrumMap = new HashMap<String, Integer>();
		for (int i = 0; i < scrumList.size(); i++) {
			scrumMap.put(scrumList.get(i).getgBoardCategori(), scrumList.get(i).getgBoardCount());
		}

		if(scrumMap.get("ToDo")==null){
			scrumMap.put("ToDo", 0);
		}
		
		if(scrumMap.get("ING")==null){
			scrumMap.put("ING", 0);
		}
		
		if(scrumMap.get("END")==null){
			scrumMap.put("END", 0);
		}
//============================================================================================================	
		model.addAttribute("memberId", memberId);
		model.addAttribute("groupId", groupId);
		model.addAttribute("scrumMap", scrumMap);
		model.addAttribute("member", member);
		model.addAttribute("list", service.groupBoardList(groupId));
		return "/groupBoard/groupBoard_main";
	}
	
	@RequestMapping(value="/updateData", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Integer> updateData(@RequestParam("groupBoardId") String groupBoardId, 
							  @RequestParam("gBoardCategori") String gBoardCategori, HttpSession session) throws Exception{
		String groupId = (String)session.getAttribute("groupID");
		GroupBoardVO vo = new GroupBoardVO();
		vo.setgBoardCategori(gBoardCategori);
		vo.setGroupBoardId(groupBoardId);
		
		service.boardUpdate(vo);
//============================================================================================================		
//카테고리에 대한 카운트 값을 리스트로 뽑아와서 HashMap으로 넣어준다. 단 ToDo, ING, END 중 하나라도 없으면 0값으로 초기화 해서 넣어준다.
		List<GroupBoardVO> scrumList = service.scrumCount(groupId);
		HashMap<String, Integer> scrumMap = new HashMap<String, Integer>();
		for (int i = 0; i < scrumList.size(); i++) {
			scrumMap.put(scrumList.get(i).getgBoardCategori(), scrumList.get(i).getgBoardCount());
		}

		if(scrumMap.get("ToDo")==null){
			scrumMap.put("ToDo", 0);
		}
		
		if(scrumMap.get("ING")==null){
			scrumMap.put("ING", 0);
		}
		
		if(scrumMap.get("END")==null){
			scrumMap.put("END", 0);
		}
//============================================================================================================	

		
		
		
		return scrumMap;
	}
	
	
	@RequestMapping("/deleteData")
	@ResponseBody
	public HashMap<String, Integer> deleteData(@RequestParam("groupBoardId") String groupBoardId, HttpSession session)throws Exception{
		String groupId = (String)session.getAttribute("groupID");
		service.boardDelete(groupBoardId);
		
//============================================================================================================		
//카테고리에 대한 카운트 값을 리스트로 뽑아와서 HashMap으로 넣어준다. 단 ToDo, ING, END 중 하나라도 없으면 0값으로 초기화 해서 넣어준다.
		List<GroupBoardVO> scrumList = service.scrumCount(groupId);
		HashMap<String, Integer> scrumMap = new HashMap<String, Integer>();
		for (int i = 0; i < scrumList.size(); i++) {
			scrumMap.put(scrumList.get(i).getgBoardCategori(), scrumList.get(i).getgBoardCount());
		}

		if(scrumMap.get("ToDo")==null){
			scrumMap.put("ToDo", 0);
		}
		
		if(scrumMap.get("ING")==null){
			scrumMap.put("ING", 0);
		}
		
		if(scrumMap.get("END")==null){
			scrumMap.put("END", 0);
		}
//============================================================================================================	

		
		return scrumMap;
	}
	
	@RequestMapping("/dateView")
	@ResponseBody
	public String dateView(@RequestParam("groupBoardId")String groupBoardId)throws Exception{
		return service.dateView(groupBoardId);
	}
	
	@RequestMapping("/realTimeScrum")
	@ResponseBody
	public List<TeamVO> realTimeScrum(HttpSession session)throws Exception{
		String groupId = (String)session.getAttribute("groupID");
		List<TeamVO>groupMember = service.groupMember(groupId);
		return groupMember;
	}
};
