package com.example.demo.signalement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SignalementRepository 
		extends JpaRepository<Signalement,String>{
	
	
	@Query(nativeQuery = true, value ="select * from detailsSignalement  where etat!=1 or etat is null ")
	List<Object[]> getDetailsSignalements();
	
	@Query(nativeQuery = true, value ="select * from detailsSignalement where idSignalement=?1")
	List<Object[]> getFicheSignalement(String idSignalement);
	
	@Query(nativeQuery = true, value="select * from top4RegionSignalement")
	List<Object[]> getStatParRegion();
	
	@Query(nativeQuery = true, value="select * from statSousCategorie")
	List<Object[]> getStatSousCategorie();
	
	@Query(nativeQuery = true, value="select * from statSignalementParRegion")
	List<Object[]> getStatSignalementParRegion();
	
	@Query(nativeQuery = true, value="select * from signalement where region is null")
	List<Object[]> getStatSignalementSansRegion();

    @Query(nativeQuery = true, value="select count(*) as nb,MONTH(date_resolu) as mois from groupement where YEAR(date_resolu)=?1 and etat=1 group by MONTH(date_resolu)")
    List<Object[]> getStatParMois(Integer annee);
    
    @Query(nativeQuery = true, value="select count(*) as nb,MONTH(date_resolu) as mois,  r.nom as nomRegion  from groupement g join region r on r.id_region=g.region where YEAR(date_resolu)=?1 and etat=1 and region=?2 group by MONTH(date_resolu), r.nom")
    List<Object[]> getResoluParMoisParRegion(Integer annee, String idRegion);

    @Query(nativeQuery = true, value="select count(*) as nb,MONTH(s.date_signalement) as mois ,  r.nom as nomRegion from signalement s join groupement g on s.id_groupement=g.id_groupement join region r on r.id_region=g.region where g.etat=0 and YEAR(s.date_signalement)=?1 and g.region = ?2 group by MONTH(s.date_signalement), r.nom")
    List<Object[]> getSignalementsParMoisParRegion(Integer annee, String idRegion);

    
    @Query(nativeQuery = true, value="select * from detailsSignalement where (etat!=1 or etat is null) and dateSignalement >=?1 and dateSignalement <=?2  ")
    List<Object[]> rechercheSign(String d1, String d2);
    
    @Query(nativeQuery = true, value="select * from detailsSignalement where idRegion=?1 ")
	List<Object[]> getSignalementByRegion(String idRegion);
	
	@Query(nativeQuery = true, value="select " + 
			"	id_signalement," + 
			"	date_signalement," + 
			"	s.description," + 
			"	id_utilisateur," + 
			"	date_resolu," + 
			"	s.nom_image," + 
			"	etat " + 
			"from signalement s" + 
			"	join groupement g on g.id_groupement = s.id_groupement " + 
			"where s.id_groupement=?1")
	List<Object[]> getSignalementByGroupement(String idGroupement);

}
