package cosmos.signup.domain;

import java.io.Serializable;

public class SignUpVO implements Serializable{
	private String memberID ; //회원 아이디
	private String memberPw ; //회원 비밀번호
	private String memberName ; //회원 이름
	private String memberPhoneNum ; //회원 전화번호
	private String memberEmail ; // 회원 이메일
	private String isLeader; //팀장인지 아닌지
	
	public SignUpVO(){}
	
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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPhoneNum() {
		return memberPhoneNum;
	}
	public void setMemberPhoneNum(String memberPhoneNum) {
		this.memberPhoneNum = memberPhoneNum;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}
	
	public SignUpVO(String memberID, String memberPw, String memberName, String memberPhoneNum, String memberEmail,
			String isLeader) {
		super();
		this.memberID = memberID;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberPhoneNum = memberPhoneNum;
		this.memberEmail = memberEmail;
		this.isLeader = isLeader;
	}
	
	
}
