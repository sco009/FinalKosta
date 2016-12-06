package cosmos.codetrace.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cosmos.codetrace.domain.CTBoardVO;
import cosmos.codetrace.domain.Criteria;
import cosmos.codetrace.domain.PageMaker;
import cosmos.codetrace.domain.SearchCriteria;
import cosmos.codetrace.service.CodetraceService;
import cosmos.login.domain.LoginVO;

@Controller
@RequestMapping("/codetrace/*")
public class CodetraceController {
	private static final Logger logger = LoggerFactory.getLogger(CodetraceController.class);
	
	@Inject
	private CodetraceService service;
	
	@RequestMapping(value = "codetrace/register_form", method=RequestMethod.GET)
	public void registerGET(CTBoardVO vo, Model model, HttpSession session)throws Exception{
		logger.info("register get...");
	}
	
	@RequestMapping(value = "codetrace/register_form", method=RequestMethod.POST)
	public String registerPOST(CTBoardVO vo, RedirectAttributes rttr)throws Exception{
		logger.info("register post...");
		logger.info(vo.toString());
		
		service.regist(vo);
		
		rttr.addFlashAttribute("result", "success");
		
		
		return "redirect:/codetrace/listPage";
	}
	
	/*@RequestMapping(value="/listAll", method = RequestMethod.GET)
	public void listAll(Model model)throws Exception{
		logger.info("show all list........");
		model.addAttribute("list", service.listAll());
	}*/
	
	@RequestMapping(value="/listPage", method = RequestMethod.GET)
	public void listCri(@ModelAttribute("cri") SearchCriteria cri, Model model)throws Exception{
		model.addAttribute("list", service.listCri(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		//pageMaker.setTotalCount(14);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	/*@RequestMapping(value="/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model)throws Exception{
		model.addAttribute(service.read(bno));
	}*/
	@RequestMapping(value="/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model, HttpSession session)throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	/*@RequestMapping(value="/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr)
			throws Exception{
		
		service.remove(bno);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/codetrace/listAll";
	}*/
	
	/*@RequestMapping(value="/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr)
			throws Exception{
		
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/codetrace/listPage";
	}*/
	
	@RequestMapping(value="/removePage", method = RequestMethod.POST)
	public String removePage(@RequestParam("bno") int bno, 
			@ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr)
			throws Exception{
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/codetrace/listPage";
	}
	
	
	
	/*@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model)throws Exception{
		model.addAttribute(service.read(bno));
	}*/
	
	/*@RequestMapping(value="/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("bno")int bno,@ModelAttribute("cri") Criteria cri, Model model)throws Exception{
		model.addAttribute(service.read(bno));
	}*/
	
	@RequestMapping(value="/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("bno")int bno,@ModelAttribute("cri") SearchCriteria cri, Model model)throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	/*@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modifyPOST(CTBoardVO vo, RedirectAttributes rttr)throws Exception{
		logger.info("mod post.....");
		service.modify(vo);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/codetrace/listAll";
	}*/
	
	/*@RequestMapping(value="/modifyPage", method = RequestMethod.POST)
	public String modifyPOST(CTBoardVO vo, Criteria cri, RedirectAttributes rttr)throws Exception{
		logger.info("mod post.....");
		service.modify(vo);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/codetrace/listPage";
	}*/
	
	@RequestMapping(value="/modifyPage", method = RequestMethod.POST)
	public String modifyPOST(CTBoardVO vo, SearchCriteria cri, RedirectAttributes rttr)throws Exception{
		logger.info("mod post.....");
		service.modify(vo);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		logger.info(rttr.toString());
		
		return "redirect:/codetrace/listPage";
	}
	
	/*@RequestMapping(value="/listCri", method = RequestMethod.GET)
	public void listAll(Criteria cri, Model model)throws Exception{
		logger.info("criteria...");
		
		model.addAttribute("list", service.listCriteria(cri));
	}*/
	
	@RequestMapping("/getAttach/{bno}")
	   @ResponseBody
	   public List<String> getAttach(@PathVariable("bno") Integer bno) throws Exception{
	      return service.getAttach(bno);
	   }
	
	@RequestMapping(value = "codetrace/codetrace", method=RequestMethod.GET)
	public void codeTraceGET(CTBoardVO vo, Model model)throws Exception{
		logger.info("codetrace get...");
	}
	
}
