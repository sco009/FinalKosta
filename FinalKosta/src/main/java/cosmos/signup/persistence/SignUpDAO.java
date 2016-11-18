package cosmos.signup.persistence;

import cosmos.signup.domain.SignUpVO;

public interface SignUpDAO {
	public void insertMember(SignUpVO vo) throws Exception;
	//public int chkId(String memberID)throws Exception;
	public SignUpVO selectMember(String memberID)throws Exception;
}
