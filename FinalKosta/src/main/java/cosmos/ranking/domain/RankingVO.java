package cosmos.ranking.domain;

public class RankingVO {
	private String memberID;
	private int multiple_ranking;
	private int multiple_point;
	private int subject_ranking;
	private int subject_point;
	
	public RankingVO(){}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberID == null) ? 0 : memberID.hashCode());
		result = prime * result + multiple_point;
		result = prime * result + multiple_ranking;
		result = prime * result + subject_point;
		result = prime * result + subject_ranking;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RankingVO other = (RankingVO) obj;
		if (memberID == null) {
			if (other.memberID != null)
				return false;
		} else if (!memberID.equals(other.memberID))
			return false;
		if (multiple_point != other.multiple_point)
			return false;
		if (multiple_ranking != other.multiple_ranking)
			return false;
		if (subject_point != other.subject_point)
			return false;
		if (subject_ranking != other.subject_ranking)
			return false;
		return true;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
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

	public int getSubject_ranking() {
		return subject_ranking;
	}

	public void setSubject_ranking(int subject_ranking) {
		this.subject_ranking = subject_ranking;
	}

	public int getSubject_point() {
		return subject_point;
	}

	public void setSubject_point(int subject_point) {
		this.subject_point = subject_point;
	};
	
}
