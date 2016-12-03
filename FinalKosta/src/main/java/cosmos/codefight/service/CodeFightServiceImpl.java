package cosmos.codefight.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.codefight.domain.CodeFightVO;
import cosmos.codefight.persistence.CodeFightDAO;

@Service
public class CodeFightServiceImpl implements CodeFightService {
	@Inject
	private CodeFightDAO dao;

	@Override
	public List<CodeFightVO> codefightList(String memberId) throws Exception {
		return dao.codefightList(memberId);
	}

}
