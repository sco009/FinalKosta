package cosmos.group.domain;

public class GroupsVO {
	private String groupID, leaderID, createDate, groupName;
	private String team1, team2, team3, team4, team5, team6;
	
	public GroupsVO(){}

	public GroupsVO(String groupID, String leaderID, String createDate, String groupName, String team1, String team2,
			String team3, String team4, String team5, String team6) {
		super();
		this.groupID = groupID;
		this.leaderID = leaderID;
		this.createDate = createDate;
		this.groupName = groupName;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
}
