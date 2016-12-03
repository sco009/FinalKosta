package cosmos.groupboard.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.groupboard.domain.GroupBoardVO;
import cosmos.groupboard.domain.TeamVO;

@Repository
public class GroupBoardDAOImpl implements GroupBoardDAO {
	@Inject 
	private SqlSession sqlSession;
	
	private static final String namespace="cosmos.mappers.groupboardMapper";

	@Override
	public List<GroupBoardVO> groupBoardList(String groupId) throws Exception {
		return sqlSession.selectList(namespace+".groupBoardList", groupId);
	}

	@Override
	public void boardInsert(GroupBoardVO VO) throws Exception {
		sqlSession.insert(namespace+".boardInsert", VO);
	}

	@Override
	public void boardUpdate(GroupBoardVO VO) throws Exception {
		sqlSession.update(namespace+".boardUpdate", VO);
	}

	@Override
	public void boardDelete(String groupBoardId) throws Exception {
		sqlSession.delete(namespace+".boardDelete", groupBoardId);
	}

	@Override
	public List<TeamVO> groupMember(String groupId) throws Exception {
		return sqlSession.selectList(namespace+".groupMember", groupId);
		
	}

	@Override
	public List<GroupBoardVO> scrumCount(String groupId) throws Exception {
		return sqlSession.selectList(namespace+".scrumCount", groupId);
	}
	
	

}
