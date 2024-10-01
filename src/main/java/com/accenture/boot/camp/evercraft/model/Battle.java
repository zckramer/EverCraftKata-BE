package com.accenture.boot.camp.evercraft.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Battle {
    protected CharacterSheet player;
    protected CharacterSheet opponent;
    protected TargetAction attack;
    protected Boolean isPlayerTurn;
    protected String battleResult;
    protected int id;

    public Battle(CharacterSheet player, CharacterSheet opponent){
        this.player = player;
        this.opponent = opponent;
        this.attack = new TargetAction(player, opponent);
        this.isPlayerTurn = true;
        this.battleResult = "";
    }

    public void encounter(){
        while(player.getIsAlive() && opponent.getIsAlive()){
            if(isPlayerTurn){
                this.attack = new TargetAction(player, opponent);
                int attackRoll = player.roll(11);
                attack.dealDamage(attackRoll);
                this.isPlayerTurn = false;
            }
            else {
                this.attack = new TargetAction(opponent, player);
                int attackRoll = opponent.roll(12);
                attack.dealDamage(attackRoll);
                this.isPlayerTurn = true;
            }
        };

        if(!player.getIsAlive()){
            this.battleResult = "You Lose!";
        } else if(!opponent.getIsAlive()){
            this.battleResult = "You've defeated your enemy!";
        }
    };
}
