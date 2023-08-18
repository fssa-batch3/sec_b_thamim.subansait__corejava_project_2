package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.model.TrackEntity;
import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.service.TrackService;
import in.fssa.doboo.service.UserService;

public class TestCreateTrack {

	@Test
	public void testCreateUserWithValidInput() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		track.setTrackName("rush");
		track.setTrackDetail("this is the basic details");
		track.setScale("c minor");
		track.setPrice(100);
		track.setGenre("pop");
		track.setDaw("Fl");
		track.setBpm(90);
		assertDoesNotThrow(() -> {
			trackService.create(track,1);
		});
	}
}
