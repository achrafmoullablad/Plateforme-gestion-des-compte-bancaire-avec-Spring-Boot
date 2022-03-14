package gestion.compte.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GeneratorType;

import gestion.compte.security.entities.User;

@Entity
public class Client implements Serializable  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long code;
	@Column
	String nom;
	@OneToMany(mappedBy="client" ,fetch=FetchType.LAZY)
	List<Compte> comptes;

	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "username", referencedColumnName = "username") User user;
	 */
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Long code, String nom) {
		super();
		this.code = code;
		this.nom = nom;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
