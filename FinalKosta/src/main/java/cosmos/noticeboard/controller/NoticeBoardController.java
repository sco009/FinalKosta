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
import cosmos.noticeboard.domain.NoticePageMaker;
import cosmos.noticeboard.domain.NoticeSearchCriteria;
import cosmos.noticeboard.service.NoticeService;

@Controller
@RequestMapping("/notice/*")
public class NoticeBoardController {
	@Inject
	private NoticeService service;
	
	@RequestMapping(value ="/register", method=RequestMethod.GET)
	public void registerGet(NoticeVO vo)throws Exception{
		System.out.println("ok");
	}
	
	@RequestMapping(value ="/register",  method=RequestMethod.POST)
	public String registerPost(NoticeVO vo)throws Exception{
		service.regist(vo);
		
		return "redirect:/notice/listPage";
	}
	/*@RequestMapping(value = "/listAll",  method=RequestMethod.GET)
	public String listAll(Model model)throws Exception{
		List<BoardVO> list = service.listAll();
		model.addAttribute("list",list);
		return "/board/listAll";
		
	}*/
	
	@RequestMapping(value = "/listPage",  method=RequestMethod.GET)
	public void listCri(@ModelAttribute("cri") NoticeSearchCriteria criteria,Model model)throws Exception{
		List<NoticeVO> list = service.listCri(criteria);
		//form에서 넘어오는 값이 없어도 여기 자체에서 크리테리아 객체를 생성해서 디폴트값을 넘겨준다.
		model.addAttribute("list",list);
		NoticePageMaker maker = new NoticePageMaker();
		maker.setCri(criteria);
		maker.setTotalCount(service.listCountCriteria(criteria));
		
		model.addAttribute("pageMaker",maker);
		/*return "/board/listCri";*/
		
	}
	
	@RequestMapping(value ="/readPage", method=RequestMethod.GET)
	public String read(@RequestParam("bno") int bno, @ModelAttribute("cri") NoticeSearchCriteria cri ,Model model)throws Exception{
		NoticeVO noticeVO = service.read(bno);
		model.addAttribute("noticeVO",noticeVO);
		return "/notice/readPage";
	}
	
	@RequestMapping(value = "/modifyPage",method=RequestMethod.GET)
	public String modify(@RequestParam("bno") int bno, @ModelAttribute("cri") NoticeSearchCriteria cri ,Model model) throws Exception{
		NoticeVO noticeVO = service.read(bno);
		model.addAttribute("noticeVO",noticeVO);
		return "/notice/modifyPage";
		
	}

	@RequestMapping(value = "/modifyPage",method=RequestMethod.POST)
	public String modify(NoticeVO noticeVO, @ModelAttribute("cri") NoticeSearchCriteria cri ,RedirectAttributes rttr) throws Exception{
		service.modify(noticeVO);
		rttr.addFlashAttribute("bno",noticeVO.getBno());
		return "/notice/readPage";
		
	}
	
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno,@ModelAttribute("cri") NoticeSearchCriteria cri ,RedirectAttributes rttr) throws Exception{
		service.remove(bno);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/notice/listPage";
	}
	
	@ResponseBody
	@RequestMapping(value="/getAttach/{bno}")
	public List<String> getAttach(@PathVariable("bno") Integer bno)throws Exception{
		return service.getAttach(bno);
	}
}
