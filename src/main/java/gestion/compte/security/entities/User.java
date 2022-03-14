package gestion.compte.security.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import gestion.compte.dao.entities.Client;

@Entity
@Table(name="users")
public class User {
	@Id
	@Size(max=25)
	private String username;
	@Column
	@Size(max=255)
	private String password;
	@Column
	private boolean active;
	 @ManyToMany(fetch = FetchType.LAZY,
	            cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            })
	@JoinTable(name="user_roles",
			joinColumns= {@JoinColumn(name="user")},
			inverseJoinColumns= {@JoinColumn(name="role")})
	private Set<Role> roles=new HashSet<Role>();

	/*
	 * @OneToOne(mappedBy = "user") private Client client;
	 */
	public void addRole(Role r)
	{
		this.roles.add(r);
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, boolean active) {
		super();
		this.username = username;
		this.password = password;
		this.active = active;
	}
	
	
}
