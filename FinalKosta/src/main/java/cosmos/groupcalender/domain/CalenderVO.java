package cosmos.groupcalender.domain;


public class CalenderVO {
	private int calenderNo;
	private String title, contexts;
	private int yy,mm,dd;
	
	public CalenderVO(){}
	
	public CalenderVO(int calenderNo, String title, String contexts, int yy, int mm, int dd) {
		super();
		this.calenderNo = calenderNo;
		this.title = title;
		this.contexts = contexts;
		this.yy = yy;
		this.mm = mm;
		this.dd = dd;
	}
	
	@Override
	public String toString() {
		return "[calenderNo=" + calenderNo + ", title=" + title + ", contexts=" + contexts + ", yy=" + yy + ", mm="
				+ mm + ", dd=" + dd + "]";
	}

	public int getcalenderNo() {
		return calenderNo;
	}
	public void setcalenderNo(int calenderNo) {
		this.calenderNo = calenderNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContexts() {
		return contexts;
	}
	public void setContexts(String contexts) {
		this.contexts = contexts;
	}
	public int getYy() {
		return yy;
	}
	public void setYy(int yy) {
		this.yy = yy;
	}
	public int getMm() {
		return mm;
	}
	public void setMm(int mm) {
		this.mm = mm;
	}
	public int getDd() {
		return dd;
	}
	public void setDd(int dd) {
		this.dd = dd;
	}
	
	
}