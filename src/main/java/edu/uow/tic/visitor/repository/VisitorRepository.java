package edu.uow.tic.visitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uow.tic.visitor.entity.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

}

