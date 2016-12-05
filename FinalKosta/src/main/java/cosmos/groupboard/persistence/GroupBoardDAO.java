package cosmos.groupboard.persistence;

import java.util.List;

import cosmos.groupboard.domain.GroupBoardVO;
import cosmos.groupboard.domain.TeamVO;

public interface GroupBoardDAO {

	List<GroupBoardVO> groupBoardList(String groupId) throws Exception;
	void boardInsert(GroupBoardVO VO)throws Exception;
	void boardUpdate(GroupBoardVO VO)throws Exception;
	void boardDelete(String groupBoardId)throws Exception;
	List<TeamVO> groupMember(String groupId)throws Exception;
	List<GroupBoardVO> scrumCount(String groupId)throws Exception;
	String dateView(String groupBoardId)throws Exception;
}
