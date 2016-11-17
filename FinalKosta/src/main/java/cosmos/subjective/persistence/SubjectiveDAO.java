package cosmos.subjective.persistence;

import java.util.List;

import cosmos.subjective.domain.SubjectiveVO;

public interface SubjectiveDAO {
	public List<SubjectiveVO> selectSubjective(SubjectiveVO VO)throws Exception;
	public SubjectiveVO choiceSubjective(String subjectiveId)throws Exception;
}
