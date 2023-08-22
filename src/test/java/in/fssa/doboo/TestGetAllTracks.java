package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.TrackEntity;
import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.service.TrackService;
import in.fssa.doboo.service.UserService;

public class TestGetAllTracks {
	
	@Test
	public void testGetAllTracks() {

		TrackService trackService = new TrackService();
		assertDoesNotThrow(() -> {
			Set<TrackEntity> tracks = trackService.getAll();
			for(TrackEntity track : tracks) {
				System.out.println(track);			
			}

		});
	}

	@Test
	public void testFindtrackArtistname() {

		TrackService trackService = new TrackService();

		assertDoesNotThrow(() -> {
			System.out.println(trackService.findTracksByAtirstName("thamimtommy"));
		});
	}
	
	@Test
	public void testMatchingTrackByTrackname() {

		TrackService trackService = new TrackService();

		assertDoesNotThrow(() -> {
			System.out.println(trackService.findMatchTrackByName("baby"));
		});
	}
	
	@Test
	public void testMatchingTrackByInvalidTrackname() {

		TrackService trackService = new TrackService();

		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.findMatchTrackByName("");
		});

		String expectedMessage = "trackName cannot be null or empty";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	@Test
	public void testMatchingTrackByNull() {

		TrackService trackService = new TrackService();

		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.findMatchTrackByName(null);
		});

		String expectedMessage = "trackName cannot be null or empty";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test
	public void testFindTrackByInvalidArtistName() {

		TrackService trackService = new TrackService();

		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.findTracksByAtirstName("");
		});

		String expectedMessage = "artistName cannot be null or empty";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test
	public void testFindTrackByInvalidArtistNameWithNull() {

		TrackService trackService = new TrackService();

		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.findTracksByAtirstName(null);
		});

		String expectedMessage = "artistName cannot be null or empty";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test
	public void testIfNoTrackIsFoundByTrackName() {

		TrackService trackService = new TrackService();

		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.findMatchTrackByName("wall e");
		});

		String expectedMessage = "No track has been found with the given name";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	@Test
	public void testIfNoTrackIsFoundByAtirstName() {

		TrackService trackService = new TrackService();

		Exception exception = assertThrows(RuntimeException.class, () -> {
			trackService.findTracksByAtirstName("kanye west");
		});

		String expectedMessage = "No tracks found for the artist";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}


}
