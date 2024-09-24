package edu.uow.tic.visitor.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uow.tic.visitor.entity.Action;
import edu.uow.tic.visitor.entity.ActionType;
import edu.uow.tic.visitor.entity.Status;
import edu.uow.tic.visitor.entity.Visitor;
import edu.uow.tic.visitor.repository.VisitorRepository;
import edu.uow.tic.visitor.service.VisitorService;
import edu.uow.tic.visitor.utility.EventLogService;


@Service
public class VisitorServiceImpl implements VisitorService {

    private static final Logger logger = LogManager.getLogger(VisitorServiceImpl.class);

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private EventLogService eventLogService;

    @Override
    public Visitor createVisitorInformation(Visitor visitorInformation) {
        logger.info("Creating visitor information for: {}", visitorInformation.getVisitorName());
        try {
            Visitor savedVisitor = visitorRepository.save(visitorInformation);
            logger.info("Visitor information created successfully with ID: {}", savedVisitor.getId());
            
            // Log the event after successful creation
            eventLogService.saveEventLog(savedVisitor.getId(), savedVisitor.getVisitorName(), 
                ActionType.CREATE, Action.VISITOR_INFORMATION_CREATE, Status.SUCCESS);
            
            return savedVisitor;
        } catch (Exception e) {
            logger.error("Error occurred while creating visitor information", e);
            // Log the event in case of failure
            eventLogService.saveEventLog(null, visitorInformation.getVisitorName(), 
                ActionType.CREATE, Action.VISITOR_INFORMATION_CREATE, Status.ERROR);
            throw e;
        }
    }

    @Override
    public List<Visitor> getAllVisitorInformation() {
        logger.info("Fetching all visitor information.");
        List<Visitor> visitors = visitorRepository.findAll();
        logger.info("Successfully retrieved {} visitor records.", visitors.size());
        
        // Log the event after fetching all visitor information
        eventLogService.saveEventLog(1L, "admin", ActionType.GET_ALL, 
            Action.VISITOR_INFORMATION_GET_ALL, Status.SUCCESS);
        
        return visitors;
    }

    @Override
    public Optional<Visitor> getVisitorInformationById(Long id) {
        logger.info("Fetching visitor information by ID: {}", id);
        Optional<Visitor> visitor = visitorRepository.findById(id);

        if (visitor.isPresent()) {
            logger.info("Visitor information found for ID: {}", id);
            // Log success
            eventLogService.saveEventLog(visitor.get().getId(), visitor.get().getVisitorName(), 
                ActionType.GET_BY_ID, Action.VISITOR_INFORMATION_GET_BY_ID, Status.SUCCESS);
        } else {
            logger.warn("No visitor information found for ID: {}", id);
            // Log failure
            eventLogService.saveEventLog(id, "Unknown", ActionType.GET_BY_ID, 
                Action.VISITOR_INFORMATION_GET_BY_ID, Status.ERROR);
        }
        return visitor;
    }

    @Override
    public Visitor updateVisitorInformation(Long id, Visitor visitorInformation) {
        logger.info("Updating visitor information for ID: {}", id);
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
            
            Visitor savedVisitor = visitorRepository.save(updateVisitorInfo);
            logger.info("Visitor information updated successfully for ID: {}", id);
            
            // Log the event after successful update
            eventLogService.saveEventLog(savedVisitor.getId(), savedVisitor.getVisitorName(), 
                ActionType.UPDATE, Action.VISITOR_INFORMATION_UPDATE, Status.SUCCESS);
            
            return savedVisitor;
        } else {
            logger.warn("No visitor information found for update, ID: {}", id);
            // Log the event if the update fails
            eventLogService.saveEventLog(id, "Unknown", ActionType.UPDATE, 
                Action.VISITOR_INFORMATION_UPDATE, Status.ERROR);
            return null;
        }
    }

    @Override
    public void deleteVisitorInformation(Long id) {
        logger.info("Deleting visitor information for ID: {}", id);
        try {
            visitorRepository.deleteById(id);
            logger.info("Visitor information deleted successfully for ID: {}", id);
            
            // Log successful delete
            eventLogService.saveEventLog(id, "admin", ActionType.DELETE, 
                Action.VISITOR_INFORMATION_DELETE, Status.SUCCESS);
        } catch (Exception e) {
            logger.error("Error occurred while deleting visitor information for ID: {}", id, e);
            // Log failure in delete
            eventLogService.saveEventLog(id, "admin", ActionType.DELETE, 
                Action.VISITOR_INFORMATION_DELETE, Status.ERROR);
            throw e;
        }
    }
}
