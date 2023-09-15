package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.exception.ServiceException;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.Assest;

import in.fssa.doboo.service.AssestService;


public class TestUpdateAssest {
	@Test
	public void testUpdateAssestWithValidInput() {

		Assest asset=new Assest();
		AssestService assetService = new AssestService();
		asset.setAudioUrl("1YG40IlRKNjh7-F_Czh1QYZpvbjpdVYcQ");
		asset.setImageUrl("https://cdn.openimage.cloud/2023/248/jfsUvrJDFDCy.webp");
		assertDoesNotThrow(() -> {
			assetService.updateAssest(asset, 1);
		});
	}
	
	// Invalid Input
		@Test
		public void testUpdateAssestWithInValidInput() {
			
			AssestService assetService = new AssestService();
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				assetService.updateAssest(null, 1);
			});
			String expectedMessage = "Invalid user input";
			String receivedMessage = exception.getMessage();
			assertTrue(expectedMessage.equals(receivedMessage));
		}
		
		// Invalid Input
				@Test
				public void testUpdateAssestWithInValidImageInput() {
					
					AssestService assetService = new AssestService();
					Assest asset=new Assest();
					asset.setAudioUrl("1YG40IlRKNjh7-F_Czh1QYZpvbjpdVYcQ");
					asset.setImageUrl("cdn.openimage.cloud/2023/248/jfsUvrJDFDCy.webp");
					Exception exception = assertThrows(ServiceException.class, () -> {
						assetService.updateAssest(asset, 1);
					});
					String expectedMessage = "imageUrl doesn't match the pattern";
					String receivedMessage = exception.getMessage();
					assertTrue(expectedMessage.equals(receivedMessage));
				}
				
				@Test

				public void testUpdateAssestWithInValidAudioInput() {
					Assest asset=new Assest();
					AssestService assetService = new AssestService();
					asset.setAudioUrl("http://cdn.openimage.cloud/2023/248/jfsUvrJDFDCy.webp");
					asset.setImageUrl("http://cdn.openimage.cloud/2023/248/jfsUvrJDFDCy.webp");
					Exception exception = assertThrows(ServiceException.class, () -> {
						assetService.updateAssest(asset, 1);
					});
					String expectedMessage = "don't paste http link. please paste the /d/{variable} only";
					String receivedMessage = exception.getMessage();
					System.out.println(receivedMessage);
					assertTrue(expectedMessage.equals(receivedMessage));
					
				}
				
}
