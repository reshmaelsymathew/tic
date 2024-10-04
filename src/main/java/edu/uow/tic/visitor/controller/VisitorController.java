package edu.uow.tic.visitor.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.uow.tic.visitor.entity.Visitor;
import edu.uow.tic.visitor.service.VisitorService;

@RestController
@RequestMapping("/api/visitorinformation")
//@CrossOrigin(origins = "http://localhost:5173")
public class VisitorController {

    // Initialize the logger
    private static final Logger logger = LogManager.getLogger(VisitorController.class);

    @Autowired
    private VisitorService visitorService;

    // Create new visitor information
    @PostMapping
    public ResponseEntity<Visitor> createVisitorInformation(@RequestBody Visitor visitorInformation) {
        logger.info("[TIC] Request received to create visitor information for: {}", visitorInformation.getVisitorName());
        
        Visitor newVisitorInformation = visitorService.createVisitorInformation(visitorInformation);
        logger.info("[TIC] Visitor information created successfully with ID: {}", newVisitorInformation.getId());
        
        return ResponseEntity.ok(newVisitorInformation);
    }

    // Get all visitor information
    @GetMapping
    public List<Visitor> getAllVisitorInformation() {
        logger.info("[TIC] Request received to get all visitor information.");
        
        List<Visitor> visitors = visitorService.getAllVisitorInformation();
        logger.info("[TIC] Successfully retrieved {} visitor records.", visitors.size());
        
        return visitors;
    }

    // Get visitor information by ID
    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitorInformationById(@PathVariable Long id) {
        logger.info("[TIC] Request received to get visitor information by ID: {}", id);
        
        Optional<Visitor> visitorInformation = visitorService.getVisitorInformationById(id);
        if (visitorInformation.isPresent()) {
            logger.info("[TIC] Visitor information found for ID: {}", id);
            return ResponseEntity.ok(visitorInformation.get());
        } else {
            logger.warn("[TIC] Visitor information not found for ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Update visitor information by ID
    @PutMapping("/{id}")
    public ResponseEntity<Visitor> updateVisitorInformation(@PathVariable Long id, @RequestBody Visitor visitorInformation) {
        logger.info("[TIC] Request received to update visitor information for ID: {}", id);
        
        Visitor updatedVisitorInformation = visitorService.updateVisitorInformation(id, visitorInformation);
        if (updatedVisitorInformation != null) {
            logger.info("[TIC] Visitor information updated successfully for ID: {}", id);
            return ResponseEntity.ok(updatedVisitorInformation);
        } else {
            logger.warn("[TIC] Visitor information not found for update, ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Delete visitor information by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitorInformation(@PathVariable Long id) {
        logger.info("[TIC] Request received to delete visitor information for ID: {}", id);
        
        visitorService.deleteVisitorInformation(id);
        logger.info("[TIC] Visitor information deleted successfully for ID: {}", id);
        
        return ResponseEntity.noContent().build();
    }
}
