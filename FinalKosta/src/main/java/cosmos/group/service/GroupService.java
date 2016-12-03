package cosmos.group.service;

import java.util.List;

import cosmos.group.domain.GroupVO;
import cosmos.group.domain.GroupsVO;
import cosmos.group.domain.Invite;
import cosmos.group.domain.InviteVO;
import cosmos.login.domain.LoginVO;


public interface GroupService {
	
	public List<GroupVO> currentLoginMemberPrintService(String searchVal)throws Exception;
	public List<InviteVO> inviteListPrintService(LoginVO vo) throws Exception;
	public void insertMemberIvitecnt(String[] receives)throws Exception;
	public void insertInviteMember(Invite invite)throws Exception;
	public int inviteCountService(LoginVO vo) throws Exception;
	public void rejectInvite(String inviteID, String memberID)throws Exception;
	public void acceptInvite(String inviteID, String memberID)throws Exception;
	public void joinGroup(String groupID, String memberID)throws Exception;
/*	public GroupsVO checkNull(InviteVO vo)throws Exception;
*/}
