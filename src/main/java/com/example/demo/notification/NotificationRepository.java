/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.notification;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author ratsi
 */
public interface NotificationRepository
        extends MongoRepository<Notification, String> {

    @Query(value = "{utilisateur:'?0'}")
    List<Notification> findByUtilisateur(String utilisateur);
}
