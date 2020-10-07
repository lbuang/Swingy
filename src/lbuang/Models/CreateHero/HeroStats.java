package lbuang.Models.CreateHero;

public class HeroStats {
  private String heroType;
	private int level;
	private int attack;
	private int defense;
	private int hitpoints;
	private int experience;
	private int power;

	public HeroStats(){

	}

	public HeroStats(String type, int level, int attack, int defense, int hitpoints, int experience){
		this.heroType = type;
		this.level = level;
		this.attack = attack;
		this.defense = defense;
		this.hitpoints = hitpoints;
		this.experience = experience;
		this.power = attack + defense;
	}

	//GETTERS

	public String getHeroType(){
		return this.heroType;
	}

	public int getLevel(){
		return this.level;
	}

	public int getAttack(){
		return this.attack;
	}

	public int getDefense(){
		return this.defense;
	}

	public int getHitPoints(){
		return this.hitpoints;
	}

	public int getExperience(){
		return this.experience;
	}

	public int getPower(){
		return this.power;
	}


	// SETTERS

	public void setLevel(int level){
		this.level = level;
	}

	public void setAttack(int attack){
		this.attack = attack;
	}

	public void setDefense(int defense){
		this.defense += defense;
		if (this.defense <= 0)
			this.defense = 0;
	}
	
	public void setHitPoints(int hitpoints){
		this.hitpoints += hitpoints;
		if (this.hitpoints <= 0)
			this.hitpoints = 0;
	}

	public void setExperience(int experience){
        this.experience = experience;
	}

}

