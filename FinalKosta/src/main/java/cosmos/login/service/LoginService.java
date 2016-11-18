package cosmos.login.service;

import cosmos.login.domain.LoginVO;
import cosmos.login.dto.LoginDTO;

public interface LoginService {
	public LoginVO login(LoginDTO dto)throws Exception;
	public String currentMemberCheck(LoginDTO dto)throws Exception;
	public void insertCurrentMember(LoginDTO dto)throws Exception;
}
