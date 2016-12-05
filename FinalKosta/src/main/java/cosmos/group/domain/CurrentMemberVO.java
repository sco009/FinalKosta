package cosmos.group.domain;

public class CurrentMemberVO {
	private String memberID ; //회원 아이디
	private String memberName ; //회원 이름
	
	public CurrentMemberVO(){}
	public CurrentMemberVO(String memberID, String memberName) {
		super();
		this.memberID = memberID;
		this.memberName = memberName;
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
	@Override
	public String toString() {
		return "GroupVO [memberID=" + memberID + ", memberName=" + memberName + "]";
	}
}
