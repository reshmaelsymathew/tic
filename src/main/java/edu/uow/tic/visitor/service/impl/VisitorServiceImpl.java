package edu.uow.tic.visitor.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uow.tic.visitor.entity.Visitor;
import edu.uow.tic.visitor.repository.VisitorRepository;
import edu.uow.tic.visitor.service.VisitorService;

@Service
public class VisitorServiceImpl implements VisitorService{

	 @Autowired
	    private VisitorRepository visitorRepository;

	    @Override
	    public Visitor createVisitorInformation(Visitor visitorInformation) {
	        return visitorRepository.save(visitorInformation);
	    }

	    @Override
	    public List<Visitor> getAllVisitorInformation() {
	        return visitorRepository.findAll();
	    }

	    @Override
	    public Optional<Visitor> getVisitorInformationById(Long id) {
	        return visitorRepository.findById(id);
	    }

	    @Override
	    public Visitor updateVisitorInformation(Long id, Visitor visitorInformation) {
	        Optional<Visitor> existingVisitorInfo = visitorRepository.findById(id);
	        if (existingVisitorInfo.isPresent()) {
	        	Visitor updateVisitorInfo = existingVisitorInfo.get();
	            updateVisitorInfo.setVisitorName(visitorInformation.getVisitorName());
	            updateVisitorInfo.setEmail(visitorInformation.getEmail());
	            updateVisitorInfo.setPhoneNumber(visitorInformation.getPhoneNumber());
	            updateVisitorInfo.setNationality(visitorInformation.getNationality());
	            updateVisitorInfo.setPreferredLanguage(visitorInformation.getPreferredLanguage());
	            updateVisitorInfo.setInquiry(visitorInformation.getInquiry());
	            updateVisitorInfo.setVisitDate(visitorInformation.getVisitDate());
	            return visitorRepository.save(updateVisitorInfo);
	        } else {
	            return null; // Handle no such visitor
	        }
	    }

	    @Override
	    public void deleteVisitorInformation(Long id) {
	    	visitorRepository.deleteById(id);
	    }

}
