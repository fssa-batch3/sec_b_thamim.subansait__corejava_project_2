package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import in.fssa.doboo.util.RandomValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.TrackEntity;
import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.service.TrackService;
import in.fssa.doboo.service.UserService;
@TestMethodOrder(OrderAnnotation.class)

public class TestCreateTrack {
	
	RandomValue value = new RandomValue();
	
	@Order(1)
	@Test
	
	public void testCreateTrackWithValidInput() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		String randomString = value.generateRandomString(8);
		track.setTrackName(randomString);
		track.setTrackDetail("this is the basic details");
		track.setScale("c minor");
		track.setPrice(500);
		track.setGenre("pop");
		track.setDaw("Fl");
		track.setBpm(90);
		assertDoesNotThrow(() -> {
			trackService.createTrack(track,1);
		});
	}
	@Order(2)
	
	@Test
	public void testCreateTrackWithInValiduserId() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		track.setTrackName("ewewq");
		track.setTrackDetail("this is the basic details");
		track.setScale("c minor");
		track.setPrice(100);
		track.setGenre("pop");
		track.setDaw("Fl");
		track.setBpm(90);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.createTrack(track,100);
		});
		String expectedMessage = "User ID does not exist";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	@Order(3)
	
	@Test
	public void testCreateTrackWithExtisTrackName() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		track.setTrackName("Pradeep Kumar");
		track.setTrackDetail("this is the basic details");
		track.setScale("c minor");
		track.setPrice(100);
		track.setGenre("pop");
		track.setDaw("Fl");
		track.setBpm(90);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.createTrack(track,17);
		});
		String expectedMessage = "Track name already exists for the user";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	@Order(4)
	@Test
	public void testCreateTrackWithnull() {
		TrackService trackService = new TrackService();
		TrackEntity track = new TrackEntity();
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.createTrack(null,17);
		});
		String expectedMessage = "track cannot be null";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	@Order(5)
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
			trackService.createTrack(track,17);
		});
		String expectedMessage = "TrackName cannot be null or empty";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	@Order(6)
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
			trackService.createTrack(track,17);
		});
		String expectedMessage = "price cannot be less than zero or greater than 6000";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	@Order(7)
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
			trackService.createTrack(track,17);
		});
		String expectedMessage = "price cannot be less than zero or greater than 6000";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}
