/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ratsi
 */
@Configuration
public class StudentConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository){
        return args ->{
            Student mariam = new Student(
                    "Mariam"
                    ,"mariam.jamal@gmail.com"
                    ,LocalDate.of(2000, Month.APRIL, 5)
                    );
            Student alex = new Student(
                    "Alex"
                    ,"alex@gmail.com"
                    ,LocalDate.of(2004, Month.APRIL, 5)
                    );
            List<Student> ls = new ArrayList<>();
            ls.add(mariam);
            ls.add(alex);
            repository.saveAll(
                   ls
            );
            

        };
    }
}
