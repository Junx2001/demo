package com.example.demo.groupement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupementRepository 
extends JpaRepository<Groupement,String>{


}
