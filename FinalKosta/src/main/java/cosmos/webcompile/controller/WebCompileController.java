package cosmos.webcompile.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cosmos.webcompile.service.WebCompileService;

@Controller
@RequestMapping("/webcompile/*")
public class WebCompileController {
	
	@Inject
	private WebCompileService compileService;
	
	@RequestMapping(value ="/main")
	public String goWebcomplieForm(){
		return 	"redirect:http://localhost:3000/webcompile";
	}
	
	@ResponseBody
	@RequestMapping(value ="/compile", method=RequestMethod.POST)
	public String ajaxCompile(@RequestParam("wc_code") String wc_code) throws Exception{
		System.out.println(wc_code);	
		
		String wc_result = "";
		if(wc_code !=null && !(wc_code.equals(""))){
			System.out.println(wc_code);
			wc_result = compileService.compileResult(wc_code);
			System.out.println(wc_result);
		}
		return 	wc_result;
	}


}
