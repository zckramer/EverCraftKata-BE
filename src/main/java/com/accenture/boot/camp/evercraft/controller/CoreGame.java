package com.accenture.boot.camp.evercraft.controller;

import com.accenture.boot.camp.evercraft.entity.CharacterSheetEntity;
import com.accenture.boot.camp.evercraft.model.Battle;
import com.accenture.boot.camp.evercraft.model.CharacterSheet;
import com.accenture.boot.camp.evercraft.repo.CharacterSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class CoreGame {
    protected CharacterSheetRepository characterSheetRepository;

    public CoreGame(@Autowired CharacterSheetRepository characterSheetRepository ) {
        this.characterSheetRepository = characterSheetRepository;
    }

    @GetMapping("/newCharacter")
    public CharacterSheet getCharacter(@RequestParam String characterName, String alignment) {
        return new CharacterSheet(characterName, alignment);
    }

    @GetMapping("/getCharacter")
    public CharacterSheetEntity getCharacterById(@RequestParam Integer characterId) {
        return characterSheetRepository.findById(characterId).get();
    }

    @GetMapping("/character")
    public CharacterSheetEntity getCharacterByName(@RequestParam String characterName) {
        return characterSheetRepository.findByName(characterName);
    }
};

//    @GetMapping("/newBattle")
//    public void encounter(@RequestParam CharacterSheet player, CharacterSheet opponent) {
//
//    }