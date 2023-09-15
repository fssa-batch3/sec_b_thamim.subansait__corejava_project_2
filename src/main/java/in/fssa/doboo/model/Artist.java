package in.fssa.doboo.model;

public class Artist {
		public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getInsta() {
		return insta;
	}
	public void setInsta(String insta) {
		this.insta = insta;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getLinkedln() {
		return linkedln;
	}
	public void setLinkedln(String linkedln) {
		this.linkedln = linkedln;
	}
	public String getSpotify() {
		return spotify;
	}
	public void setSpotify(String spotify) {
		this.spotify = spotify;
	}
		private int id;
		private String type;
		private String artistName;
		private String bio;
		private String insta;
		private String facebook;
		private String linkedln;
		private String spotify;
		@Override
		public String toString() {
			return "Artist [id=" + id + ", type=" + type + ", artistName=" + artistName + ", bio=" + bio + ", insta="
					+ insta + ", facebook=" + facebook + ", linkedln=" + linkedln + ", spotify=" + spotify + "]";
		}
		
}
