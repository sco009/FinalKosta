package cosmos.noticeboard.persistence;

import java.util.List;

import cosmos.noticeboard.domain.FileVO;
import cosmos.noticeboard.domain.NoticeVO;
import cosmos.noticeboard.domain.NoticeSearchCriteria;

public interface NoticeDAO {
	public void create(NoticeVO boardVO)throws Exception;
	public NoticeVO read(Integer bno)throws Exception;
	public void update(NoticeVO boardVO)throws Exception;
	public void delete(Integer bno)throws Exception;
	public List<NoticeVO> listCriteria(NoticeSearchCriteria criteria)throws Exception;
	public int countPaging(NoticeSearchCriteria criteria)throws Exception;
	public void updateReplyCnt(Integer bno, int amount)throws Exception;
	public int getBno(Integer rno)throws Exception;
	public void updateViewCnt(Integer bno)throws Exception;
	public void addAttach(FileVO fileVO)throws Exception;
	public int getBno()throws Exception;
	public List<String> getAttach(Integer bno)throws Exception;
	public void deleteAttach(Integer bno)throws Exception;
	public void repalceAttach(String fullName, Integer bno)throws Exception;

}
