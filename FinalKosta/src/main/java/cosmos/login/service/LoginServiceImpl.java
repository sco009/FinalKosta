package cosmos.login.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.login.domain.LoginVO;
import cosmos.login.dto.LoginDTO;
import cosmos.login.persistence.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	private LoginDAO dao;

	@Override
	public LoginVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public String currentMemberCheck(LoginDTO dto) throws Exception {
		return dao.currentMemberCheck(dto);
	}

	@Override
	public void insertCurrentMember(LoginDTO dto) throws Exception {
		dao.insertCurrentMember(dto);
	}

	@Override
	public void currentLogoutMember(LoginVO vo) throws Exception {
		dao.currentLogoutMember(vo);
	}

	@Override
	public void keepLogin(String memberID, String sessionId, Date next) throws Exception {
		dao.keepLogin(memberID, sessionId, next);
	}
	
	@Override
	public LoginVO checkLoginBefore(String value) {
		return dao.checkUserWithSessionKey(value);
	}
}
