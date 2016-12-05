package cosmos.noticeboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cosmos.noticeboard.domain.FileVO;
import cosmos.noticeboard.domain.NoticeVO;
import cosmos.noticeboard.domain.SearchCriteria;
import cosmos.noticeboard.persistence.NoticeDAO;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Inject
	private NoticeDAO dao;
	
	@Override
	public void regist(NoticeVO notice) throws Exception {
		dao.create(notice);
		String[] files = notice.getFiles();
		int bno = dao.getBno();
		
		FileVO fileVO = new FileVO();
		fileVO.setBno(bno);
		
		if(files == null){
			return;
		}
		for(String fileName : files){
			fileVO.setFullName(fileName);
			dao.addAttach(fileVO);
		}
	}

	@Transactional
	@Override
	public NoticeVO read(Integer bno) throws Exception {
		dao.updateViewCnt(bno);
		NoticeVO noticeVO = dao.read(bno);
		return noticeVO;
	}

	@Override
	public void modify(NoticeVO vo) throws Exception {
		dao.update(vo);
		Integer bno = vo.getBno();
		dao.deleteAttach(bno);
		
		String[] files = vo.getFiles();
		if(files== null){
			return;
		}
		for(String fileName : files){
			dao.repalceAttach(fileName, bno);
		}
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.deleteAttach(bno);
		dao.delete(bno);
	}

	@Override
	public List<NoticeVO> listCri(SearchCriteria criteria) throws Exception {
		return dao.listCriteria(criteria);
	}

	@Override
	public int listCountCriteria(SearchCriteria criteria) throws Exception {
		return dao.countPaging(criteria);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		
		return dao.getAttach(bno);
	}
}
