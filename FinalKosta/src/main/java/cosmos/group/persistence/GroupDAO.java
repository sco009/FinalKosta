package cosmos.group.persistence;

import java.util.List;

import cosmos.group.domain.GroupVO;
import cosmos.group.domain.Invite;
import cosmos.login.domain.LoginVO;

public interface GroupDAO {

	public List<GroupVO> currentLoginMemberPrintService(String searchVal)throws Exception;

	public void insertInviteMember(Invite invite)throws Exception;

}
