package gestion.compte.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import gestion.compte.dao.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation,Long>{
	public Page<Operation> findAll(Pageable page);
}
