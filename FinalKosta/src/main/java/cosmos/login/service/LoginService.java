package cosmos.login.service;

import java.util.Date;

import cosmos.login.domain.LoginVO;
import cosmos.login.dto.LoginDTO;

public interface LoginService {
	public LoginVO login(LoginDTO dto)throws Exception;
	public void keepLogin(String memberID, String sessionId, Date next) throws Exception;
	public LoginVO checkLoginBefore(String value);
	public String currentMemberCheck(LoginDTO dto)throws Exception;
	public void insertCurrentMember(LoginDTO dto)throws Exception;
	public void currentLogoutMember(LoginVO dto)throws Exception;
}
