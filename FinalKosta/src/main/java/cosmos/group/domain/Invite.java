package cosmos.group.domain;

public class Invite {
	private String sendPerson;
	private String contents;
	private String[] receive;
	
	public Invite(){}
	public Invite(String sendPerson, String contents, String... receive) {
		this.sendPerson = sendPerson;
		this.contents = contents;
		this.receive = receive;
	}
	
	
	public String[] getReceive() {
		return receive;
	}
	public void setReceive(String[] receive) {
		this.receive= receive;
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
}
