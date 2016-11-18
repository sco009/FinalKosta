package cosmos.education.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EducationController {
	
	@RequestMapping("education")
	public String education(){
		
		return "/education/education_Main";
	}
	
}
