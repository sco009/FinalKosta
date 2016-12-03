package cosmos.group.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.group.domain.GroupVO;
import cosmos.group.domain.GroupsVO;
import cosmos.group.domain.Invite;
import cosmos.group.domain.InviteVO;
import cosmos.group.persistence.GroupDAO;
import cosmos.login.domain.LoginVO;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Inject
	private GroupDAO dao;

	@Override
	public List<GroupVO> currentLoginMemberPrintService(String searchVal) throws Exception {
		return dao.currentLoginMemberPrintService();
	}

	@Override
	public List<InviteVO> inviteListPrintService(LoginVO vo) throws Exception {
		return dao.inviteListPrintService(vo);
	}

	@Override
	public void insertMemberIvitecnt(String[] receives) throws Exception {
		dao.insertMemberIvitecnt(receives);
	}

	@Override
	public void insertInviteMember(Invite invite) throws Exception {
		dao.insertInviteMember(invite);
	}

	@Override
	public int inviteCountService(LoginVO vo) throws Exception {
		return dao.inviteCount(vo);
	}

	@Override
	public void rejectInvite(String inviteID, String memberID) throws Exception {
		dao.rejectInvite(inviteID, memberID);
	}

	@Override
	public void acceptInvite(String inviteID, String memberID) throws Exception {
		dao.acceptInvite(inviteID, memberID);
	}

	@Override
	public void joinGroup(String groupID, String memberID) throws Exception {
		dao.joinGroup(groupID,memberID);
		
		
	}
/*
	@Override
	public GroupsVO checkNull(InviteVO vo) throws Exception {
		return dao.checkNull(vo);
	}
	*/
	
}