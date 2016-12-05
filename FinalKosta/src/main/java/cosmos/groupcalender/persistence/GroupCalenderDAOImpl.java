package cosmos.groupcalender.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.groupcalender.domain.CalenderVO;

@Repository
public class GroupCalenderDAOImpl implements GroupCalenderDAO {
	@Inject
	private SqlSession sqlSession; 
	
	private static final String namespace="cosmos.mappers.groupcalenderMapper";
	
	@Override
	public List<CalenderVO> maincalenderlist(String groupid) throws Exception {
		return sqlSession.selectList(namespace+".maincalenderlist",groupid);
	}

	@Override
	public List<CalenderVO> selectcalenderlist(int yy, int mm, int dd,String groupid) throws Exception {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		
		paramMap.put("yy", yy);
		paramMap.put("dd", dd);
		paramMap.put("mm", mm);
		paramMap.put("groupid", groupid);
		return sqlSession.selectList(namespace+".selectcalenderlist", paramMap);
	}

	@Override
	public void newcalenderinsert(CalenderVO vo) throws Exception {
		sqlSession.insert(namespace+".newcalenderinsert", vo);
	}

	@Override
	public void calenderdeletes(int calenderNo) throws Exception {
		sqlSession.delete(namespace+".calenderdeletes", calenderNo);
	}

	@Override
	public void calenderupdate(CalenderVO vo) throws Exception {
		sqlSession.update(namespace+".calenderupdate", vo);
	}

}
