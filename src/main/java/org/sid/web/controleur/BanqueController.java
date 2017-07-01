package org.sid.web.controleur;

import org.sid.dao.entity.Compte;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BanqueController {
	
	@Autowired
	private IBanqueMetier metier;
	
	@RequestMapping("/operations")
	public String index()
	{
		return "comptes";
	}
	
	@RequestMapping("/consultercompte")
	public String consulter(Model model , String compte)
	{
		try
		{
			Compte c = metier.consulterCompte(compte);
			model.addAttribute("compte",c);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("exception",e);
		}	
		return "comptes";
	}
}