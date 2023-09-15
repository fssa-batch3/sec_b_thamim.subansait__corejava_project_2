package in.fssa.doboo.service;

import in.fssa.doboo.dao.AssestDAO;
import in.fssa.doboo.exception.PersistanceException;
import in.fssa.doboo.exception.ServiceException;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.Assest;
import in.fssa.doboo.validator.AssestValidator;
import in.fssa.doboo.validator.TrackValidator;

public class AssestService {
	
	public void createAssest(Assest assest, int trackId) throws PersistanceException {
		try {
			
			AssestValidator.validate(assest);
			AssestDAO assestDAO = new AssestDAO();
			assestDAO.createAssest(assest, trackId);
			
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		 catch (PersistanceException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
	}
	
	public void updateAssest(Assest assest, int trackId) throws PersistanceException {
		try {
			
			AssestValidator.validate(assest);
			TrackValidator.isIdValid(trackId);
			AssestDAO assestDAO = new AssestDAO();
			assestDAO.updateAsset(assest, trackId);
			
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		 catch (PersistanceException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
	}
	
	public Assest findByTrackId(int trackId) throws PersistanceException {
		Assest asset = null;
		try {
			
			TrackValidator.isIdValid(trackId);
			asset=new Assest();
			AssestDAO assestDAO = new AssestDAO();
			asset=assestDAO.findByTrackId(trackId);
			
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		 catch (PersistanceException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		return asset;
	}
	
	

}
