package lbuang.Models.Villians;

import lbuang.Models.Weapons.Artifacts;

public class Joker  extends Villian {

	public Joker(int level, int attack, int defense, int hitpoints, int experience, Artifacts artifact){
		super(level, attack, defense, hitpoints, experience, artifact);
		int typeID = 2;
		super.setTypeID(typeID);
	}
}
