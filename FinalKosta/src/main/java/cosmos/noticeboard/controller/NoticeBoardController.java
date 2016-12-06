package cosmos.noticeboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cosmos.noticeboard.domain.NoticeVO;
import cosmos.login.domain.LoginVO;
import cosmos.noticeboard.domain.NoticePageMaker;
import cosmos.noticeboard.domain.NoticeSearchCriteria;
import cosmos.noticeboard.service.NoticeService;

@Controller
@RequestMapping("/notice/*")
public class NoticeBoardController {
	@Inject
	private NoticeService service;
	
	@RequestMapping(value = "notice/register_form", method=RequestMethod.GET)
	public void registerGET(NoticeVO vo, Model model, LoginVO lvo)throws Exception{
	}
	
	@RequestMapping(value = "notice/register_form", method=RequestMethod.POST)
	public String registerPOST(NoticeVO vo, RedirectAttributes rttr)throws Exception{
		service.regist(vo);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/notice/listPage";
	}
	
	@RequestMapping(value="/listPage", method = RequestMethod.GET)
	public void listCri(@ModelAttribute("cri") NoticeSearchCriteria cri, Model model)throws Exception{
		model.addAttribute("list", service.listCri(cri));
		NoticePageMaker pageMaker = new NoticePageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") NoticeSearchCriteria cri, Model model)throws Exception{
		System.out.println(service.read(bno));
		model.addAttribute("NoticeVO",service.read(bno));
	}

	@RequestMapping(value="/removePage", method = RequestMethod.POST)
	public String removePage(@RequestParam("bno") int bno, 
			@ModelAttribute("cri") NoticeSearchCriteria cri, RedirectAttributes rttr)
			throws Exception{
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/notice/listPage";
	}
	
	@RequestMapping(value="/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("bno")int bno,@ModelAttribute("cri") NoticeSearchCriteria cri, Model model)throws Exception{
		model.addAttribute("NoticeVO",service.read(bno));
	}
	
	
	@RequestMapping(value="/modifyPage", method = RequestMethod.POST)
	public String modifyPOST(NoticeVO vo, NoticeSearchCriteria cri, RedirectAttributes rttr)throws Exception{
		System.out.println(vo);
		service.modify(vo);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
				
		return "redirect:/notice/listPage";
	}
	
	@RequestMapping("/getAttach/{bno}")
	   @ResponseBody
	   public List<String> getAttach(@PathVariable("bno") Integer bno) throws Exception{
	      return service.getAttach(bno);
	   }
}
