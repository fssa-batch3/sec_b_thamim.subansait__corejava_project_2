package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.TrackEntity;
import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.service.TrackService;
import in.fssa.doboo.service.UserService;

public class TestUpdateTrackDetails {
	@Test
	public void testUpdateUserWithValidInput() {

		TrackService trackService = new TrackService();

		TrackEntity track = new TrackEntity();
		track.setTrackName("don't lie");
		track.setTrackDetail("life is just waste for you");
		track.setScale("c minor");
		track.setPrice(200);
		track.setGenre("rap");
		track.setDaw("adobe");
		track.setBpm(69);
		assertDoesNotThrow(() -> {
			trackService.updateTrack(5, track);
		});
	}
	@Test
	public void testUpdateUserWithInValidInput() {

		TrackService trackService = new TrackService();

		TrackEntity track = new TrackEntity();
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.updateTrack(5, null);
		});
		String expectedMessage = "track cannot be null";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
		
	}
	
	@Test
	public void testUpdateUserWithInValidtrackName() {

		TrackService trackService = new TrackService();

		TrackEntity track = new TrackEntity();
		track.setTrackName("");
		track.setTrackDetail("life is just waste for you");
		track.setScale("c minor");
		track.setPrice(200);
		track.setGenre("rap");
		track.setDaw("adobe");
		track.setBpm(69);
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.updateTrack(5, track);
		});
		String expectedMessage = "TrackName cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
		
	}
	
	@Test
	public void testUpdateUserWithInValidtrackName1() {

		TrackService trackService = new TrackService();

		TrackEntity track = new TrackEntity();
		track.setTrackName(null);
		track.setTrackDetail("life is just waste for you");
		track.setScale("c minor");
		track.setPrice(200);
		track.setGenre("rap");
		track.setDaw("adobe");
		track.setBpm(69);
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.updateTrack(5, track);
		});
		String expectedMessage = "TrackName cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
		
	}
	
	@Test
	public void testUpdateUserWithInValidId() {

		TrackService trackService = new TrackService();

		TrackEntity track = new TrackEntity();
		track.setTrackName("poland");
		track.setTrackDetail("life is just waste for you");
		track.setScale("c minor");
		track.setPrice(200);
		track.setGenre("rap");
		track.setDaw("adobe");
		track.setBpm(69);
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.updateTrack(0, track);
		});
		String expectedMessage = "id is less than zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
		
	}
	
	@Test
	public void testUpdateUserWithWrongId() {

		TrackService trackService = new TrackService();

		TrackEntity track = new TrackEntity();
		track.setTrackName("poland");
		track.setTrackDetail("life is just waste for you");
		track.setScale("c minor");
		track.setPrice(200);
		track.setGenre("rap");
		track.setDaw("adobe");
		track.setBpm(69);
		track.setId(100);
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.updateTrack(100, track);
		});
		String expectedMessage = "track not found";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
		
	}
	
	@Test
	public void testUpdateUserWithInvalidPrice() {

		TrackService trackService = new TrackService();

		TrackEntity track = new TrackEntity();
		track.setTrackName("high profile");
		track.setTrackDetail("life is just waste for you");
		track.setScale("c minor");
		track.setPrice(0);
		track.setGenre("rap");
		track.setDaw("adobe");
		track.setBpm(69);
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.updateTrack(1, track);
		});
		String expectedMessage = "price cannot be less than zero or greater than 6000";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
		
	}
	
	@Test
	public void testUpdateUserWithInvalidPrice2() {

		TrackService trackService = new TrackService();

		TrackEntity track = new TrackEntity();
		track.setTrackName("high profile");
		track.setTrackDetail("life is just waste for you");
		track.setScale("c minor");
		track.setPrice(10000);
		track.setGenre("rap");
		track.setDaw("adobe");
		track.setBpm(69);
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.updateTrack(1, track);
		});
		String expectedMessage = "price cannot be less than zero or greater than 6000";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
		
	}


}
