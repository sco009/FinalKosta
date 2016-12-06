package cosmos.noticeboard.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.noticeboard.domain.NoticeVO;
import cosmos.noticeboard.domain.NoticeSearchCriteria;

@Repository
public class NoticeDAOImpl implements NoticeDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="cosmos.mappers.NoticeMapper";

	@Override
	public void create(NoticeVO noticeVO) throws Exception {
		sqlSession.insert(namespace+".create", noticeVO);
		
	}

	@Override
	public NoticeVO read(Integer bno) throws Exception {
		NoticeVO vo = sqlSession.selectOne(namespace+".read",bno);
		return vo;
	}

	@Override
	public void update(NoticeVO noticeVO) throws Exception {
		sqlSession.update(namespace+".update",noticeVO);
		
	}

	@Override
	public void delete(Integer bno) throws Exception {
		sqlSession.delete(namespace+".delete",bno);
		
	}

	@Override
	public List<NoticeVO> listAll() throws Exception {
		return sqlSession.selectList(namespace+".listAll");
	}
	
	@Override
	public List<NoticeVO> listPage(int page) throws Exception {
		if(page<=0){
			page = 1;
		}
		
		page = (page - 1)*5;
		
		return sqlSession.selectList(namespace+".listPage", page);
	}
	
	@Override
	public List<NoticeVO> listCriteria(NoticeSearchCriteria cri) throws Exception {
		return sqlSession.selectList(namespace+".listAll", cri, new RowBounds(cri.getPageStart(), cri.getPerPageNum()));
	}

	@Override
	public int countPaging(NoticeSearchCriteria cri) throws Exception {
		return sqlSession.selectOne(namespace+".countPaging", cri);
	}
	
	@Override
	public void updateReplyCnt(Integer bno, int amount) throws Exception {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("bno", bno);
		paramMap.put("amount", amount);
		
		sqlSession.update(namespace+".updateReplyCnt",paramMap);
		
	}

	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		sqlSession.update(namespace+".updateViewCnt",bno);
		
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
	
		return sqlSession.selectList(namespace+".getAttach",bno);
	}
}
