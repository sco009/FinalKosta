package cosmos.ranking.domain;

public class MultipleRankingVO {
	private String memberID;
	private String memberName;
	private int multiple_ranking;
	private int multiple_point;
	
	public MultipleRankingVO(){}

	public MultipleRankingVO(String memberID, String memberName, int multiple_ranking, int multiple_point) {
		super();
		this.memberID = memberID;
		this.memberName = memberName;
		this.multiple_ranking = multiple_ranking;
		this.multiple_point = multiple_point;
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

	public int getMultiple_ranking() {
		return multiple_ranking;
	}

	public void setMultiple_ranking(int multiple_ranking) {
		this.multiple_ranking = multiple_ranking;
	}

	public int getMultiple_point() {
		return multiple_point;
	}

	public void setMultiple_point(int multiple_point) {
		this.multiple_point = multiple_point;
	}
	
	
}
