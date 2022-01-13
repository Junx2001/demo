package com.example.demo.userFinal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserFinalRepository extends JpaRepository<UserFinal,String>{

}
