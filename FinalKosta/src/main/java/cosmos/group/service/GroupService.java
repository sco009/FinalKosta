package cosmos.group.service;

import java.util.List;

import cosmos.group.domain.GroupVO;
import cosmos.group.domain.InviteVO;
import cosmos.login.domain.LoginVO;


public interface GroupService {

	public List<GroupVO> currentLoginMemberPrintService()throws Exception;
	public InviteVO inviteListPrintService(LoginVO vo) throws Exception;
}
