package cosmos.codetrace.persistence;

import java.util.List;

import cosmos.codetrace.domain.CTBoardVO;
import cosmos.codetrace.domain.Criteria;
import cosmos.codetrace.domain.SearchCriteria;

public interface CodetraceDAO {
	public void create(CTBoardVO vo)throws Exception;
	public CTBoardVO read(Integer bno)throws Exception;
	public void update(CTBoardVO vo)throws Exception;
	public void delete(Integer bno)throws Exception;
	public List<CTBoardVO> listAll()throws Exception;
	public List<CTBoardVO> listPage(int page)throws Exception;
	//public List<CTBoardVO> listCriteria(Criteria cri)throws Exception;
	public List<CTBoardVO> listCriteria(SearchCriteria cri)throws Exception;
	//public int countPaging(Criteria cri)throws Exception;
	public int countPaging(SearchCriteria cri)throws Exception;
	public void updateReplycnt(Integer bno, int amount)throws Exception;
	public void updateViewCnt(Integer bno)throws Exception;
	public List<String> getAttach(Integer bno)throws Exception;
}
