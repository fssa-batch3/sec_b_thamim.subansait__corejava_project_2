package in.fssa.doboo.model;

public class Orders {

	private int id;
	
	private int userId;
	private int trackId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Orders [id=" + id + ", userId=" + userId + ", trackId=" + trackId + "]";
	}
	
	
}
