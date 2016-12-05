package cosmos.codetrace.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.codetrace.domain.Criteria;
import cosmos.codetrace.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "cosmos.mappers.codetraceMapper"; //꼭 경로일 필요는 없지만 경로로하면 나중에 보기가 쉽다.

	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
		return session.selectList(namespace+".list", bno);
	}

	@Override
	public void r_create(ReplyVO vo) throws Exception {
		session.insert(namespace+".r_create", vo);
	}

	@Override
	public void r_update(ReplyVO vo) throws Exception {
		session.update(namespace+".r_update", vo);
	}

	@Override
	public void r_delete(Integer rno) throws Exception {
		session.delete(namespace+".r_delete", rno);
	}

	@Override
	public List<ReplyVO> r_listPage(Integer bno, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("bno", bno);
		paramMap.put("cri", cri);
		
		return session.selectList(namespace+".r_listPage", paramMap);
	}

	@Override
	public int r_count(Integer bno) throws Exception {
		return session.selectOne(namespace+".r_count", bno);
	}

	@Override
	public int getBno(Integer rno) throws Exception {
		return session.selectOne(namespace+".getBno", rno);
	}

	

	

}
