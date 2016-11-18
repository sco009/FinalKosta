package cosmos.login.persistence;

import java.util.Date;

import cosmos.login.domain.LoginVO;
import cosmos.login.dto.LoginDTO;

public interface LoginDAO {

	public LoginVO login(LoginDTO dto)throws Exception;
	public void keepLogin(String memberID, String sessionId, Date next);
	public LoginVO checkUserWithSessionKey(String value);
	public String currentMemberCheck(LoginDTO dto)throws Exception;
	public void insertCurrentMember(LoginDTO dto)throws Exception;
	public void currentLogoutMember(LoginVO dto)throws Exception;
}
