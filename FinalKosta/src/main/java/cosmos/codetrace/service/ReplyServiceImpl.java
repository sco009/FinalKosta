package cosmos.codetrace.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cosmos.codetrace.domain.Criteria;
import cosmos.codetrace.domain.ReplyVO;
import cosmos.codetrace.persistence.CodetraceDAO;
import cosmos.codetrace.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO replyDAO;
	
	@Inject
	private CodetraceDAO dao;

	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		replyDAO.r_create(vo);
		dao.updateReplycnt(vo.getBno(), 1);
	}
	
	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
		return replyDAO.list(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		replyDAO.r_update(vo);
	}
	
	@Transactional
	@Override
	public void removeReply(Integer rno) throws Exception {
		int bno = replyDAO.getBno(rno);
		replyDAO.r_delete(rno);
		dao.updateReplycnt(bno, -1);
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {
		return replyDAO.r_listPage(bno, cri);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return replyDAO.r_count(bno);
	}

	

}
