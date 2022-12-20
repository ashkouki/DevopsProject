package com.gomycode.DevopsGomyCode.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Etudiant {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEtudinat;
@Column(name="nom",nullable = true,length =500)
	private String nom;
@Column(name="prenom",nullable = true,length =500)
	private String prenom;
@Column(name="telphone",nullable = true,length =500)
	private int telphone;

	@Temporal(TemporalType.DATE)
	@Column(name="date_inseartion")
	private Date datinsert;
	

	
}
