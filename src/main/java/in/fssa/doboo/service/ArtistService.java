package in.fssa.doboo.service;

import java.util.List;

import in.fssa.doboo.dao.ArtistDAO;
import in.fssa.doboo.exception.PersistanceException;
import in.fssa.doboo.exception.ServiceException;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.Artist;
import in.fssa.doboo.validator.ArtistValidator;

public class ArtistService {
    private ArtistDAO artistDAO; // Replace with your actual AssetDAO instance

    public ArtistService() {
        this.artistDAO = new ArtistDAO();
    }

    public void createArtist(Artist artist, int userId) throws ServiceException, ValidationException {
        try {
            // You can perform any additional business logic or validation here if needed
        	
        	
        	
        	ArtistValidator.validate(artist, userId);
        	ArtistValidator.validateUser(userId);
        	
        	if(!artist.getInsta().isEmpty()) {
        	ArtistValidator.validUrl(artist.getInsta());
        	}
        	if(!artist.getFacebook().isEmpty()) {
            	ArtistValidator.validUrl(artist.getFacebook());
            	}
        	if(!artist.getLinkedln().isEmpty()) {
            	ArtistValidator.validUrl(artist.getLinkedln());
            	}
        	if(!artist.getSpotify().isEmpty()) {
            	ArtistValidator.validUrl(artist.getSpotify());
            	}
       
            // Call the DAO method to create the asset
        	
        	artistDAO.createArtist(artist, userId);
        	UserService userService = new UserService();
        	userService.setArtistNameAndRole(userId, artist.getArtistName());
            
            System.out.println("Artist is successfully created");
        } catch (PersistanceException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    
    public List<Artist> getAllArtists() throws ServiceException {
    	List<Artist> artists = null;
    	try {
            // You can perform any additional business logic or filtering here if needed
            
            // Call the DAO method to retrieve all artists
           artists = artistDAO.getAllArtists();
           
        } catch (PersistanceException e) {
            throw new ServiceException(e.getMessage());
        }
    	return artists;
    }
    
    public void updateArtistDetails(Artist artist,int userId) throws ServiceException, ValidationException {
        try {
        	
        	
        	ArtistValidator.validate(artist, userId);
        	ArtistValidator.validateUser(userId);

        	if(!artist.getInsta().isEmpty()) {
        	ArtistValidator.validUrl(artist.getInsta());
        	}
        	if(!artist.getFacebook().isEmpty()) {
            	ArtistValidator.validUrl(artist.getFacebook());
            	}
        	if(!artist.getLinkedln().isEmpty()) {
            	ArtistValidator.validUrl(artist.getLinkedln());
            	}
        	if(!artist.getSpotify().isEmpty()) {
            	ArtistValidator.validUrl(artist.getSpotify());
            	}
        	
        	artistDAO.updateArtistDetails(artist,userId);
        } catch (PersistanceException | ValidationException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    
    public Artist findArtistByUserId(int userId) throws ServiceException {
        try {
        	ArtistValidator.validateUser(userId);
            return artistDAO.findArtistByUserId(userId);
        } catch (PersistanceException | ValidationException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    
    
}

