package gestion.compte.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.compte.security.entities.Role;
import java.lang.String;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String>{
	public Role findByRole(String role);
}
