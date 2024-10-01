package com.accenture.boot.camp.evercraft;

import com.accenture.boot.camp.evercraft.model.CharacterSheet;
import com.accenture.boot.camp.evercraft.model.TargetAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class EvercraftApplicationTests {

    @Autowired
    private MockMvc mvc;
    private CharacterSheet newCharacter;
    private CharacterSheet targetCharacter;
    private TargetAction attack;

    @BeforeEach
    public void beforeEach() {
        newCharacter = new CharacterSheet("Edgar", "Good");
        targetCharacter = new CharacterSheet("Kefka", "Evil");
    }

    @Test
    void person_endpoint_will_default_name_to_person_if_a_name_is_not_provided_as_a_request_param() throws Exception
    {
        // given the user does not provide a name in the request parameters

        // when we call the endpoint
        MvcResult result = mvc.perform(get("/person")).andReturn();
        // then the endpoint greets us as "World"
        Assertions.assertEquals(200, result.getResponse().getStatus());
        Assertions.assertEquals("Hello, World", result.getResponse().getContentAsString());
    }

    @Test
    void person_endpoint_will_greet_you_by_your_first_name_if_provided() throws Exception
    {
        // given the user provides their name in the request parameter
        String name = "Asong";

        // when we call the endpoint
        MvcResult result = mvc.perform(get("/person?name="+name)).andReturn();

        // then the endpoint greets us by name
        Assertions.assertEquals(200, result.getResponse().getStatus());
        Assertions.assertEquals("Hello, " + name , result.getResponse().getContentAsString());

    }

    @Test
    void new_character_possesses_attributes_with_default_values() throws Exception
    {
        // given the new character sheet has a name
        // when the character is initialized with the name

        // then the instance of the character has the expected name attribute
        String expectedName = "Edgar";
        Assertions.assertEquals(expectedName, newCharacter.getName());
    }

    @Test
    void new_character_posses_alignment_default_attribute_when_instantiated() throws Exception
    {
        // given there is a new Character Sheet
        // when the instance is called
        // then the instance of the character has the expected attribute "Good"
        String expectedAlignment = "Good";
        Assertions.assertEquals(expectedAlignment, newCharacter.getAlignment());
    }

    @Test
    void character_combatant_possesses_default_values_for_armor_class() throws Exception
    {
        // given a new Character Sheet
        // when I call the instance of the character
        // then the instance should possess the correct default attributes for armor class & hit points
        int expectedArmorClass = 10;
        int expectedHitPoints = 5;
        Assertions.assertEquals(expectedArmorClass, newCharacter.getArmorClass());
        Assertions.assertEquals(expectedHitPoints, newCharacter.getHitPoints());
    }

    @Test
    void die_roll_returns_a_valid_number() throws Exception
    {
        // given a new Character Sheet
        // when I roll the die
        // then the die roll will return a number between 1 and 20
        int expectedNonValidNumber = 21;
        int expectedValidNumber = 4;

        Assertions.assertEquals(expectedValidNumber, newCharacter.roll(expectedValidNumber));
        Assertions.assertEquals(-1, newCharacter.roll(expectedNonValidNumber));
    }


    @Test
    @DisplayName("Character is able to land a successful roll")
    void character_meets_or_beats_target_character_armor_class_to_land_a_hit() throws Exception {
        // given a character is performing an attack
        // When the die is rolled to attack a target
        // And the dieNumber returned is greater than or equal to the target's armorClass
        // Then the attack is successful
        int roll = newCharacter.roll(10);
        attack = new TargetAction(newCharacter, targetCharacter);
        Assertions.assertTrue(attack.isSuccessful(roll));
    }

    @Test
    @DisplayName("Character doesn't land a successful roll")
    void character_does_not_meet_or_beat_target_character_armor_class() throws Exception
    {
        // given a character is performing an attack
        // when the die is rolled to attack a target
        // and the die number returned is not greater than or equal to the target's armor class
        // then the attack is not successful
        int roll = newCharacter.roll(5);
        attack = new TargetAction(newCharacter, targetCharacter);
        Assertions.assertFalse(attack.isSuccessful(roll));
    }

    @Test
    @DisplayName("Target character takes damage and loses 1 hit point")
    void target_character_takes_damage_and_loses_a_hit_point() throws Exception {
        // given a character's attack is on the target character
        int roll = newCharacter.roll(10);
        attack = new TargetAction(newCharacter, targetCharacter);
        // when the target character is hit is successful
        // then the target character loses 1 hit point
        Assertions.assertEquals(targetCharacter.getHitPoints()-1, attack.dealDamage(roll));
    }

    @Test
    @DisplayName("Target character does not take damage when roll is not successful")
    void no_damage_is_dealt_if_roll_is_not_successful() throws Exception {
        // given a character's attack is on the target character
        int roll = newCharacter.roll(5);
        attack = new TargetAction(newCharacter, targetCharacter);
        // when the target character is not hit
        // then the target character should not lose any points
        Assertions.assertEquals(targetCharacter.getHitPoints(), attack.dealDamage(roll));
    }

    @Test
    @DisplayName("Should deal double damage when die roll is 20")
    void double_damage_deal_when_die_roll_is_twenty() throws Exception
    {
        // given a character's attack is on the target character
        int roll = newCharacter.roll(20);
        attack = new TargetAction(newCharacter, targetCharacter);
        int targetHitPointBeforeAttack = targetCharacter.getHitPoints();

        // And the die roll is equal to 20
        // when the target character is hit
        int preRollDamage = attack.getDamage();
        attack.dealDamage(roll);
        int criticalDamage = attack.getDamage();
        // then the damage dealt to target character is doubled
        int targetHitPointsAfterAttack = targetHitPointBeforeAttack - criticalDamage;

        Assertions.assertEquals(targetHitPointsAfterAttack, targetHitPointBeforeAttack - criticalDamage);
        Assertions.assertEquals(criticalDamage, preRollDamage * 2);
    }

    @Test
    @DisplayName("When the target character's hit points is  0, then the player dies")
    void target_character_dies_when_hit_points_is_zero() throws Exception
    {
        // given a character
        // when the character's hit point is zero
        int health = targetCharacter.getHitPoints();
        targetCharacter.takeDamage(health);
        // then the character is dead
        Assertions.assertFalse(targetCharacter.getIsAlive());
    }
}