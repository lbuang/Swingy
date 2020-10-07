package lbuang.Models.Villians;

import lbuang.Models.Weapons.Artifacts;

public class Thanos  extends Villian {

	public Thanos(int level, int attack, int defense, int hitpoints, int experience, Artifacts artifact){
		super(level, attack, defense, hitpoints, experience, artifact);
		int typeID = 1;
		super.setTypeID(typeID);
	}
}
