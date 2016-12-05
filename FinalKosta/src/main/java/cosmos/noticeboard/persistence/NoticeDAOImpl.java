package cosmos.noticeboard.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.noticeboard.domain.FileVO;
import cosmos.noticeboard.domain.NoticeVO;
import cosmos.noticeboard.domain.SearchCriteria;

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
		NoticeVO vo = sqlSession.selectOne(namespace+".getBoard",bno);
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
	public List<NoticeVO> listCriteria(SearchCriteria criteria) throws Exception {
		return sqlSession.selectList(namespace+".getList", criteria,new RowBounds(criteria.getPageStart(),criteria.getPerPageNum()));
	}

	@Override
	public int countPaging(SearchCriteria criteria) throws Exception {
		
		return sqlSession.selectOne(namespace+".countPaging",criteria);
	}


	@Override
	public void updateReplyCnt(Integer bno, int amount) throws Exception {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("bno", bno);
		paramMap.put("amount", amount);
		
		sqlSession.update(namespace+".updateReplyCnt",paramMap);
		
	}

	@Override
	public int getBno(Integer rno) throws Exception {//원본글 번호를 가져온다
		
		System.out.println(rno);
		int bno = sqlSession.selectOne(namespace+".getBno",rno);
		System.out.println(bno);
		return sqlSession.selectOne(namespace+".getBno",rno);
	}

	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		sqlSession.update(namespace+".updateViewCnt",bno);
		
	}

	@Override
	public void addAttach(FileVO fileVO) throws Exception {
		sqlSession.insert(namespace+".addAttach",fileVO);
	}

	@Override
	public int getBno() throws Exception {
		return sqlSession.selectOne(namespace+".maxNum");
		
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
	
		return sqlSession.selectList(namespace+".getAttach",bno);
	}

	@Override
	public void deleteAttach(Integer bno) throws Exception {
		sqlSession.delete(namespace+".deleteAttach",bno);
		
	}

	@Override
	public void repalceAttach(String fullName, Integer bno) throws Exception {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("bno", bno);
		paramMap.put("fullName", fullName);
		
		sqlSession.insert(namespace+".replaceAttach",paramMap);
		
		
	}
}
