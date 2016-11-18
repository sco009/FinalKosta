package cosmos.education.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.stream.events.Namespace;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cosmos.education.domain.EducationManagerVO;
import cosmos.education.domain.EducationVO;

@Repository
public class EducationDAOImpl implements EducationDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"cosmos.mappers.educationMapper";
	
	
	@Override
	public void inserteducation(EducationVO Econtents) {
		sqlSession.insert(namespace+".inserteducation", Econtents);
	}


	@Override
	public Integer selectEc_edu_c_ID() throws Exception {
		return sqlSession.selectOne(namespace+".selectEc_edu_c_ID");
	}


	@Override
	public void inserteductionManager(EducationManagerVO Emanager) throws Exception {
		sqlSession.insert(namespace+".inserteductionManager", Emanager);
	}


	@Override
	public Integer selectEc_managerid() throws Exception {
		return sqlSession.selectOne(namespace+".selectEc_managerid");
	}


	@Override
	public List<String> selectmemberID(String memberID) {
		return sqlSession.selectList(namespace+".selectmemberID", memberID);
	}


	@Override
	public void education_delete(Map<String, String> map) throws Exception {
		
		sqlSession.delete(namespace+".education_delete", map);
	}


}
