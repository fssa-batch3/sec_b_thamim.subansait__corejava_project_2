package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.model.Artist;

import in.fssa.doboo.service.ArtistService;


public class TestGetAllArtists {
	@Test
	public void testGetAllTracks() {

		ArtistService artistService = new ArtistService();
		assertDoesNotThrow(() -> {
			List<Artist> artists = artistService.getAllArtists();
			for(Artist artist : artists) {
				System.out.println(artist);			
			}

		});
	}

}
