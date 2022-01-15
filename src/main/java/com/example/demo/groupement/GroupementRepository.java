package com.example.demo.groupement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GroupementRepository 
extends JpaRepository<Groupement,String>{
	
	@Query(nativeQuery = true, value ="select NEXT VALUE FOR seq_groupement")
	String getNextSequence();
	
}
