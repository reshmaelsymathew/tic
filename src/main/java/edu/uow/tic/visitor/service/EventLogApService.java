package edu.uow.tic.visitor.service;

import java.util.List;

import edu.uow.tic.visitor.dto.ActionTypeCountDTO;
import edu.uow.tic.visitor.dto.ActionTypeEventDateDTO;



public interface EventLogApService {
	public List<ActionTypeCountDTO> getCountByActionType();
	
	public List<ActionTypeEventDateDTO> getCountByActionTypeAndEventDate();
	


}
