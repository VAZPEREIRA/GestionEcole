package com.gestionecole.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestionecole.entities.Eleve;

public interface EleveRepository extends JpaRepository<Eleve, Long>{
	
	 @Query("select e from Eleve e where e.nom like :x")
	 public  Page<Eleve>  chercherEleves(@Param("x")String mc,Pageable pageable);
	
  //public  Page<Eleve>  findByNomContains(String mc, Pageable pageable);

	

}
