package cosmos.group.persistence;

import java.util.List;

import cosmos.group.domain.CurrentMemberVO;
import cosmos.group.domain.GroupVO;
import cosmos.group.domain.Invite;
import cosmos.group.domain.InviteVO;
import cosmos.login.domain.LoginVO;

public interface GroupDAO {

	public List<InviteVO> inviteListPrintService(LoginVO vo) throws Exception;
	public void insertInviteMember(Invite invite) throws Exception;
	public List<CurrentMemberVO> currentLoginMemberPrintService(String searchVal) throws Exception;
	public void insertMemberIvitecnt(String[] receives)throws Exception;
	public List<GroupVO> groupList(String userID)throws Exception;
	public void insertCreateGroups(GroupVO groupVO)throws Exception;
}
