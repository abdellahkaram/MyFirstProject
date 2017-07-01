package org.sid.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cc")
public class CompteCourant extends Compte implements Serializable {
	
	private double decouvert;

	public CompteCourant() {
		super();
	}

	public CompteCourant(String code, Date dateCreation, double solde, Client client , double decou) {
		super(code, dateCreation, solde, client);
		this.decouvert = decou;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
	

}
