package com.example.demo.tokenFront;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface TokenFrontRepository extends JpaRepository<TokenFront,String>{

	String QUERY_FIND_USER_FROM_DATBASE = "SELECT * from " +
		      "token_front where " +
		      "id_token= ?1";
	@Query(value = QUERY_FIND_USER_FROM_DATBASE, nativeQuery = true)
    public Optional<TokenFront> findByTokenId(String id);

	


}