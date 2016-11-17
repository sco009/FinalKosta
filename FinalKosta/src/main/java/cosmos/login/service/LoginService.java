package cosmos.login.service;

import cosmos.login.domain.LoginVO;
import cosmos.login.dto.LoginDTO;

public interface LoginService {
	public void check(LoginVO loginVO)throws Exception;
}
