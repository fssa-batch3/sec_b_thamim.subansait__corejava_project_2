package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.exception.ServiceException;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.Artist;
import in.fssa.doboo.service.ArtistService;

public class TestCreateArtist {

	@Test

	public void testCreateArtisttWithValidInput() {
		Artist artist = new Artist();
		ArtistService artistService = new ArtistService();
		artist.setType("SongWriter");
		artist.setBio("Hi there I'm popsmoke I'm a music Producer. You can connect with me in this platform");
		artist.setArtistName("OgMakeMoney");
		artist.setFacebook(null);
		artist.setInsta(null);
		artist.setLinkedln(null);
		artist.setSpotify("https://www.facebook.com/thamimtommy");

		assertDoesNotThrow(() -> {
			artistService.createArtist(artist, 1);
		});
	}

	@Test

	public void testCreateArtisttWithNull() {
		ArtistService artistService = new ArtistService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			artistService.createArtist(null, 33);
		});
		String expectedMessage = "aritst cannot be null";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test

	public void testCreateArtisttWithEmpty() {
		Artist artist = new Artist();
		ArtistService artistService = new ArtistService();
		artist.setType("MusicProducer");
		artist.setBio("Hi there I'm Drake I'm a music Producer. You can connect with me in socail platform");
		artist.setArtistName(null);
		artist.setFacebook("https://www.facebook.com/thamimtommy");
		artist.setInsta("https://www.facebook.com/thamimtommy");
		artist.setLinkedln("https://www.facebook.com/thamimtommy");
		artist.setSpotify("https://www.facebook.com/thamimtommy");
		Exception exception = assertThrows(ValidationException.class, () -> {
			artistService.createArtist(artist, 33);
		});
		String expectedMessage = "ArtistName cannot be null or empty";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test

	public void testCreateArtistTypeWithNull() {
		Artist artist = new Artist();
		ArtistService artistService = new ArtistService();
		artist.setType(null);
		artist.setBio("Hi there I'm Drake I'm a music Producer. You can connect with me in socail platform");
		artist.setArtistName("drake");
		artist.setFacebook("https://www.facebook.com/thamimtommy");
		artist.setInsta("https://www.facebook.com/thamimtommy");
		artist.setLinkedln("https://www.facebook.com/thamimtommy");
		artist.setSpotify("https://www.facebook.com/thamimtommy");
		Exception exception = assertThrows(ValidationException.class, () -> {
			artistService.createArtist(artist, 33);
		});
		String expectedMessage = "ArtistType cannot be null or empty";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test

	public void testCreateArtistBioWithNull() {
		Artist artist = new Artist();
		ArtistService artistService = new ArtistService();
		artist.setType("MusicProducer");
		artist.setBio(null);
		artist.setArtistName("drake");
		artist.setFacebook("https://www.facebook.com/thamimtommy");
		artist.setInsta("https://www.facebook.com/thamimtommy");
		artist.setLinkedln("https://www.facebook.com/thamimtommy");
		artist.setSpotify("https://www.facebook.com/thamimtommy");
		Exception exception = assertThrows(ValidationException.class, () -> {
			artistService.createArtist(artist, 33);
		});
		String expectedMessage = "ArtistBio cannot be null or empty";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test

	public void testCreateArtistWithinvalidUserId() {
		Artist artist = new Artist();
		ArtistService artistService = new ArtistService();
		artist.setType("MusicProducer");
		artist.setBio("Hi there I'm Drake I'm a music Producer. You can connect with me in socail platform");
		artist.setArtistName("drake");
		artist.setFacebook("https://www.facebook.com/thamimtommy");
		artist.setInsta("https://www.facebook.com/thamimtommy");
		artist.setLinkedln("https://www.facebook.com/thamimtommy");
		artist.setSpotify("https://www.facebook.com/thamimtommy");
		Exception exception = assertThrows(ValidationException.class, () -> {
			artistService.createArtist(artist, 100);
		});
		String expectedMessage = "user doesn't exits";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test

	public void testCreateArtistWithinvalidLinkEmpty() {
		Artist artist = new Artist();
		ArtistService artistService = new ArtistService();
		artist.setType("MusicProducer");
		artist.setBio("Hi there I'm Drake I'm a music Producer. You can connect with me in socail platform");
		artist.setArtistName("drake");
		artist.setFacebook("");
		artist.setInsta("https://www.facebook.com/thamimtommy");
		artist.setLinkedln("https://www.facebook.com/thamimtommy");
		artist.setSpotify("https://www.facebook.com/thamimtommy");
		Exception exception = assertThrows(ValidationException.class, () -> {
			artistService.createArtist(artist, 33);
		});
		String expectedMessage = "social link is wrong";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}

}
