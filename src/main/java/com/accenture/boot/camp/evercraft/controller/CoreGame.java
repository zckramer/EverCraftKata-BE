package com.accenture.boot.camp.evercraft.controller;

import com.accenture.boot.camp.evercraft.model.CharacterSheet;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class CoreGame {
    public CoreGame(){}

    @GetMapping("/character")
    public CharacterSheet getCharacter(@RequestParam String characterName) {
        CharacterSheet newCharacter = new CharacterSheet(characterName, "Good");
        return newCharacter;
    }

}