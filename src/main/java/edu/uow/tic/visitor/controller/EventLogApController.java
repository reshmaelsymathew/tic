package edu.uow.tic.visitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uow.tic.visitor.dto.ActionTypeCountDTO;
import edu.uow.tic.visitor.dto.ActionTypeEventDateDTO;
import edu.uow.tic.visitor.service.EventLogApService;



@RestController
@RequestMapping("/api/eventlogvisitor")
public class EventLogApController {
	
	@Autowired
	private EventLogApService eventLogApService;
	
	@GetMapping("/countByActionType") // Added endpoint for clarity
    public List<ActionTypeCountDTO> getCountByActionType() {
        return eventLogApService.getCountByActionType(); // Return the result from service
    }
	
	 @GetMapping("/countByActionTypeAndEventDate") // New endpoint for action type and event date
	    public List<ActionTypeEventDateDTO> getCountByActionTypeAndEventDate() {
	        return eventLogApService.getCountByActionTypeAndEventDate(); // Return the service response
	    }

}
