package cosmos.multiple.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MultipleDAOImpl implements MultipleDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="cosmos.mappers.multipleMapper";
	
	@Override
	public void dd(){
		
	}
}
