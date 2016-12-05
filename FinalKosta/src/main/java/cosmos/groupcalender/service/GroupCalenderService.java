package cosmos.groupcalender.service;

import java.util.List;

import cosmos.groupcalender.domain.CalenderVO;

public interface GroupCalenderService {
	public List<CalenderVO> maincalenderlist(String groupid)throws Exception;
	public List<CalenderVO> selectcalenderlist(int yy , int mm , int dd,String groupid)throws Exception;
	public void newcalenderinsert(CalenderVO vo)throws Exception;
	public void calenderdeletes(int calenderNo)throws Exception;
	public void calenderupdate(CalenderVO vo)throws Exception;
}
