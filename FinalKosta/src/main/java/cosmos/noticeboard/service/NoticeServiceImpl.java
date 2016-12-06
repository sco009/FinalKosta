package cosmos.noticeboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cosmos.noticeboard.domain.NoticeVO;
import cosmos.noticeboard.domain.NoticeSearchCriteria;
import cosmos.noticeboard.persistence.NoticeDAO;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Inject
	private NoticeDAO dao;

	@Override
	public void regist(NoticeVO vo) throws Exception {
		dao.create(vo);		
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public NoticeVO read(Integer bno) throws Exception {
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	@Override
	public void modify(NoticeVO vo) throws Exception {
		dao.update(vo);		
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<NoticeVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<NoticeVO> listCri(NoticeSearchCriteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(NoticeSearchCriteria cri) throws Exception {
		return dao.countPaging(cri);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return dao.getAttach(bno);

	}
	
}
