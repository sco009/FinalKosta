package cosmos.codefight.service;

import java.util.List;

import cosmos.codefight.domain.CodeFightVO;


public interface CodeFightService {
	public List<CodeFightVO> codefightList(String memberId)throws Exception;

}
