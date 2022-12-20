package com.gomycode.DevopsGomyCode.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gomycode.DevopsGomyCode.entites.Cours;


@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {

	//List<Cours> findByEtudiantId(Long idEtudinat);

	//List<Cours> findByName(Long postId);
	  
	
}
