package cosmos.ranking.domain;

public class CodeRankingVO {
	private String memberID;
	private String memberName;
	private int code_ranking;
	private int code_point;
	
	public CodeRankingVO(){}
	
	public CodeRankingVO(String memberID, String memberName, int code_ranking, int code_point) {
		super();
		this.memberID = memberID;
		this.memberName = memberName;
		this.code_ranking = code_ranking;
		this.code_point = code_point;
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
	public int getCode_ranking() {
		return code_ranking;
	}
	public void setCode_ranking(int code_ranking) {
		this.code_ranking = code_ranking;
	}
	public int getCode_point() {
		return code_point;
	}
	public void setCode_point(int code_point) {
		this.code_point = code_point;
	}

}
