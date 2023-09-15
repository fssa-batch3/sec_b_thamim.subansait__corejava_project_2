package in.fssa.doboo.model;

public class Assest {
	private String imageUrl;
	private String audioUrl;
	private int trackId;
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAudioUrl() {
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	@Override
	public String toString() {
		return "Assest [imageUrl=" + imageUrl + ", audioUrl=" + audioUrl + ", trackId=" + trackId 
				+ "]";
	}
	
}
