package cosmos.group.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.group.domain.GroupVO;
import cosmos.group.domain.GroupsVO;
import cosmos.group.domain.Invite;
import cosmos.group.domain.InviteVO;
import cosmos.login.domain.LoginVO;

@Repository
public class GroupDAOImpl implements GroupDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "cosmos.mappers.GroupMapper";

	@Override
	public List<GroupVO> currentLoginMemberPrintService() throws Exception {
		return sqlSession.selectList(namespace + ".currentList");
	}

	@Override
	public List<InviteVO> inviteListPrintService(LoginVO vo) throws Exception {
		return sqlSession.selectList(namespace + ".inviteList", vo);
	}

	@Override
	public void insertMemberIvitecnt(String[] receives) throws Exception {
		for (int i = 0; i < receives.length; i++) {
			String str = receives[i];
			sqlSession.update(namespace + ".updateInvitecnt", str);
		}
	}

	@Override
	public void insertInviteMember(Invite invite) throws Exception {
		sqlSession.insert(namespace + ".invite", invite);
	}

	@Override
	public int inviteCount(LoginVO vo) throws Exception {
		return sqlSession.selectOne(namespace + ".inviteCount", vo);
	}

	@Override
	public void rejectInvite(String inviteID, String memberID) throws Exception {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("inviteID", inviteID);
		paramMap.put("memberID", memberID);
		paramMap.put("result", "★reject");

		sqlSession.update(namespace + ".rejectInvite", paramMap);
	}

	@Override
	public void acceptInvite(String inviteID, String memberID) throws Exception {
		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("inviteID", inviteID);
		paramMap.put("memberID", memberID);
		paramMap.put("result", "★accept");

		sqlSession.update(namespace + ".acceptInvite", paramMap);
	}

	@Override
	public void joinGroup(String groupID, String memberID) throws Exception {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("groupID", groupID);
		paramMap.put("memberID", memberID);

		for (int i = 1; i <= 6; i++) {
			String team = "team" + i;
			paramMap.put("team", team);
			System.out.println("z: " + groupID);
			System.out.println("z: " + memberID);
			System.out.println("z: " + team);
			String teamIsNull = sqlSession.selectOne(namespace + ".checkNull", paramMap);

			System.out.println(teamIsNull);

			if (teamIsNull == null) {
				sqlSession.update(namespace + ".joinGroup", paramMap);
				break;
			}
		}

	}
}