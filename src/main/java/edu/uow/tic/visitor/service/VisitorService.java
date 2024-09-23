package edu.uow.tic.visitor.service;

import java.util.List;
import java.util.Optional;

import edu.uow.tic.visitor.entity.Visitor;

public interface VisitorService {
	
	Visitor createVisitorInformation(Visitor visitorInformation);
    List<Visitor> getAllVisitorInformation();
    Optional<Visitor> getVisitorInformationById(Long id);
    Visitor updateVisitorInformation(Long id, Visitor visitorInformation);
    void deleteVisitorInformation(Long id);

}
