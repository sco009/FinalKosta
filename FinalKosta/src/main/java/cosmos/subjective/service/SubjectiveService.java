package cosmos.subjective.service;

import java.util.List;

import cosmos.subjective.domain.SubjectiveVO;

public interface SubjectiveService {
	public List<SubjectiveVO> selectSubjective(SubjectiveVO VO)throws Exception;
	public SubjectiveVO choiceSubjective(String subjectiveId)throws Exception;
	public int countSubjective(SubjectiveVO VO)throws Exception;
}
