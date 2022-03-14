package gestion.compte;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import gestion.compte.dao.ClientRepository;
import gestion.compte.dao.CompteRepository;
import gestion.compte.dao.OperationRepository;
import gestion.compte.dao.entities.Client;
import gestion.compte.dao.entities.Compte;
import gestion.compte.dao.entities.CompteCourant;
import gestion.compte.dao.entities.Operation;
import gestion.compte.dao.entities.Retrait;
import gestion.compte.dao.entities.Versement;
import gestion.compte.security.dao.RoleRepository;
import gestion.compte.security.dao.UserRepository;
import gestion.compte.security.entities.Role;
import gestion.compte.security.entities.User;

@SpringBootApplication
public class GestionDesComptesApplication implements CommandLineRunner {
	@Autowired
	CompteRepository repo1;
	@Autowired
	ClientRepository repo2;
	@Autowired
	OperationRepository repo3;
	@Autowired
	UserRepository repo4;
	@Autowired
	RoleRepository repo5;
	public static BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
	public static void main(String[] args) {
		SpringApplication.run(GestionDesComptesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client c = new Client(1L, "HASSAN");
		repo2.save(c);
		for (int i = 1; i <= 3; i++) {
			repo1.save(new CompteCourant(Long.parseLong(String.valueOf(i)), new Date(), 10000D,100D, c));
		}
		for (int i = 1; i <= 30; i++) {
			Compte c1=repo1.findByNumCompte(1L);
			if(i%2==0)
			repo3.save(new Retrait(Long.parseLong(String.valueOf(i)), new Date(), 120D,c1));
			else
				repo3.save(new Versement(Long.parseLong(String.valueOf(i)), new Date(), 120D,c1));
				
		}
		Role r1=new Role("ADMIN");
		repo5.save(r1);
		Role r2=new Role("USER");
		repo5.save(r2);
		
		
		User u1=new User("admin", bc.encode("admin"), true);
		u1.addRole(r1);
		u1.addRole(r2);
		repo4.save(u1);
		
// delete
//				  repo1.deleteById(25L); 
//update 
//				  Election el=(Election)repo1.findByNumero(26L); el.setTitre("Update titre");
//				  repo1.save(el);
//		
	}

}
