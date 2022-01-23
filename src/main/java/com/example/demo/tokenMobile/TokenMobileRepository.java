package com.example.demo.tokenMobile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface TokenMobileRepository extends JpaRepository<TokenMobile,String>{

	String QUERY_FIND_USER_FROM_DATBASE = "SELECT * from " +
		      "token_mobile where " +
		      "id_token= ?1 and date_expiration>getdate()";
	@Query(value = QUERY_FIND_USER_FROM_DATBASE, nativeQuery = true)
    public Optional<TokenMobile> findByTokenId(String id);

	


}