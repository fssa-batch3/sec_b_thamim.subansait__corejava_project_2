package in.fssa.doboo.Interface;

import java.util.Set;

import in.fssa.doboo.model.TrackEntity;

public interface TrackInterface extends Base<TrackEntity> {
	
		public abstract Set<TrackEntity> findMatchTrackByName(String TrackName);
		public abstract Set<TrackEntity> findTracksByAtirstName(String ArtistName);
		
		public abstract int createTrack(TrackEntity track, int userId);

}
