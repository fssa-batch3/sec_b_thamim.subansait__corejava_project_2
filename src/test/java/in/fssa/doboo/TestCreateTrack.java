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

public class TestCreateTrack {

	@Test
	public void testCreateTrackWithValidInput() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		track.setTrackName("popstick");
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
	
	@Test
	public void testCreateTrackWithInValiduserId() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		track.setTrackName("popstick");
		track.setTrackDetail("this is the basic details");
		track.setScale("c minor");
		track.setPrice(100);
		track.setGenre("pop");
		track.setDaw("Fl");
		track.setBpm(90);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.create(track,10);
		});
		String expectedMessage = "User with ID 10 does not exist";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test
	public void testCreateTrackWithExtisTrackName() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		track.setTrackName("popstick");
		track.setTrackDetail("this is the basic details");
		track.setScale("c minor");
		track.setPrice(100);
		track.setGenre("pop");
		track.setDaw("Fl");
		track.setBpm(90);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.create(track,6);
		});
		String expectedMessage = "Track name popstick already exists for the user";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	@Test
	public void testCreateTrackWithnull() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.create(null,6);
		});
		String expectedMessage = "track cannot be null";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test
	public void testCreateTrackWithEmptyTrackName() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		track.setTrackName("");
		track.setTrackDetail("this is the basic details");
		track.setScale("c minor");
		track.setPrice(100);
		track.setGenre("pop");
		track.setDaw("Fl");
		track.setBpm(90);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.create(track,6);
		});
		String expectedMessage = "TrackName cannot be null or empty";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test
	public void testCreateTrackWithinvalidPrice() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		track.setTrackName("england");
		track.setTrackDetail("this is the basic details");
		track.setScale("c minor");
		track.setPrice(0);
		track.setGenre("pop");
		track.setDaw("Fl");
		track.setBpm(90);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.create(track,6);
		});
		String expectedMessage = "price cannot be less than zero or greater than 6000";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test
	public void testCreateTrackWithinvalidPrice2() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		track.setTrackName("england");
		track.setTrackDetail("this is the basic details");
		track.setScale("c minor");
		track.setPrice(10000);
		track.setGenre("pop");
		track.setDaw("Fl");
		track.setBpm(90);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.create(track,6);
		});
		String expectedMessage = "price cannot be less than zero or greater than 6000";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}
