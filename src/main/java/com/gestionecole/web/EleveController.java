package com.gestionecole.web;


import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestionecole.dao.EleveRepository;
import com.gestionecole.entities.Eleve;

@Controller
public class EleveController {
	
	@Autowired
	private  EleveRepository eleveRepository;
	//@RequestMapping(value="/index",method=RequestMethod.GET)
	@GetMapping("/user/index")
	public String chercher(Model model,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="size", defaultValue="5")int s,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		Page<Eleve> pageEleves=eleveRepository.chercherEleves("%"+mc+"%", new PageRequest(p, s));
		model.addAttribute("listEleves",pageEleves.getContent());
		int[] pages=new int [pageEleves.getTotalPages()];
		
		int pagesCount=pageEleves.getTotalPages();
		model.addAttribute("pages" ,pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle",mc);
		return "eleves";
	}
	@GetMapping("/admin/delete")
	public  String delete(Long id, int page, String motCle)  {
		eleveRepository.deleteById(id);
		return "redirect:/user/index?page="+page+"&motCle="+motCle;
		
	}

	@GetMapping("/admin/formEleve")
	public  String form(Model model)  {
		model.addAttribute("eleve", new Eleve());
		return "FormEleve";
		
	}
	
	@GetMapping("/admin/edit")
	public  String edit(Model model, Long id)  {
		Eleve eleve=eleveRepository.findById(id).get();
		model.addAttribute("eleve",eleve);
		return "EditEleve";
		
	}
	
	@PostMapping("/admin/save")
	public  String save(Model model ,@Valid Eleve eleve, BindingResult bindingResult)  {
		
		eleveRepository.save(eleve);
		return "redirect:/user/index";
		
	}
	
	@GetMapping("/")
	public  String def()  {
		return "redirect:/user/index";
	}
	
	@GetMapping("/403")
	public  String notAutorized()  {
		return "403";
	}
	
	@GetMapping("/login")
	public  String login()  {
		return "login";
	}
}
