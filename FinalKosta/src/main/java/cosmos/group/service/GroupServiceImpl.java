package cosmos.group.service;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.group.domain.CurrentMemberVO;
import cosmos.group.domain.GroupVO;
import cosmos.group.domain.Invite;
import cosmos.group.domain.InviteVO;
import cosmos.group.persistence.GroupDAO;
import cosmos.login.domain.LoginVO;
import cosmos.login.persistence.LoginDAO;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Inject
	private GroupDAO dao;

	@Override
	public List<CurrentMemberVO> currentLoginMemberPrintService(String searchVal) throws Exception {
		return dao.currentLoginMemberPrintService(searchVal);
	}
	@Override
	public void insertInviteMember(Invite invite) throws Exception {
		dao.insertInviteMember(invite);
		
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
	public List<GroupVO> groupList(String userID) throws Exception {
	
		return dao.groupList(userID);
	}
	@Override
	public void insertCreateGroups(GroupVO groupVO) throws Exception {
		dao.insertCreateGroups(groupVO);
		
	}
}
