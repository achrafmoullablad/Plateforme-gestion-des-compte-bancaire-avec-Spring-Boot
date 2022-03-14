package gestion.compte.web;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gestion.compte.GestionDesComptesApplication;
import gestion.compte.dao.CompteRepository;
import gestion.compte.dao.OperationRepository;
import gestion.compte.dao.entities.Client;
import gestion.compte.dao.entities.Compte;
import gestion.compte.dao.entities.CompteCourant;
import gestion.compte.dao.entities.CompteEpargne;
import gestion.compte.dao.entities.Operation;
import gestion.compte.dao.entities.Retrait;
import gestion.compte.dao.entities.Versement;
import gestion.compte.security.dao.RoleRepository;
import gestion.compte.security.dao.UserRepository;
import gestion.compte.security.entities.Role;
import gestion.compte.security.entities.User;

@Controller
public class MainController {
	@Autowired
	CompteRepository compteRepo;
	@Autowired
	OperationRepository operationRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	
	@GetMapping("/admin/main")
	public String mainPage(Model model,
			@RequestParam(name="code_c",defaultValue="")String codeCompte,
			@RequestParam(name="page",defaultValue="0")int page
			) {
		//Client c = new Client(1L, "");
		Compte cp=null;//=new CompteEpargne(1L, new Date(), 1000D,1D, c);
		Page<Operation> listOperations=operationRepo.findAll( PageRequest.of(page, 5));
		if(!codeCompte.isEmpty())
      {
    	  cp= compteRepo.findByNumCompte(Long.parseLong(codeCompte));
    	  
      }
		System.out.println(listOperations.getSize());
		model.addAttribute("listop", listOperations);
		model.addAttribute("pages", new int[listOperations.getTotalPages()]);
		model.addAttribute("page", page);
		model.addAttribute("compte", cp);
		return "index";
	}
	@GetMapping("/inscription")
	public String inscriptionAction(Model model)
	{
		model.addAttribute("user", new User("", "",true));
		return "inscription";
	}
	
	@PostMapping("/inscription/save")
	public String checkAction(Model model,@Valid User user,BindingResult result)
	{
		System.out.println("test1");
		Role r1=roleRepo.findByRole("USER");
		User u1=user;
		u1.setActive(true);
		u1.setPassword(GestionDesComptesApplication.bc.encode((u1.getPassword())));
		
		u1.addRole(r1);
		//roleRepo.save(r1);
		userRepo.save(u1);
		return "redirect:/login";
	}
	
	@RequestMapping("/admin/main/save")
	public String saveAction(Model model,
			@RequestParam(name="code_c",defaultValue="")String codeCompte,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="operation",defaultValue="")String operation,
			@RequestParam(name="montant",defaultValue="")Double montant
			){
		int msg=0;
		
		if(codeCompte.isEmpty())
		{
			msg=2;
			return "redirect:/admin/main?msgerr="+msg;
		}
		
		if(operation.equals("retrait"))
		{
			try {
				Compte c=compteRepo.findByNumCompte(Long.parseLong(codeCompte));
				Retrait op=new Retrait(1L, new Date(), montant, c);
				operationRepo.save(op);
			} catch (Exception e) {
				e.printStackTrace();	
				msg=1;
			}
		
		}
		else
		{
			try {
				Compte c=compteRepo.findByNumCompte(Long.parseLong(codeCompte));
				Versement op=new Versement(199L, new Date(), montant, c);
				operationRepo.save(op);
				System.err.println(operation);	
			} catch (Exception e) {
				e.printStackTrace();	
			}
			
		}
		
	
		
		return "redirect:/admin/main?code_c="+codeCompte+"&msgerr="+msg;
	}
}
