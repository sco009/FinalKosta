package cosmos.group.domain;

public class InviteVO {
	private String inviteID, sendPerson, contents;
	private String receive1, receive2, receive3, receive4, receive5, receive6;
	
	public InviteVO() {}
	
	public InviteVO(String inviteID, String sendPerson, String contents, String receive1, String receive2,
			String receive3, String receive4, String receive5, String receive6) {
		super();
		this.inviteID = inviteID;
		this.sendPerson = sendPerson;
		this.contents = contents;
		this.receive1 = receive1;
		this.receive2 = receive2;
		this.receive3 = receive3;
		this.receive4 = receive4;
		this.receive5 = receive5;
		this.receive6 = receive6;
	}
	
	public String getInviteID() {
		return inviteID;
	}
	public void setInviteID(String inviteID) {
		this.inviteID = inviteID;
	}
	public String getSendPerson() {
		return sendPerson;
	}
	public void setSendPerson(String sendPerson) {
		this.sendPerson = sendPerson;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getReceive1() {
		return receive1;
	}
	public void setReceive1(String receive1) {
		this.receive1 = receive1;
	}
	public String getReceive2() {
		return receive2;
	}
	public void setReceive2(String receive2) {
		this.receive2 = receive2;
	}
	public String getReceive3() {
		return receive3;
	}
	public void setReceive3(String receive3) {
		this.receive3 = receive3;
	}
	public String getReceive4() {
		return receive4;
	}
	public void setReceive4(String receive4) {
		this.receive4 = receive4;
	}
	public String getReceive5() {
		return receive5;
	}
	public void setReceive5(String receive5) {
		this.receive5 = receive5;
	}
	public String getReceive6() {
		return receive6;
	}
	public void setReceive6(String receive6) {
		this.receive6 = receive6;
	}

	@Override
	public String toString() {
		return "InviteVO [inviteID=" + inviteID + ", sendPerson=" + sendPerson + ", contents=" + contents
				+ ", receive1=" + receive1 + ", receive2=" + receive2 + ", receive3=" + receive3 + ", receive4="
				+ receive4 + ", receive5=" + receive5 + ", receive6=" + receive6 + "]";
	}
}
