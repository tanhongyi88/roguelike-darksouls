package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;

public class YhormTheGiant extends LordOfCinder{
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private final static int YHORM_THE_GIANT_SOULS = 5000;

    public YhormTheGiant(String name, char displayChar, int hitPoints, Actor player) {
        super(name, displayChar, hitPoints);
        behaviours.add(new FollowBehaviour(player));
        this.addCapability(Status.WEAK_TO_STORM_RULER);
        this.addItemToInventory(new YhormsGreatMachete());
    }

    @Override
    public void transferSouls(Soul playerSoul) {
        playerSoul.addSouls(YHORM_THE_GIANT_SOULS);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(this.hasCapability(Status.STUN)){
            this.removeCapability(Status.STUN);
            return new DoNothingAction();
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    public String toString(){
        return name + " (" + hitPoints + "/" + maxHitPoints +")(" + getWeapon() + ")";
    }
}
