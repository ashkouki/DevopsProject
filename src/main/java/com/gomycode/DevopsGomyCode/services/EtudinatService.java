package com.gomycode.DevopsGomyCode.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gomycode.DevopsGomyCode.entites.Etudiant;
import com.gomycode.DevopsGomyCode.repositories.EtudinatRepository;


@Service
public class EtudinatService {

	@Autowired
	EtudinatRepository etudinatrepository;
	



	public ResponseEntity<List<Etudiant>> getEtudinatbyservice(){
		
		 List<Etudiant> etudinat=new ArrayList<>();
		 
		 etudinat=etudinatrepository.findAll();
		 if (etudinat.isEmpty())
		return  
				new ResponseEntity<List<Etudiant>>(HttpStatus.NO_CONTENT);
		 
		 else 
			 return new ResponseEntity<List<Etudiant>>(etudinat,HttpStatus.OK);
		
	}
	
	
    public Page<Etudiant> findEtudinatWithPagination(int offset,int pageSize){
        Page<Etudiant> products = etudinatrepository.findAll(PageRequest.of(offset, pageSize));
        return  products;
    }
    
    public Page<Etudiant> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Etudiant> products = etudinatrepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  products;
    }
}
