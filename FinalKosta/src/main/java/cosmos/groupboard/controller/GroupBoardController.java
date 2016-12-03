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

@Controller
@RequestMapping("/test/*")
public class GroupBoardController {
	@Inject
	private GroupBoardService service;
	
	@RequestMapping("/main")
	public String main(HttpSession session){
		return "redirect:/test/start";
	}
	
	@RequestMapping("/start")
	public String start(Model model, HttpSession session) throws Exception{
		String groupId = "병지니꺼";
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
		}else if(scrumMap.get("ING")==null){
			scrumMap.put("ING", 0);
		}else if(scrumMap.get("END")==null){
			scrumMap.put("END", 0);
		}
//============================================================================================================		
		
		TeamVO member = groupMember.get(0);
		
		model.addAttribute("scrumMap", scrumMap);
		model.addAttribute("member", member);
		
		model.addAttribute("list", grouplist);
		model.addAttribute("groupId", groupId);
		
		return "/groupBoard/groupBoard_main";
	}
	
	@RequestMapping("/saveData")
	public String saveData(GroupBoardVO VO, Model model) throws Exception{
		String groupId = "병지니꺼";
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

		if (scrumMap.get("ToDo") == null) {
			scrumMap.put("ToDo", 0);
		} else if (scrumMap.get("ING") == null) {
			scrumMap.put("ING", 0);
		} else if (scrumMap.get("END") == null) {
			scrumMap.put("END", 0);
		}
//============================================================================================================	
		
		model.addAttribute("scrumMap", scrumMap);
		model.addAttribute("member", member);
		model.addAttribute("list", service.groupBoardList(groupId));
		return "/groupBoard/groupBoard_main";
	}
	
	@RequestMapping(value="/updateData", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Integer> updateData(@RequestParam("groupBoardId") String groupBoardId, 
							  @RequestParam("gBoardCategori") String gBoardCategori) throws Exception{
		String groupId = "병지니꺼";
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

		if (scrumMap.get("ToDo") == null) {
			scrumMap.put("ToDo", 0);
		} else if (scrumMap.get("ING") == null) {
			scrumMap.put("ING", 0);
		} else if (scrumMap.get("END") == null) {
			scrumMap.put("END", 0);
		}
//============================================================================================================	

		
		
		
		return scrumMap;
	}
	
	
	@RequestMapping("/deleteData")
	@ResponseBody
	public HashMap<String, Integer> deleteData(@RequestParam("groupBoardId") String groupBoardId)throws Exception{
		String groupId = "병지니꺼";
		service.boardDelete(groupBoardId);
		
//============================================================================================================		
//카테고리에 대한 카운트 값을 리스트로 뽑아와서 HashMap으로 넣어준다. 단 ToDo, ING, END 중 하나라도 없으면 0값으로 초기화 해서 넣어준다.
		List<GroupBoardVO> scrumList = service.scrumCount(groupId);
		HashMap<String, Integer> scrumMap = new HashMap<String, Integer>();
		for (int i = 0; i < scrumList.size(); i++) {
			scrumMap.put(scrumList.get(i).getgBoardCategori(), scrumList.get(i).getgBoardCount());
		}

		if (scrumMap.get("ToDo") == null) {
			scrumMap.put("ToDo", 0);
		} else if (scrumMap.get("ING") == null) {
			scrumMap.put("ING", 0);
		} else if (scrumMap.get("END") == null) {
			scrumMap.put("END", 0);
		}
//============================================================================================================	

		
		return scrumMap;
	}
	
}
