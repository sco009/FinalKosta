package cosmos.groupboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.groupboard.domain.GroupBoardVO;
import cosmos.groupboard.domain.TeamVO;
import cosmos.groupboard.persistence.GroupBoardDAO;

@Service
public class GroupBoardServiceImpl implements GroupBoardService {
	@Inject
	private GroupBoardDAO dao;
	
	@Override
	public List<GroupBoardVO> groupBoardList(String groupId) throws Exception {
		return dao.groupBoardList(groupId);
	}

	@Override
	public void boardInsert(GroupBoardVO VO) throws Exception {
		dao.boardInsert(VO);
	}

	@Override
	public void boardUpdate(GroupBoardVO VO) throws Exception {
		dao.boardUpdate(VO);
	}

	@Override
	public void boardDelete(String groupBoardId) throws Exception {
		dao.boardDelete(groupBoardId);
	}

	@Override
	public List<TeamVO> groupMember(String groupId) throws Exception {
		return dao.groupMember(groupId);
	}

	@Override
	public List<GroupBoardVO> scrumCount(String groupId) throws Exception {
		return dao.scrumCount(groupId);
	}

	@Override
	public String dateView(String groupBoardId) throws Exception {
		return dao.dateView(groupBoardId);
	}
}
