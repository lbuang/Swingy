package lbuang.Models.Weapons;

public class Armor extends Artifacts {
	private int defense = 95;

	public Armor(String type){
		super(type);
	}

	public int getDefense() {
		return this.defense;
	}
}
