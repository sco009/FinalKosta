/*package cosmos.multiple.controller;

import java.util.List;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cosmos.multiple.domain.MultipleVO;
import cosmos.multiple.service.MultipleService;

@Controller
@RequestMapping("/multiple/*")
public class MultipleController {

	@Inject
	private MultipleService service;

	@RequestMapping(value = "/multiple", method = RequestMethod.GET)
	public String mainGET(MultipleVO vo) throws Exception {
		return "multiple/multiple_main";
	}

	@RequestMapping(value = "/multiple", method = RequestMethod.POST)
	public String mainPOST(MultipleVO vo, Model model) throws Exception {
	public String mainPOST(MultipleVO vo, Model model) throws Exception {
		String mulquestCategori = vo.getMulquestCategori();
		String mulquestLevel = vo.getMulquestLevel();
		System.out.println(mulquestCategori);
		System.out.println(mulquestLevel);
		
		
		if(mulquestCategori.equals("sort-list")){
			JOptionPane.showMessageDialog(null, "카테고리 선택해라");
			return "multiple/multiple_main";
		}else if(mulquestLevel.equals("sort-list")){
			JOptionPane.showMessageDialog(null, "난이도 선택해라");
			return "multiple/multiple_main";
		}else{

			List<MultipleVO> multipleList = service.selectMultiple(vo); 
			
			
			model.addAttribute("mulquestContent", multipleSelect.get(0).getMulquestContent()); 
			model.addAttribute("mulquestCategori", mulquestCategori);
			model.addAttribute("mulquestLevel",mulquestLevel);
			int multipleSelectCount = service.selectMultipleCount(vo); 
			
			
			model.addAttribute("multipleSelectCount", multipleSelectCount);
			System.out.println(multipleSelectCount);
			
			System.out.println(mulquestCategori);
			return "multiple/multiple_main";
			// response.sendRedirect("MultipleMain.jsp?reCheckCount=0");
		}

		
		 * model.addAttribute("multipleSelect",vo); return
		 * "redirect:multiple/multiple_main";
		 
	}

}
*/