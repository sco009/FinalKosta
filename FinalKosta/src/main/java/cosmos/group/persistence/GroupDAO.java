package cosmos.group.persistence;

import java.util.List;

import cosmos.group.domain.GroupVO;
import cosmos.group.domain.GroupsVO;
import cosmos.group.domain.Invite;
import cosmos.group.domain.InviteVO;
import cosmos.login.domain.LoginVO;

public interface GroupDAO {

	public List<GroupVO> currentLoginMemberPrintService() throws Exception;
	public List<InviteVO> inviteListPrintService(LoginVO vo) throws Exception;
	public void insertMemberIvitecnt(String[] receives)throws Exception;
	public void insertInviteMember(Invite invite) throws Exception;
	public int inviteCount(LoginVO vo) throws Exception;
	public void rejectInvite(String inviteID, String memberID)throws Exception;
	public void acceptInvite(String inviteID, String memberID)throws Exception;
	public void joinGroup(String groupID, String memberID)throws Exception;
	//public S checkNull(InviteVO vo)throws Exception;
}
