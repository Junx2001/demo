/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.utilisateur;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ratsi
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,String>{

    @Query(value = "SELECT * from utilisateur where email = ?1", nativeQuery = true)
    public Optional<Utilisateur> findUtilByEmail(String email);
    
    @Query(value = "SELECT * from utilisateur where region = ?1", nativeQuery = true)
    public Optional<Utilisateur> findUtilByRegion(String region);
    
    @Query(nativeQuery = true, value ="select * from viewUtil")
	List<Object[]> getViewUtilisateur();
	
    
}
