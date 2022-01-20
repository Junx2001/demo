package com.example.demo.userFinal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserFinalRepository extends JpaRepository<UserFinal,String>{

	String QUERY_FIND_USER_FROM_DATBASE = "SELECT * from " +
		      "user_final where " +
		      "email= ?1 and " +
		     "mdp=HashBytes('SHA2_256', convert(varchar,?2))";
	@Query(value = QUERY_FIND_USER_FROM_DATBASE, nativeQuery = true)
	Optional<UserFinal> findUserFinalByEmailAndMdp(String email, String mdp);
	
	@Query(value = "SELECT * from user_final where email = ?1", nativeQuery = true)
    public Optional<UserFinal> findUserFinalByEmail(String email);

}