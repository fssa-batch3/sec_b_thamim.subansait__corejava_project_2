package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.service.TrackService;
import in.fssa.doboo.service.UserService;

public class TestDeleteTrack {
	@Test
	public void testDeleteTrackWithValidInput() {

		TrackService trackService = new TrackService();

		assertDoesNotThrow(() -> {
			trackService.deleteTrack(1);
		});
	}

	// Id is Invalid
	@Test
	public void testDeleteUserWithInvalidId() {

		TrackService trackService = new TrackService();
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.deleteTrack(0);
		});

		String expectedMessage = "id is less than zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	// Id check
	@Test
	public void testUserCheckIdPresent() {

		TrackService trackService = new TrackService();
		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.deleteTrack(10);
		});

		String expectedMessage = "track not found";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}

}
