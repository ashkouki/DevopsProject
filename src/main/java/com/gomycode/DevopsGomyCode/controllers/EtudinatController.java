package com.gomycode.DevopsGomyCode.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gomycode.DevopsGomyCode.entites.Etudiant;
import com.gomycode.DevopsGomyCode.repositories.EtudinatRepository;
import com.gomycode.DevopsGomyCode.services.EtudinatService;


import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/api")
public class EtudinatController {
	
	@Autowired
	EtudinatRepository etudinatRepository;
	
@Autowired
	EtudinatService etudinatService;


@GetMapping("/search/{nom}")
public boolean  existsEtudiantBynom(@PathVariable String nom){
	
	return etudinatRepository.existsEtudiantBynom(nom);
}


@GetMapping("/sorted/{filed}")
public List<Etudiant>  getalletudinatSorted(@PathVariable String filed){
	
	return etudinatRepository.findAll(Sort.by(filed));
}

@GetMapping("/pagination/{offset}/{pageSize}/{filed}")
public Page<Etudiant> findEtudinatsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String filed)
{
	
	  Page<Etudiant> productsWithPagination=etudinatService.findProductsWithPaginationAndSorting(offset, pageSize,filed);
	  
	  return productsWithPagination;
}




@GetMapping("/getetudiants")
public ResponseEntity<List<Etudiant>> getEtudinatbyservices(){
	
	return etudinatService.getEtudinatbyservice();
}
	

	
	@GetMapping("/etudinat/{id}")
	public ResponseEntity<Etudiant> getEtudinatByID(@PathVariable(required = false) long id) {
	 
		try {
		Optional<Etudiant> etudinatID=etudinatRepository.findById(id);
				if (etudinatID.isPresent())
					return new ResponseEntity<>(etudinatID.get(),HttpStatus.OK);
					
				else 
					return  new ResponseEntity<>(HttpStatus.NO_CONTENT);}
		
		catch(Exception e) {
			
			  return new ResponseEntity<>(HttpStatus
					  .BAD_REQUEST);
			  
		}
		}
	
	
	
	@PostMapping("/add-Etudinat")
	public Etudiant  saveEtudinat(@RequestBody Etudiant etudiant) {
		log.info("save etudinat avec success");
		return etudinatRepository.save(etudiant);
	}
	
	@DeleteMapping("deleteEtudinat/{id}")
	 public ResponseEntity<HttpStatus> deleteEtudinat(@PathVariable long id) {
		 try {
			 
		  etudinatRepository.deleteById(id);
		  log.info("l'etudinat est supprimé");
		 return new ResponseEntity<>(HttpStatus.OK);
		 }
		 catch(Exception e) {
			 return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
	
	@PutMapping("/{id}")
	public ResponseEntity<Etudiant> updateEtudinat(@PathVariable long ID , @RequestBody Etudiant etudinat){
		
		Optional<Etudiant> etudinatid=etudinatRepository.findById(ID);
		
		if ( etudinatid.isPresent()) {
		
			Etudiant _etudinat=etudinatid.get();
			
			_etudinat.setNom(etudinat.getNom());
			_etudinat.setPrenom(etudinat.getPrenom());
			_etudinat.setTelphone(etudinat.getTelphone());
			
			return new ResponseEntity<>(etudinatRepository.save(_etudinat),HttpStatus.OK);
			
		}
		else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("/{prenom}")
	public  ResponseEntity<List<Etudiant>> findbyprenom(@RequestParam(required = true) String peronm){
		
	  List<Etudiant>  etudinatprenom=etudinatRepository.Findbyprenom(peronm);
	  
	 if (etudinatprenom.isEmpty())
	 {
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  
	}
	else
	{
	 return new ResponseEntity<>(etudinatprenom,HttpStatus.OK);
	}
}}
