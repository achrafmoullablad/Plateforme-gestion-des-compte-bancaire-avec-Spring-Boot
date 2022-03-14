package gestion.compte.security.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="roles")
public class Role {
	@Id
	@Size(max=25)
	private String role;
	
	@ManyToMany(fetch=FetchType.LAZY,  cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        },mappedBy="roles")
	private Set<User> users=new HashSet<User>();
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(@Size(max = 25) String role) {
		super();
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
