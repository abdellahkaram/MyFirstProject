package org.sid;

import java.util.Date;

import org.sid.dao.entity.Client;
import org.sid.dao.entity.Compte;
import org.sid.dao.entity.CompteCourant;
import org.sid.dao.entity.CompteEpargne;
import org.sid.dao.entity.Operation;
import org.sid.dao.entity.Retrait;
import org.sid.dao.entity.Versment;
import org.sid.dao.repository.ClientRepository;
import org.sid.dao.repository.CompteRepository;
import org.sid.dao.repository.OperationRepository;
import org.sid.metier.IBanqueMetierImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BanqueApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clt;
	@Autowired
	private CompteRepository cpt;
	@Autowired
	private OperationRepository opr;
	@Autowired
	private IBanqueMetierImpl imetier;

	public static void main(String[] args) {
		SpringApplication.run(BanqueApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Client c1 = clt.save(new Client("hello", "hello@gmail.com"));
		Client c2 = clt.save(new Client("world", "world@gmail.com"));
		Compte cp1 = cpt.save(new CompteCourant("code01",new Date(),5000,c1,20));
		Compte cp2 = cpt.save(new CompteEpargne("code02",new Date(),7000,c2,30));
		opr.save(new Retrait(new Date(),500,cp1));
		opr.save(new Versment(new Date(),2000,cp1));
		opr.save(new Retrait(new Date(),2500,cp2));
		opr.save(new Versment(new Date(),3000,cp2));
		imetier.virement("code01", "code02", 200);
	}
}
