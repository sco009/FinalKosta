package cosmos.codetrace.persistence;

import java.util.List;

import cosmos.codetrace.domain.Criteria;
import cosmos.codetrace.domain.ReplyVO;


public interface ReplyDAO {
	public List<ReplyVO> list(Integer bno)throws Exception;
	public void r_create(ReplyVO vo)throws Exception;
	public void r_update(ReplyVO vo)throws Exception;
	public void r_delete(Integer rno)throws Exception;
	public List<ReplyVO> r_listPage(Integer bno, Criteria cri)throws Exception;
	public int r_count(Integer bno)throws Exception;
	public int getBno(Integer rno)throws Exception;
	
}
