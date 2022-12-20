package com.gomycode.DevopsGomyCode.repo;

import static org.assertj.core.api.Assertions.assertThat;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.gomycode.DevopsGomyCode.entites.Etudiant;
import com.gomycode.DevopsGomyCode.repositories.EtudinatRepository;

import java.util.Date;

@SpringBootTest
class RepoTest {

	@Autowired
    private EtudinatRepository personRepo;
 
    @Test
    void existsEtudiantBynom() {
    	Etudiant person = new Etudiant((long) 1001, "Achref", "kouki", 999999,new Date("11/12/2022"));
        personRepo.save(person);
        boolean actualResult = personRepo.existsEtudiantBynom("Achref");
        assertThat(actualResult).isTrue();
    }

}
