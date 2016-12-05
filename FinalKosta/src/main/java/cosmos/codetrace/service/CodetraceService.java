package cosmos.codetrace.service;

import java.util.List;

import cosmos.codetrace.domain.CTBoardVO;
import cosmos.codetrace.domain.Criteria;
import cosmos.codetrace.domain.SearchCriteria;

public interface CodetraceService {
	public void regist(CTBoardVO vo)throws Exception;
	
	public CTBoardVO read(Integer bno)throws Exception;
	
	public void modify(CTBoardVO vo)throws Exception;
	
	public void remove(Integer bno)throws Exception;
	
	public List<CTBoardVO> listAll()throws Exception;
	
	//public List<CTBoardVO> listCriteria(Criteria cri)throws Exception;
	
	public List<CTBoardVO> listCri(SearchCriteria cri)throws Exception;
	
	//public int listCountCriteria(Criteria cri)throws Exception;
	public int listCountCriteria(SearchCriteria cri)throws Exception;
	
	public List<String> getAttach(Integer bno)throws Exception;
}
