package com.gomycode.DevopsGomyCode.entites;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Entity
@Table(name="cours")
public class Cours {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cours;

	
   private String nom_cours;
	

   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "etudinat_id", nullable = false)
   private Etudiant etudiant;



	  public void setTutorial(Etudiant etudiant) {
	    this.etudiant = etudiant;
	  }

	

}
