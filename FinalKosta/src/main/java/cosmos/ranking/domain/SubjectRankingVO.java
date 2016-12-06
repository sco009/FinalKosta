package cosmos.ranking.domain;

public class SubjectRankingVO {
	private String memberID;
	private String memberName;
	private int subject_ranking;
	private int subject_point;
	
	public SubjectRankingVO(){}

	public SubjectRankingVO(String memberID, String memberName, int subject_ranking, int subject_point) {
		super();
		this.memberID = memberID;
		this.memberName = memberName;
		this.subject_ranking = subject_ranking;
		this.subject_point = subject_point;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getSubject_ranking() {
		return subject_ranking;
	}

	public void setSubject_ranking(int subject_ranking) {
		this.subject_ranking = subject_ranking;
	}

	public int getSubject_point() {
		return subject_point;
	}

	public void setSubject_point(int subject_point) {
		this.subject_point = subject_point;
	}
	

}
