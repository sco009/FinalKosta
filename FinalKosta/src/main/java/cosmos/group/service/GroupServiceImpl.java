package cosmos.group.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.group.domain.GroupVO;
import cosmos.group.persistence.GroupDAO;
import cosmos.login.domain.LoginVO;
import cosmos.login.persistence.LoginDAO;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Inject
	private GroupDAO dao;

	@Override
	public List<GroupVO> currentLoginMemberPrintService() throws Exception {
		return dao.currentLoginMemberPrintService();
	}

}
