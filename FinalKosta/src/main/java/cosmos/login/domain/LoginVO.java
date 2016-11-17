package cosmos.login.domain;

public class LoginVO {
	private String memberID;
	private String memberPw;
	
	
	public LoginVO(){}
	public LoginVO(String memberID, String memberPw) {
		this.memberID = memberID;
		this.memberPw = memberPw;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	
	
	
}
