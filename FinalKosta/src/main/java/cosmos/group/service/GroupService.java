package cosmos.group.service;

import java.util.List;

import cosmos.group.domain.GroupVO;
import cosmos.group.domain.Invite;
import cosmos.login.domain.LoginVO;


public interface GroupService {

	public List<GroupVO> currentLoginMemberPrintService(String searchVal)throws Exception;
	public void insertInviteMember(Invite invite)throws Exception;

}
