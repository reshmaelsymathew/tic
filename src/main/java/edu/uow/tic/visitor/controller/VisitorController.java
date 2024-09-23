package edu.uow.tic.visitor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uow.tic.visitor.entity.Visitor;
import edu.uow.tic.visitor.service.VisitorService;

@RestController
@RequestMapping("/api/visitorinformation")
public class VisitorController {


    @Autowired
    private VisitorService visitorService;

    // Create new visitor information
    @PostMapping
    public ResponseEntity<Visitor> createVisitorInformation(@RequestBody Visitor visitorInformation) {
    	Visitor newVisitorInformation = visitorService.createVisitorInformation(visitorInformation);
        return ResponseEntity.ok(newVisitorInformation);
    }

    // Get all visitor information
    @GetMapping
    public List<Visitor> getAllVisitorInformation() {
        return visitorService.getAllVisitorInformation();
    }

    // Get visitor information by ID
    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitorInformationById(@PathVariable Long id) {
        Optional<Visitor> visitorInformation = visitorService.getVisitorInformationById(id);
        return visitorInformation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update visitor information by ID
    @PutMapping("/{id}")
    public ResponseEntity<Visitor> updateVisitorInformation(@PathVariable Long id, @RequestBody Visitor visitorInformation) {
    	Visitor updatedVisitorInformation = visitorService.updateVisitorInformation(id, visitorInformation);
        if (updatedVisitorInformation != null) {
            return ResponseEntity.ok(updatedVisitorInformation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete visitor information by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitorInformation(@PathVariable Long id) {
        visitorService.deleteVisitorInformation(id);
        return ResponseEntity.noContent().build();
    }
}