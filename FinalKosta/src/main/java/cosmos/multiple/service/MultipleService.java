package cosmos.multiple.service;

import java.util.List;

import cosmos.multiple.domain.MultipleChoice;
import cosmos.multiple.domain.MultiplePoint;
import cosmos.multiple.domain.MultipleVO;

public interface MultipleService {
	public List<MultipleVO> selectMultiple(MultipleVO vo) throws Exception;

	public MultipleChoice selectMultipleChoice(String multipleChoiceId) throws Exception;

	public int selectMultipleCount(MultipleVO vo) throws Exception;

	public MultipleVO reMultiple(String mulquestId) throws Exception;

//	public int pointInsert(MultiplePoint multiplePoint) throws Exception;
}
