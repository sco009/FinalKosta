package cosmos.noticeboard.persistence;

import java.util.List;

import cosmos.noticeboard.domain.NoticeSearchCriteria;
import cosmos.noticeboard.domain.NoticeVO;

public interface NoticeDAO {
	public void create(NoticeVO boardVO)throws Exception;
	public NoticeVO read(Integer bno)throws Exception;
	public void update(NoticeVO boardVO)throws Exception;
	public void delete(Integer bno)throws Exception;
	public List<NoticeVO> listAll()throws Exception;
	public List<NoticeVO> listPage(int page)throws Exception;
	public List<NoticeVO> listCriteria(NoticeSearchCriteria cri)throws Exception;
	public int countPaging(NoticeSearchCriteria cri)throws Exception;
	public void updateReplyCnt(Integer bno, int amount)throws Exception;
	public void updateViewCnt(Integer bno)throws Exception;
	public List<String> getAttach(Integer bno)throws Exception;
}
