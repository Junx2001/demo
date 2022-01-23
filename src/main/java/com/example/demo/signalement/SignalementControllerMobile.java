/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.signalement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ratsi
 */
@RestController
@RequestMapping(path = "/mobile/signalement")
public class SignalementControllerMobile {

    @Autowired
    private SignalementService signService;

    public SignalementControllerMobile(SignalementService signService) {
        this.signService = signService;
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

    @PostMapping
    public @ResponseBody
    void envoiSignalement(
            Signalement s
    ) {
        signService.addSignalement(s);
    }
}
