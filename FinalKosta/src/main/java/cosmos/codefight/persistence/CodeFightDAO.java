package cosmos.codefight.persistence;

import java.util.List;

import cosmos.codefight.domain.CodeFightVO;

public interface CodeFightDAO {
	List<CodeFightVO>codefightList(String memberId)throws Exception;
}
