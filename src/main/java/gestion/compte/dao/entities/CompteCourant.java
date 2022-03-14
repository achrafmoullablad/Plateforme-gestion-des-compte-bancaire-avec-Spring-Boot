package gestion.compte.dao.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class CompteCourant extends Compte{
	@Column
	private Double decouvert;

	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteCourant(Long numCompte, Date dateCreation, Double solde,Double decouvert, Client client) {
		super(numCompte, dateCreation, solde, client);
		this.decouvert=decouvert;
	}

	public Double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(Double decouvert) {
		this.decouvert = decouvert;
	}

	@Override
	public void retrait(Double s) throws Exception {
		
		Double nsolde=this.getSolde()-s;
		if(nsolde>=0)
		this.setSolde(nsolde);
		else
			throw new Exception("Solde insuffisant");
			
		
		
	}

	@Override
	public void verser(Double s) {
		
		Double nsolde=this.getSolde()+s;
		
		this.setSolde(nsolde);
	}

	
	
}
