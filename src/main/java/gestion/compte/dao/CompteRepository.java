package gestion.compte.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.compte.dao.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String>{
	public Compte findByNumCompte(Long numero);

}
