package cosmos.codetrace.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cosmos.codetrace.domain.CTBoardVO;
import cosmos.codetrace.domain.Criteria;
import cosmos.codetrace.domain.SearchCriteria;
import cosmos.codetrace.persistence.CodetraceDAO;

@Service
public class CodetraceServiceImpl implements CodetraceService {
	
	@Inject
	private CodetraceDAO dao;

	@Override
	public void regist(CTBoardVO vo) throws Exception {
		dao.create(vo);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public CTBoardVO read(Integer bno) throws Exception {
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	@Override
	public void modify(CTBoardVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<CTBoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	/*@Override
	public List<CTBoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}*/

	/*@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}*/

	@Override
	public List<CTBoardVO> listCri(SearchCriteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(SearchCriteria cri) throws Exception {
		return dao.countPaging(cri);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return dao.getAttach(bno);
	}
	

	

	

}
