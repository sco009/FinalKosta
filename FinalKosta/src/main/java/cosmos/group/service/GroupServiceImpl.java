package cosmos.group.service;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.group.domain.GroupVO;
import cosmos.group.domain.Invite;
import cosmos.group.persistence.GroupDAO;
import cosmos.login.domain.LoginVO;
import cosmos.login.persistence.LoginDAO;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Inject
	private GroupDAO dao;

	@Override
	public List<GroupVO> currentLoginMemberPrintService(String searchVal) throws Exception {
		return dao.currentLoginMemberPrintService(searchVal);
	}

	

	@Override
	public void insertInviteMember(Invite invite) throws Exception {
		
		dao.insertInviteMember(invite);
		
	}
	
	

}
