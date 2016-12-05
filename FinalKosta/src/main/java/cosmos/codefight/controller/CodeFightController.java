package cosmos.codefight.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cosmos.codefight.domain.CodeFightVO;
import cosmos.codefight.service.CodeFightService;

@Controller
@RequestMapping("/codeFight/*")
public class CodeFightController {
	@Inject
	private CodeFightService service;

	@RequestMapping("/codeFightMain")
	public void main() throws Exception {
		
	}

	@ResponseBody
	@RequestMapping("/fightList")
	public List<CodeFightVO> codeFightList(Model model, HttpSession session) throws Exception {
		session.setAttribute("memberId", "sco009");
		String memberId = (String) session.getAttribute("memberId");
		List<CodeFightVO> list = service.codefightList(memberId);
		model.addAttribute("codeFightList", list);
		return list;
	}

}
