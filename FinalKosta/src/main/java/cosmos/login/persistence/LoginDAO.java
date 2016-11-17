package cosmos.login.persistence;

import cosmos.login.domain.LoginVO;

public interface LoginDAO {

	public void check(LoginVO loginVO)throws Exception;
	
}
