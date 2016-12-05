package cosmos.education.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cosmos.education.domain.EducationManagerVO;
import cosmos.education.domain.EducationVO;

public interface EducationDAO {
	public void inserteducation(EducationVO Econtents)throws Exception;
	public Integer selectEc_edu_c_ID()throws Exception;
	public void inserteductionManager(EducationManagerVO Emanager)throws Exception;
	public Integer selectEc_managerid()throws Exception;
	public List<String>selectmemberID(String memberID);
	public void education_delete(Map<String, String> map)throws Exception;
	
}
