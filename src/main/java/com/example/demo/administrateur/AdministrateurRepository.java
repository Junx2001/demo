/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.administrateur;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ratsi
 */
@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur,String>{
	
	String QUERY_FIND_USER_FROM_DATBASE = "SELECT * from " +
		      "administrateur where " +
		      "email= ?1 and " +
		     "mdp=HashBytes('SHA2_256', convert(varchar,?2))";
	@Query(value = QUERY_FIND_USER_FROM_DATBASE, nativeQuery = true)
	Optional<Administrateur> findAdministrateurByEmailAndMdp(String email,String mdp);
    
}
