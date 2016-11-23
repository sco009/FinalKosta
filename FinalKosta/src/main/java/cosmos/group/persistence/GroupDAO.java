package cosmos.group.persistence;

import java.util.List;

import cosmos.group.domain.GroupVO;
import cosmos.group.domain.InviteVO;
import cosmos.login.domain.LoginVO;

public interface GroupDAO {

	public List<GroupVO> currentLoginMemberPrintService() throws Exception;
	public InviteVO inviteListPrintService(LoginVO vo) throws Exception;
}
