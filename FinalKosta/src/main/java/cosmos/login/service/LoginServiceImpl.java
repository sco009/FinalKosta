package cosmos.login.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.login.domain.LoginVO;
import cosmos.login.dto.LoginDTO;
import cosmos.login.persistence.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	private LoginDAO dao;
	//d
	@Override
	public void check(LoginVO loginVO) throws Exception {
		dao.check(loginVO);
		
	}
}
