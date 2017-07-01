package org.sid.metier;

import java.util.Date;

import org.sid.dao.entity.Compte;
import org.sid.dao.entity.CompteCourant;
import org.sid.dao.entity.Operation;
import org.sid.dao.entity.Retrait;
import org.sid.dao.entity.Versment;
import org.sid.dao.repository.CompteRepository;
import org.sid.dao.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IBanqueMetierImpl implements IBanqueMetier {

	@Autowired
	private CompteRepository cpt;
	@Autowired
	private OperationRepository op;

	@Override
	public Compte consulterCompte(String codeCompte) {
		Compte c = cpt.findOne(codeCompte);
		if (c == null) {
			throw new RuntimeException("Compte non trouver !!");
		}
		return c;
	}

	@Override
	public void verser(String codeCompte, double montant) {
		Compte  c = consulterCompte(codeCompte);
		Versment ver = new Versment(new Date(),montant,c);
		op.save(ver);
		c.setSolde(c.getSolde()+montant);
		cpt.save(c);
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		Compte  c = consulterCompte(codeCompte);
		double x = 0;
		if (c instanceof CompteCourant)
			x = ((CompteCourant) c).getDecouvert();
		if(c.getSolde()+x<montant)
		{
			throw new RuntimeException("solde insufisant !!");
		}
		Retrait ver = new Retrait(new Date(),montant,c);
		op.save(ver);
		c.setSolde(c.getSolde()-montant);
		cpt.save(c);

	}

	@Override
	public void virement(String codeCompte1, String codeCompte2, double montant) {
		verser(codeCompte2,montant);
		retirer(codeCompte1,montant);
	}

	@Override
	public Page<Operation> listOpration(String codeCompte, int page, int size) {
		// TODO Auto-generated method stub
		return op.listOperation(codeCompte, new PageRequest(page, size));
	}

}
