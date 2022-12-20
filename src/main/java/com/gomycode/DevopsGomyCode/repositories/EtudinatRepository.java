package com.gomycode.DevopsGomyCode.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.gomycode.DevopsGomyCode.entites.Etudiant;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;



@Repository
public interface EtudinatRepository  extends JpaRepository<Etudiant, Long>{

	
	boolean existsEtudiantBynom(String nom);
	 
	@Query(value="select * from etudiant e where e.prenom=?1",nativeQuery=true)
	
	
	List<Etudiant> Findbyprenom(String peronm);
	
	@Query(
			  value = "SELECT * FROM etudiant ORDER BY id_etudinat", 
			  countQuery = "SELECT count(*) FROM etudiant", 
			  nativeQuery = true)
			Page<Etudiant> findAllUsersWithPagination(Pageable pageable);


	
	

}
