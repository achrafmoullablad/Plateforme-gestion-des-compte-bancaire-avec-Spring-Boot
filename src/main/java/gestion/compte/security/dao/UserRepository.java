package gestion.compte.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.compte.security.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
