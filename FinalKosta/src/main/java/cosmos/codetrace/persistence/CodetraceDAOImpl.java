package cosmos.codetrace.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.codetrace.domain.CTBoardVO;
import cosmos.codetrace.domain.Criteria;
import cosmos.codetrace.domain.SearchCriteria;

@Repository
public class CodetraceDAOImpl implements CodetraceDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "cosmos.mappers.codetraceMapper";

	@Override
	public void create(CTBoardVO vo) throws Exception {
		session.insert(namespace+".create", vo);
		
	}

	@Override
	public CTBoardVO read(Integer bno) throws Exception {
		return session.selectOne(namespace+".read", bno);
	}

	@Override
	public void update(CTBoardVO vo) throws Exception {
		session.update(namespace+".update", vo);
		
	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete(namespace+".delete", bno);
		
	}

	@Override
	public List<CTBoardVO> listAll() throws Exception {
		return session.selectList(namespace+".listAll");
	}

	@Override
	public List<CTBoardVO> listPage(int page) throws Exception {
		if(page<=0){
			page = 1;
		}
		
		page = (page - 1)*5;
		
		return session.selectList(namespace+".listPage", page);
	}


	@Override
	public List<CTBoardVO> listCriteria(SearchCriteria cri) throws Exception {
		return session.selectList(namespace+".listAll", cri, new RowBounds(cri.getPageStart(), cri.getPerPageNum()));
	}

	@Override
	public int countPaging(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace+".countPaging", cri);
	}

	@Override
	public void updateReplycnt(Integer bno, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bno", bno);
		paramMap.put("amount", amount);
		
		session.update(namespace+".updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		session.update(namespace+".updateViewCnt", bno);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return session.selectList(namespace+".getAttach", bno);
	}
}
