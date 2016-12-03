package cosmos.group.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.group.domain.CurrentMemberVO;
import cosmos.group.domain.GroupVO;
import cosmos.group.domain.Invite;
import cosmos.group.domain.InviteVO;
import cosmos.login.domain.LoginVO;
@Repository
public class GroupDAOImpl implements GroupDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="cosmos.mappers.GroupMapper";

	@Override
	public List<CurrentMemberVO> currentLoginMemberPrintService(String searchVal) throws Exception {
		return sqlSession.selectList(namespace+".currentList","%"+searchVal+"%");
	}

	@Override
	public void insertInviteMember(Invite invite) throws Exception {
		
		sqlSession.insert(namespace+".invite",invite);
	}

	@Override
	public List<InviteVO> inviteListPrintService(LoginVO vo) throws Exception {
		
		return sqlSession.selectList(namespace+".inviteList",vo);
	}

	@Override
	public void insertMemberIvitecnt(String[] receives) throws Exception {
		for(int i=0; i<receives.length; i++){
			String str = receives[i];
			sqlSession.update(namespace+".updateInvitecnt",str);
		}
		
		
	}

	@Override
	public List<GroupVO> groupList(String userID) throws Exception {
		
		return sqlSession.selectList(namespace+".groupList",userID);
	}

	@Override
	public void insertCreateGroups(GroupVO groupVO) throws Exception {
		sqlSession.insert(namespace+".insertCreateGroups",groupVO);
		
	}

	
}
