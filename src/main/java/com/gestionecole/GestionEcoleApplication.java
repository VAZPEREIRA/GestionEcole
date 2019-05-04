package com.gestionecole;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gestionecole.dao.EleveRepository;
import com.gestionecole.entities.Eleve;

@SpringBootApplication
public class GestionEcoleApplication implements CommandLineRunner{
	@Autowired
	private EleveRepository eleveRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionEcoleApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		eleveRepository.save(
				new Eleve(null, "Ndiaye", "Modou", df.parse("1998-11-10"), "modou@gmail.com", "TerminalL1"));
		
		
		eleveRepository.save(
				new Eleve(null, "Ba", "Moussa", df.parse("2000-01-15"), "moussa@gmail.com", "TerminalL2"));
		
	
		eleveRepository.save(
				new Eleve(null, "Sall", "Fatou", df.parse("2003-08-18"), "fatou@gmail.com", "PremiereS1"));
		
		eleveRepository.save(
				new Eleve(null, "Diouf", "Nabou", df.parse("2001-08-18"), "nabou@gmail.com", "TerminalS2"));
		
		eleveRepository.findAll().forEach(e->{ 
			System.out.println(e.getNom());
		});
		
	}

}
