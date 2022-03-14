package gestion.compte.dao.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_o",length=2)
public abstract class Operation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numOperation;
	@Column
	private Date dateOperation;
	@Column
	private Double montant;
	@ManyToOne
	//@JoinColumn(name = "c_id")
	private Compte compte;
	public String whatOp()
	{
		
		if(this instanceof Retrait)
			return "Retrait";
		return "Versement";
	}
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operation(Long numOperation, Date dateOperation, Double montant, Compte compte) throws Exception {
		super();
		this.numOperation = numOperation;
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
		if(this.whatOp().equals("Retrait"))
		this.compte.retrait(montant);
		
		if(this.whatOp().equals("Versement"))
			this.compte.verser(montant);
	}

	public Long getNumOperation() {
		return numOperation;
	}

	public void setNumOperation(Long numOperation) {
		this.numOperation = numOperation;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
