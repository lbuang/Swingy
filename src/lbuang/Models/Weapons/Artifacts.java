package lbuang.Models.Weapons;

import java.util.Random;

public class Artifacts {
  private String type;
    private static final String artifacts[] = {"WEAPON", "ARMOR", "HELM"};

    public static String randomiseArtifact()
    {
		Random random = new Random();
		return (artifacts[random.nextInt(3)]);
	}

	Artifacts(String type){
		this.type = type;
	}

	public static String[] getArtifacts() {
		return artifacts;
	}

	public String getType(){
		return this.type;
	}
}
