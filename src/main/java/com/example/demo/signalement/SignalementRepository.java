package com.example.demo.signalement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SignalementRepository 
		extends JpaRepository<Signalement,String>{
	
	@Query(nativeQuery = true, value ="select * from detailsSignalement")
	List<Object[]> getFicheSignalements();

}
