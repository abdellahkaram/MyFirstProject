package org.sid.dao.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("v")
public class Versment extends Operation {

	public Versment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Versment(Date dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}
	
	

}
