package gestion.compte.dao.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.aspectj.weaver.ast.Instanceof;

import gestion.compte.dao.CompteRepository;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_compte",length=2)
public abstract class Compte {
@Id
private Long numCompte;
@Column
private Date dateCreation;
@Column
private Double solde;
@ManyToOne
@JoinColumn(name="client_id")
private Client client;
@OneToMany(mappedBy="compte",fetch=FetchType.LAZY)
private List<Operation> operations;

public String whatCompte()
{
	
	if(this instanceof CompteCourant)
		return "Courant";
	return "Epargne";
}
public abstract void retrait(Double s)throws Exception ;
public abstract void verser(Double s);
public String whatField()
{
	
	if(this instanceof CompteCourant)
		return "Découvert";
	return "Taux";
}
public String Value()
{
	
	if(this instanceof CompteCourant)
	{
		CompteCourant c=(CompteCourant)this;
		return c.getDecouvert().toString();
	}
	CompteEpargne c=(CompteEpargne)this;
	return c.getTaux().toString();
}

public Compte() {
	super();
}
public Compte(Long numCompte, Date dateCreation, Double solde, Client client) {
	super();
	this.numCompte = numCompte;
	this.dateCreation = dateCreation;
	this.solde = solde;
	this.client = client;
}
public Long getNumCompte() {
	return numCompte;
}
public void setNumCompte(Long numCompte) {
	this.numCompte = numCompte;
}
public Date getDateCreation() {
	return dateCreation;
}
public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
}
public Double getSolde() {
	return solde;
}
public void setSolde(Double solde) {
	this.solde = solde;
}
public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}

}
