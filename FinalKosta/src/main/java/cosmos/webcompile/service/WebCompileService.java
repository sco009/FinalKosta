package cosmos.webcompile.service;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;

public interface WebCompileService {

	public String compileResult(String wc_code)throws Exception;
}
