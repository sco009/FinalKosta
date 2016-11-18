package cosmos.mypage.persistence;

import cosmos.mypage.domain.UserVO;

public interface MypageDAO {
	public void update(UserVO vo)throws Exception;
}
