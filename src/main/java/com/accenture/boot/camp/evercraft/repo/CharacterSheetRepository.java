package com.accenture.boot.camp.evercraft.repo;

import com.accenture.boot.camp.evercraft.entity.CharacterSheetEntity;
import com.accenture.boot.camp.evercraft.model.CharacterSheet;
import org.springframework.data.repository.CrudRepository;

public interface CharacterSheetRepository extends CrudRepository<CharacterSheetEntity, Integer> {
    CharacterSheetEntity findByName(String name);
}

