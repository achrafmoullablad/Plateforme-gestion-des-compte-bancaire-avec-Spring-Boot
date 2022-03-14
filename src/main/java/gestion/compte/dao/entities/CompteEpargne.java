package gestion.compte.dao.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("E")
public class CompteEpargne extends Compte{
	@Column
	private Double taux;

	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteEpargne(Long numCompte, Date dateCreation, Double solde,Double taux, Client client) {
		super(numCompte, dateCreation, solde, client);
		this.taux=taux;
	}

	public CompteEpargne(Double taux) {
		super();
		this.taux = taux;
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

	@Override
	public void retrait(Double s) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verser(Double s) {
		// TODO Auto-generated method stub
		
	}
	
	
}
