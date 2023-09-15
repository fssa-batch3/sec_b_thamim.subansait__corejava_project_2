package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.service.AssestService;

public class TestFindAsset {
	
	@Test
	public void testFindAssestByValidTrackId() {

		AssestService assetService = new AssestService();

		assertDoesNotThrow(() -> {
			System.out.println(assetService.findByTrackId(13));
		});
	}

	@Test
	public void testFindAssestByInValidTrackId() {

		AssestService assetService = new AssestService();
		Exception exception = assertThrows(RuntimeException.class, () -> {
			assetService.findByTrackId(100);
		});
		String expectedMessage = "track not found";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test
	public void testFindAssestByInValidTrackId2() {

		AssestService assetService = new AssestService();
		Exception exception = assertThrows(RuntimeException.class, () -> {
			assetService.findByTrackId(0);
		});
		String expectedMessage = "id is less than zero";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}
