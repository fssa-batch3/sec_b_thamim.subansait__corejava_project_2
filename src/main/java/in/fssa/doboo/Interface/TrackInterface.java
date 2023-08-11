package in.fssa.doboo.Interface;

import java.util.Set;

import in.fssa.doboo.model.TrackEntity;

public interface TrackInterface extends Base<TrackEntity> {
	
		public abstract Set<TrackEntity> findMatchTrackByName();
		public abstract Set<TrackEntity> findTracksByAtirstName();
		
		public abstract void createTrack(TrackEntity track, int userId);

}
