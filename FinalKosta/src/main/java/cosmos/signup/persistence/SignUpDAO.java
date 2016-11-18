package cosmos.signup.persistence;

import cosmos.signup.domain.SignUpVO;

public interface SignUpDAO {
	public void insertMember(SignUpVO vo)throws Exception;
	public String selectMember(String memberID)throws Exception;
	public void updateMember(SignUpVO vo)throws Exception;
}
