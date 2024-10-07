package edu.uow.tic.visitor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.uow.tic.visitor.entity.EventLog;


public interface EventLogRepository extends JpaRepository<EventLog, Long> {
	
	@Query("SELECT COUNT(e) AS count, e.actionType, e.eventDate FROM EventLog e GROUP BY e.actionType, e.eventDate")
	List<Object[]> getCountByActionTypeAndEventDate();

	@Query("SELECT COUNT(e) AS count, e.actionType FROM EventLog e GROUP BY e.actionType")
	List<Object[]> getCountByActionType();


}
