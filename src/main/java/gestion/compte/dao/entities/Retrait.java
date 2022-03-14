package gestion.compte.dao.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation{

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Long numOperation, Date dateOperation, Double montant, Compte compte) throws Exception {
		super(numOperation, dateOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}
	

}
