package in.fssa.doboo.model;

public class Track implements Comparable<Track>  {

	private int id;
	private String TrackName;
	private String TrackDetail;
	private int bpm;
	private String daw;
	private String genre;
	private int price;
	private String scale;
	private boolean isActive;
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrackName() {
		return TrackName;
	}
	public void setTrackName(String trackName) {
		TrackName = trackName;
	}
	public String getTrackDetail() {
		return TrackDetail;
	}
	public void setTrackDetail(String trackDetail) {
		TrackDetail = trackDetail;
	}
	public int getBpm() {
		return bpm;
	}
	public void setBpm(int bpm) {
		this.bpm = bpm;
	}
	public String getDaw() {
		return daw;
	}
	public void setDaw(String daw) {
		this.daw = daw;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "Track [id=" + id + ", TrackName=" + TrackName + ", TrackDetail=" + TrackDetail + ", bpm=" + bpm
				+ ", daw=" + daw + ", genre=" + genre + ", scale=" + scale+", price=" + price+ ", isActive=" + isActive + "]";
	}
	
	@Override
	public int compareTo(Track o) {
		
		if (this.getId() == o.getId()) {
			return 0;
		} else {
			if (this.getId() > o.getId()) {
				return 1;
			} else {
				return -1;
			}
	}

   }
}
