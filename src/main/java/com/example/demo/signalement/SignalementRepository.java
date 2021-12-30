package com.example.demo.signalement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SignalementRepository 
		extends JpaRepository<Signalement,String>{
	
	
	@Query(nativeQuery = true, value ="select * from detailsSignalement")
	List<Object[]> getDetailsSignalements();
	
	@Query(nativeQuery = true, value ="select * from detailsSignalement where idSignalement=?1")
	List<Object[]> getFicheSignalement(String idSignalement);
	
	@Query(nativeQuery = true, value="select * from top4RegionSignalement")
	List<Object[]> getStatParRegion();
	
	@Query(nativeQuery = true, value="select * from statSousCategorie")
	List<Object[]> getStatSousCategorie();

        @Query(nativeQuery = true, value="select count(*) as nb,MONTH(date_resolu) as mois from groupement where YEAR(date_resolu)=?1 and etat=1 group by MONTH(date_resolu)")
        List<Object[]> getStatParMois(Integer annee);

}
