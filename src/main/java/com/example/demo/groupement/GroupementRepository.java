package com.example.demo.groupement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupementRepository 
extends JpaRepository<Groupement,String>{

	@Query(nativeQuery = true, value ="select NEXT VALUE FOR seq_groupement")
	String getNextSequence();
	
}
