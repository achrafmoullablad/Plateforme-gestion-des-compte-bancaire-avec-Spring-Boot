package gestion.compte.dao.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation{

	public Versement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Versement(Long numOperation, Date dateOperation, Double montant, Compte compte) throws Exception {
		super(numOperation, dateOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}

}
