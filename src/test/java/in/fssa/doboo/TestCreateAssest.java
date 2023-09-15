package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.doboo.exception.ServiceException;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.Assest;

import in.fssa.doboo.service.AssestService;


public class TestCreateAssest {

	@Test
	
	public void testCreateAssetWithValidInput() {
		Assest asset=new Assest();
		AssestService assetService = new AssestService();
		asset.setAudioUrl("1YG40IlRKNjh7-F_Czh1QYZpvbjpdVYcQ");
		asset.setImageUrl("https://cdn.openimage.cloud/2023/248/jfsUvrJDFDCy.webp");
		assertDoesNotThrow(() -> {
			assetService.createAssest(asset, 9);
		});
	}
	
@Test
	
	public void testCreateAssetWithInValidImageInput() {
		Assest asset=new Assest();
		AssestService assetService = new AssestService();
		asset.setAudioUrl("1YG40IlRKNjh7-F_Czh1QYZpvbjpdVYcQ");
		asset.setImageUrl("cdn.openimage.cloud/2023/248/jfsUvrJDFDCy.webp");
		Exception exception = assertThrows(ServiceException.class, () -> {
			assetService.createAssest(asset, 9);
		});
		String expectedMessage = "imageUrl doesn't match the pattern";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
		
	}

@Test

public void testCreateAssetWithInValidAudioInput() {
	Assest asset=new Assest();
	AssestService assetService = new AssestService();
	asset.setAudioUrl("http://cdn.openimage.cloud/2023/248/jfsUvrJDFDCy.webp");
	asset.setImageUrl("http://cdn.openimage.cloud/2023/248/jfsUvrJDFDCy.webp");
	Exception exception = assertThrows(ServiceException.class, () -> {
		assetService.createAssest(asset, 9);
	});
	String expectedMessage = "don't paste http link. please paste the /d/{variable} only";
	String receivedMessage = exception.getMessage();
	System.out.println(receivedMessage);
	assertTrue(expectedMessage.equals(receivedMessage));
	
}

@Test

public void testCreateAssetWithInValidImageNull() {
	Assest asset=new Assest();
	AssestService assetService = new AssestService();
	asset.setAudioUrl("1YG40IlRKNjh7-F_Czh1QYZpvbjpdVYcQ");
	asset.setImageUrl(null);
	Exception exception = assertThrows(ServiceException.class, () -> {
		assetService.createAssest(asset, 9);
	});
	String expectedMessage = "imageUrl cannot be null or empty";
	String receivedMessage = exception.getMessage();
	System.out.println(receivedMessage);
	assertTrue(expectedMessage.equals(receivedMessage));
	
}

@Test

public void testCreateAssetWithInValidImageEmpty() {
	Assest asset=new Assest();
	AssestService assetService = new AssestService();
	asset.setAudioUrl("1YG40IlRKNjh7-F_Czh1QYZpvbjpdVYcQ");
	asset.setImageUrl("");
	Exception exception = assertThrows(ServiceException.class, () -> {
		assetService.createAssest(asset, 9);
	});
	String expectedMessage = "imageUrl cannot be null or empty";
	String receivedMessage = exception.getMessage();
	System.out.println(receivedMessage);
	assertTrue(expectedMessage.equals(receivedMessage));
	
}

@Test

public void testCreateAssetWithInValidAudioNull() {
	Assest asset=new Assest();
	AssestService assetService = new AssestService();
	asset.setAudioUrl(null);
	asset.setImageUrl("http://cdn.openimage.cloud/2023/248/jfsUvrJDFDCy.webp");
	Exception exception = assertThrows(ServiceException.class, () -> {
		assetService.createAssest(asset, 9);
	});
	String expectedMessage = "audioUrl cannot be null or empty";
	String receivedMessage = exception.getMessage();
	System.out.println(receivedMessage);
	assertTrue(expectedMessage.equals(receivedMessage));
	
}

@Test

public void testCreateAssetWithInValidAudioEmpty() {
	Assest asset=new Assest();
	AssestService assetService = new AssestService();
	asset.setAudioUrl("");
	asset.setImageUrl("http://cdn.openimage.cloud/2023/248/jfsUvrJDFDCy.webp");
	Exception exception = assertThrows(ServiceException.class, () -> {
		assetService.createAssest(asset, 9);
	});
	String expectedMessage = "audioUrl cannot be null or empty";
	String receivedMessage = exception.getMessage();
	System.out.println(receivedMessage);
	assertTrue(expectedMessage.equals(receivedMessage));
	
}


@Test

public void testCreateAssetWithNull() {

	AssestService assetService = new AssestService();
	Exception exception = assertThrows(ServiceException.class, () -> {
		assetService.createAssest(null, 9);
	});
	String expectedMessage = "can't leave image and audio empty or null";
	String receivedMessage = exception.getMessage();
	System.out.println(receivedMessage);
	assertTrue(expectedMessage.equals(receivedMessage));
	
}



}
