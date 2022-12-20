package com.gomycode.DevopsGomyCode.controllers;

import java.util.List;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gomycode.DevopsGomyCode.entites.Cours;
import com.gomycode.DevopsGomyCode.repositories.CoursRepository;
import com.gomycode.DevopsGomyCode.repositories.EtudinatRepository;

@RestController
@RequestMapping("/cours")
public class CoursController {
	
	@Autowired
 private CoursRepository coursRepository;
	@Autowired
	EtudinatRepository etudinatRepository;

 @GetMapping("/findAll")
  public List<Cours> getAllCours(){
	  
	  return (List<Cours>)coursRepository.findAll();
  }
 
 
 @GetMapping("{id}")
 public ResponseEntity<Cours> getCourssByEtudiantId(@PathVariable(value = "id") Long id) {
	 Cours comment = coursRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

   return new ResponseEntity<>(comment,HttpStatus.OK);
 }
 
 @PutMapping("/{id}")
 public ResponseEntity<Cours> Updatecours(@PathVariable("id") long id, @RequestBody Cours commentRequest) {
	 Cours comment = coursRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

   comment.setNom_cours(commentRequest.getNom_cours());

   return new ResponseEntity<>(coursRepository.save(comment), HttpStatus.OK);
 }

 @DeleteMapping("{id}")
 public ResponseEntity<HttpStatus> deleteCours(@PathVariable("id") long id) {
	 coursRepository.deleteById(id);

   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }
 
 @PostMapping("/tutorials/{etudiantId}/cours")
 public ResponseEntity<Cours> CreateCours(@PathVariable(value = "etudiantId") Long etudiantId,
     @RequestBody Cours commentRequest) {
	 Cours comment = etudinatRepository.findById(etudiantId).map(tutorial -> {
     commentRequest.setTutorial(tutorial);
     return coursRepository.save(commentRequest);
   }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + etudiantId));

   return new ResponseEntity<>(comment, HttpStatus.CREATED);
 }

   

}
