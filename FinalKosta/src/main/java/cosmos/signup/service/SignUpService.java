package cosmos.signup.service;

import cosmos.signup.domain.SignUpVO;

public interface SignUpService {

	public void insertMember(SignUpVO vo)throws Exception;
	public String selectMember(String memberID)throws Exception;
	public void updateMember(SignUpVO vo)throws Exception;
}
