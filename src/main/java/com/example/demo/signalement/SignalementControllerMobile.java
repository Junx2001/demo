/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.signalement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(path = "/mobile/signalements")
public class SignalementControllerMobile {

    @Autowired
    private SignalementService signService;

    /*public SignalementControllerMobile(SignalementService signService) {
        this.signService = signService;
    }*/
    
	private String uploadLocation;
	
	
	public SignalementControllerMobile(@Value("${upload.location}") String uploadLocation) throws IOException {
		super();
		this.uploadLocation = uploadLocation;
		System.out.println(System.getProperty("user.dir")+"/"+uploadLocation);
		
		Path uploadPath = Paths.get(System.getProperty("user.dir")+"/"+uploadLocation);
		if(!Files.exists(uploadPath)) {
			Files.createDirectory(uploadPath);
		}
	}
    
	@GetMapping(path = "{util}")
    public List rechercheSignalementMobile(
            @PathVariable("util") String utilisateur,
            @RequestParam(required = false) String cat,
            @RequestParam(required = false) String sousCat,
            @RequestParam(required = false) String d1,
            @RequestParam(required = false) String d2,
            @RequestParam(required = false) String etat
    ) {

        return signService.rechercheSignalementMobile(utilisateur, cat, sousCat, d1, d2, etat);
    }
	
	
	@PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir")+"/"+uploadLocation));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder!");
        }
    }
	
    @PostMapping
    public @ResponseBody void envoiSignalement(
            Signalement s,
            @RequestParam("image") MultipartFile file
       ) throws IOException
    {
    	s.setNomImage(file.getOriginalFilename());
        signService.addSignalement(s);
    	System.out.println(s.getLatitude());
        try {
            Path root = Paths.get(System.getProperty("user.dir")+"/"+uploadLocation);
            if (!Files.exists(root)) {
                init();
            }
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        } 
    
    }
    
    
    
}
