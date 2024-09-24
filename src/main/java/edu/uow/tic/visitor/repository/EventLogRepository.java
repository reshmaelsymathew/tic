package edu.uow.tic.visitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.uow.tic.visitor.entity.EventLog;


public interface EventLogRepository extends JpaRepository<EventLog, Long> {

}
