package lbuang.Models.Villians;

import java.util.Random;

import lbuang.Models.Weapons.Artifacts;

public class Villian {
   private int xcoordinator;
    private int ycoordinator;
    private int level, attack, defense, hitpoints, experience, power;
    private long id;
    private long idCounter = 1;
    private int typeID;
    private Artifacts artifact;
    private static final String enemies[] = {"THANOS", "JOKER"};

    public Villian(){

    }

	public Villian(int level, int attack, int defense, int hitpoints, int experience, Artifacts artifact){
        this.level = level;
        this.attack = attack;
        this.defense = defense;
        this.hitpoints = hitpoints;
        this.experience = experience;
        this.id = nextId();
        this.power = attack + defense;
        this.artifact = artifact;
    }

    private long nextId(){
        idCounter += 1;
        return idCounter;
    }

 //getters
    public int getVXcoord(){
        return this.xcoordinator;
    }

    public int getVYcoord(){
        return this.ycoordinator;
    }

    public int getTypeID(){
		return this.typeID;
    }

	public int getLevel(){
		return this.level;
	}

	public int getHitPoints(){
		return this.hitpoints;
	}

	public int getPower(){
		return this.power;
    }

    public Artifacts getArtifact(){
        return artifact;
    }

	//setters

	public void setLevel(int level){
		this.level += level;
	}

    public void setTypeID(int id){
        this.typeID = id;
    }

	public void setHitPoints(int hitpoints){
        this.hitpoints += hitpoints;
        if (this.hitpoints <= 0)
            this.hitpoints = 0;
	}

    public void SetValue(int xcoordinator, int ycoordinator){
        this.xcoordinator = xcoordinator;
        this.ycoordinator = ycoordinator;
    }


}
