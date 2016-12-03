package cosmos.group.domain;

import java.util.Date;

public class GroupVO {
	private String groupID;
	private String leaderID;
	private Date createDate;
	private String groupName;
	private int totalMember;
	private String team1;
	private String team2;
	private String team3;
	private String team4;
	private String team5;
	private String team6;
	
	
	public GroupVO(){}
	public GroupVO(String groupID, String leaderID, Date createDate, String groupName, int totalMember, String team1,
			String team2, String team3, String team4, String team5, String team6) {
		
		this.groupID = groupID;
		this.leaderID = leaderID;
		this.createDate = createDate;
		this.groupName = groupName;
		this.totalMember = totalMember;
		this.team1 = team1;
		this.team2 = team2;
		this.team3 = team3;
		this.team4 = team4;
		this.team5 = team5;
		this.team6 = team6;
	}



	public String getGroupID() {
		return groupID;
	}


	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}


	public String getLeaderID() {
		return leaderID;
	}


	public void setLeaderID(String leaderID) {
		this.leaderID = leaderID;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public int getTotalMember() {
		return totalMember;
	}


	public void setTotalMember(int totalMember) {
		this.totalMember = totalMember;
	}


	public String getTeam1() {
		return team1;
	}


	public void setTeam1(String team1) {
		this.team1 = team1;
	}


	public String getTeam2() {
		return team2;
	}


	public void setTeam2(String team2) {
		this.team2 = team2;
	}


	public String getTeam3() {
		return team3;
	}


	public void setTeam3(String team3) {
		this.team3 = team3;
	}


	public String getTeam4() {
		return team4;
	}


	public void setTeam4(String team4) {
		this.team4 = team4;
	}


	public String getTeam5() {
		return team5;
	}


	public void setTeam5(String team5) {
		this.team5 = team5;
	}


	public String getTeam6() {
		return team6;
	}


	public void setTeam6(String team6) {
		this.team6 = team6;
	}
	@Override
	public String toString() {
		return "GroupVO [groupID=" + groupID + ", leaderID=" + leaderID + ", createDate=" + createDate + ", groupName="
				+ groupName + ", totalMember=" + totalMember + ", team1=" + team1 + ", team2=" + team2 + ", team3="
				+ team3 + ", team4=" + team4 + ", team5=" + team5 + ", team6=" + team6 + "]";
	}
	
	
	
	

	
	
	
	
	
	
}
