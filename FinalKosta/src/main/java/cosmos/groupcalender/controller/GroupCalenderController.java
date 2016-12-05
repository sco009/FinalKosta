package cosmos.groupcalender.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cosmos.groupcalender.domain.CalenderVO;
import cosmos.groupcalender.service.GroupCalenderService;

@Controller
@RequestMapping("/groupcalender/*")
public class GroupCalenderController {
	@Inject
	public GroupCalenderService service;
	
	@RequestMapping("/groupCalenderMain")
	public String test(Model model) throws Exception{
		
		
		List<CalenderVO> list= new ArrayList<CalenderVO>();
		list.addAll(service.maincalenderlist());
		model.addAttribute("list", list);
		return "/groupcalender/groupCalenderMain";
	}
	
	@RequestMapping("/movemain")
	public String tt(Model model ,@RequestParam("mm")String mm , @RequestParam("yy")String yy) throws Exception{
		model.addAttribute("mm", mm);
		model.addAttribute("yy", yy);
		List<CalenderVO> list= new ArrayList<CalenderVO>();
		list.addAll(service.maincalenderlist());
		model.addAttribute("list", list);
		return "/groupcalender/groupCalenderMain";
	}
	
	@RequestMapping("/groupCalenderDetail")
	public String detail(Model model,@RequestParam("yy")int yy,@RequestParam("mm")int mm,@RequestParam("dd")int dd) throws Exception{
		
		model.addAttribute("list",service.selectcalenderlist(yy, mm, dd));
		model.addAttribute("yy", yy);
		model.addAttribute("mm", mm);
		model.addAttribute("dd", dd);
		
		return "/groupcalender/groupCalenderDetail";
	}
	@RequestMapping("/groupCalenderinsert")
	public String insert(Model model,@RequestParam("yy")int yy,@RequestParam("mm")int mm,@RequestParam("dd")int dd)throws Exception{
		model.addAttribute("yy", yy);
		model.addAttribute("mm", mm);
		model.addAttribute("dd", dd);
		
		return "/groupcalender/groupCalenderinsert";
	}
	@RequestMapping("/newinsert")
	public String insert(Model model,CalenderVO vo )throws Exception{
		service.newcalenderinsert(vo);
		model.addAttribute("yy", vo.getYy());
		model.addAttribute("mm", vo.getMm());
		model.addAttribute("dd", vo.getDd());	
		return "redirect:/groupcalender/groupCalenderDetail";
	}
	@RequestMapping("/calenderdelete")
	public String deletes(Model model, @RequestParam("calenderNo")int calenderNo,
			@RequestParam("yy")int yy,@RequestParam("mm")int mm,@RequestParam("dd")int dd)throws Exception{
		service.calenderdeletes(calenderNo);
		model.addAttribute("yy", yy);
		model.addAttribute("mm", mm);
		model.addAttribute("dd", dd);
		return "redirect:/groupcalender/groupCalenderDetail";
	}
	
	@RequestMapping("/updatepage")
	public String rework(Model model, @RequestParam("calenderNo")int calenderNo,
			@RequestParam("yy")int yy,@RequestParam("mm")int mm,@RequestParam("dd")int dd)throws Exception{
		
		model.addAttribute("calenderNo", calenderNo);
		model.addAttribute("yy", yy);
		model.addAttribute("mm", mm);
		model.addAttribute("dd", dd);
		return "/groupcalender/groupCalenderUpdate";
	}
	@RequestMapping("/calenderUpdate")
	public String newupdate(Model model,CalenderVO vo )throws Exception{
		
		service.calenderupdate(vo);
		model.addAttribute("yy", vo.getYy());
		model.addAttribute("mm", vo.getMm());
		model.addAttribute("dd", vo.getDd());	
		return "redirect:/groupcalender/groupCalenderDetail";
	}
	
	
}
