package cosmos.groupcalender.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cosmos.groupcalender.domain.CalenderVO;
import cosmos.groupcalender.persistence.GroupCalenderDAO;

@Service
public class GroupCalenderServiceImpl implements GroupCalenderService {
	@Inject
	public GroupCalenderDAO dao;
	
	@Override
	public List<CalenderVO> maincalenderlist(String groupid) throws Exception {
		return dao.maincalenderlist(groupid);
	}

	@Override
	public List<CalenderVO> selectcalenderlist(int yy, int mm, int dd,String groupid) throws Exception {
		return dao.selectcalenderlist(yy, mm, dd,groupid);
	}

	@Override
	public void newcalenderinsert(CalenderVO vo) throws Exception {
		dao.newcalenderinsert(vo);
	}

	@Override
	public void calenderdeletes(int calenderNo) throws Exception {
		dao.calenderdeletes(calenderNo);
	}

	@Override
	public void calenderupdate(CalenderVO vo) throws Exception {
		dao.calenderupdate(vo);
	}

}
