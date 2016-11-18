package cosmos.education.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.education.domain.EducationManagerVO;
import cosmos.education.domain.EducationVO;
import cosmos.education.persistence.EducationDAO;

@Service
public class EducationServiceImpl implements EducationService {
	@Inject
	private EducationDAO dao;

	@Override
	public void inserteducation(EducationVO Econtents) throws Exception {
		dao.inserteducation(Econtents);
	}

	
	@Override
	public void inserteductionManager(EducationManagerVO Emanager) throws Exception {
		dao.inserteductionManager(Emanager);
	}


	@Override
	public Integer selectEc_edu_c_ID() throws Exception {
		return dao.selectEc_edu_c_ID()+1;
	}


	@Override
	public Integer selectEc_managerid() throws Exception {
		return dao.selectEc_managerid()+1;
	}


	@Override
	public List<String> selectmemberID(String memberID) {
		
		
		return dao.selectmemberID(memberID);
	}


	@Override
	public void education_delete(String memberID, String hl_contents) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("0", memberID);
		map.put("1", hl_contents);
		
		dao.education_delete(map);
	}


	


	
	

}
