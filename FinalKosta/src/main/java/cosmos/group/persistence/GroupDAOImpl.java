package cosmos.group.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.group.domain.GroupVO;
import cosmos.login.domain.LoginVO;
@Repository
public class GroupDAOImpl implements GroupDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="cosmos.mappers.GroupMapper";

	@Override
	public List<GroupVO> currentLoginMemberPrintService() throws Exception {
		
		return sqlSession.selectList(namespace+".currentList");
	}

}
