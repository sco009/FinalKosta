package cosmos.noticeboard.service;

import java.util.List;

import cosmos.noticeboard.domain.NoticeVO;
import cosmos.noticeboard.domain.SearchCriteria;

public interface NoticeService {
	public void regist(NoticeVO vo) throws Exception;
	public NoticeVO read(Integer bno) throws Exception;
	public void modify(NoticeVO vo) throws Exception;
	public void remove(Integer bno) throws Exception;
	public List<NoticeVO> listCri(SearchCriteria criteria) throws Exception;
	public int listCountCriteria(SearchCriteria criteria) throws Exception;
	public List<String> getAttach(Integer bno) throws Exception;
}
