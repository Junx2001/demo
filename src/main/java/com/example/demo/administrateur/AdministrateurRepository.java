/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.administrateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ratsi
 */
@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur,String>{

    @Query(nativeQuery = true, value ="SELECT * FROM administrateur WHERE email =?1 AND mdp =HASHBYTES(\'SHA2_256\',?2)")
	Administrateur findAdministrateurByEmailAndMdp(String email, String mdp);
    
}
