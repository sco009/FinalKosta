package cosmos.group.persistence;

import java.util.List;

import cosmos.group.domain.GroupVO;
import cosmos.login.domain.LoginVO;

public interface GroupDAO {

	public List<GroupVO> currentLoginMemberPrintService()throws Exception;

}
