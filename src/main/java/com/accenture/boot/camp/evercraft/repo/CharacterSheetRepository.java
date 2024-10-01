package com.accenture.boot.camp.evercraft.repo;

import com.accenture.boot.camp.evercraft.entity.CharacterSheetEntity;
import org.springframework.data.repository.CrudRepository;

public interface CharacterSheetRepository extends CrudRepository<CharacterSheetEntity, Integer> {
}
