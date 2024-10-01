package com.accenture.boot.camp.evercraft.controller;

import com.accenture.boot.camp.evercraft.model.Battle;
import com.accenture.boot.camp.evercraft.model.CharacterSheet;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class CoreGame {
    public CoreGame() {
    }

    @GetMapping("/character")
    public CharacterSheet getCharacter(@RequestParam String characterName, String alignment) {
        return new CharacterSheet(characterName, alignment);
    }
};

//    @GetMapping("/newBattle")
//    public void encounter(@RequestParam CharacterSheet player, CharacterSheet opponent) {
//
//    }