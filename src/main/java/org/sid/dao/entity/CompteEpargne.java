package org.sid.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("ce")
public class CompteEpargne extends Compte implements Serializable {

	private double taux;

	public CompteEpargne(String code, Date dateCreation, double solde, Client client, double t) {
		super(code, dateCreation, solde, client);
		taux = t;
	}

	public CompteEpargne() {
		super();
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

}
