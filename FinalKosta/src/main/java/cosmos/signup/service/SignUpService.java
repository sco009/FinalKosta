package cosmos.signup.service;

import cosmos.signup.domain.SignUpVO;

public interface SignUpService {

	public void insertMember(SignUpVO vo)throws Exception;
	//public int chkId(String memberID)throws Exception;
	public SignUpVO selectMember(String memberID)throws Exception;
}
