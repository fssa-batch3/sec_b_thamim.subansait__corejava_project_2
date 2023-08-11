package in.fssa.doboo.service;

import java.util.Set;

import in.fssa.doboo.dao.TrackDAO;
import in.fssa.doboo.dao.UserDAO;
import in.fssa.doboo.model.TrackEntity;
import in.fssa.doboo.model.UserEntity;

public class TrackService {
	
	private TrackDAO trackDao;

	public TrackService() {
		this.trackDao = new TrackDAO(); // Initialize userDao instance in the constructor
	}
	
	// this is method is to get all tracks from the database.
	
	public Set<TrackEntity> getAll() {

		Set<TrackEntity> trackList = trackDao.findAll();

		return trackList;
	}
}
