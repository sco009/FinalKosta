package cosmos.login.dto;

public class LoginDTO {
	private String memberID;
	private String memberPw;
	private String memberName;
	private boolean useCookie;
	
	public LoginDTO(){}
	

	
	public LoginDTO(String memberID, String memberPw, String memberName, boolean useCookie) {
		this.memberID = memberID;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.useCookie = useCookie;
	}



	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
		
	}



	@Override
	public String toString() {
		return "LoginDTO [memberID=" + memberID + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", useCookie=" + useCookie + "]";
	}
	
	
}
