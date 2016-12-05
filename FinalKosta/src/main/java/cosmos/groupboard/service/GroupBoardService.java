package cosmos.groupboard.service;

import java.util.List;

import cosmos.groupboard.domain.GroupBoardVO;
import cosmos.groupboard.domain.TeamVO;

public interface GroupBoardService {
	public List<GroupBoardVO>groupBoardList(String groupId)throws Exception;
	public void boardInsert(GroupBoardVO VO)throws Exception;
	public void boardUpdate(GroupBoardVO VO)throws Exception;
	public void boardDelete(String groupBoardId)throws Exception;
	public List<TeamVO> groupMember(String groupId)throws Exception;
	public List<GroupBoardVO> scrumCount(String groupId)throws Exception;
	public String dateView(String groupBoardId)throws Exception;
}
