package cosmos.signup.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.signup.domain.SignUpVO;
import cosmos.signup.persistence.SignUpDAO;

@Service
public class SignUpServiceImpl implements SignUpService {
	@Inject
	private SignUpDAO signupDao;
	
	@Override
	public void insertMember(SignUpVO vo)throws Exception{
		signupDao.insertMember(vo);
	}

	@Override
	public SignUpVO selectMember(String memberID) throws Exception {
		return signupDao.selectMember(memberID);
	}

	/*@Override
	public int chkId(String memberID) throws Exception {
		return signupDao.chkId(memberID);
	}*/
	
	
}
