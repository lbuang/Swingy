package lbuang.Views;

import lbuang.Models.CreateHero.BlackPanther;
import lbuang.Models.CreateHero.Hero;
import lbuang.Read_n_Write.WriteTXT;
import lbuang.Controllers.Players;
import lbuang.Models.CreateHero.HeroStats;
import lbuang.Models.Weapons.Armor;
import lbuang.Models.Weapons.Artifacts;
import lbuang.Models.Weapons.Helm;
import lbuang.Models.Weapons.Weapon;

public class StartGame {
    private static int level;
    private static int attack;
    private static int defense;
    private static int hitpoints;
    private static int experience;
    private static String artifact;
    private static String stats;
    private static Hero newHero = new Hero();
    private static Hero dbHero = new Hero();

    public static Hero addHero(String type, String player){
        artifact = Artifacts.randomiseArtifact();

        if (artifact.equals("WEAPON")){
            Weapon weapon = new Weapon("Weapon");
            level = 1;
            attack = 100 + weapon.getAttack();
            defense = 100;
            hitpoints = 100;
            experience = 1000;
            HeroStats heroStats = new HeroStats(type, level, attack, defense, hitpoints, experience);
            newHero = Players.newPlayer(type, player, heroStats, weapon);
            stats = type + " " + player + " " + level + " " + attack + " " + defense + " " + hitpoints + " " + experience + " " + artifact;
        }
        else if (artifact.equals("ARMOR")){
            Armor armor = new Armor("Armor");
            level = 1;
            attack = 100;
            defense = 100 + armor.getDefense();
            hitpoints = 100;
            experience = 1000;
            HeroStats heroStats = new HeroStats(type, level, attack, defense, hitpoints, experience);
            newHero = Players.newPlayer(type, player, heroStats, armor);
            stats = type + " " + player + " " + level + " " + attack + " " + defense + " " + hitpoints + " " + experience + " " + artifact;
        }
        else if (artifact.equals("HELM")){
            Helm helm = new Helm("Helm");
            level = 1;
            attack = 100 + helm.getHitPoints();
            defense = 100;
            hitpoints = 100;
            experience = 1000;
            HeroStats heroStats = new HeroStats(type, level, attack, defense, hitpoints, experience);
            newHero = Players.newPlayer(type, player, heroStats, helm);
            stats = type + " " + player + " " + level + " " + attack + " " + defense + " " + hitpoints + " " + experience + " " + artifact;
        }

        WriteTXT.writeToFile(stats);
        return newHero;
    }

    public static Hero NewHero(String player, long type){
        if (type == 1){
            return addHero("Batman", player);
        }
        else if (type == 2){
            return addHero("Spiderman", player);
        }
        else if (type == 3){
            return addHero("BlackPanther", player);
        }
        else
            return null;
    }

    public static Hero dbHero(String hero){
        String[] items = hero.split(" ");
        int i = 0;

        String type = items[0];
        String player = items[1];
        int level = Integer.parseInt(items[2]);
        int attack = Integer.parseInt(items[3]);
        int defense = Integer.parseInt(items[4]);
        int hitpoints = Integer.parseInt(items[5]);
        int experience = Integer.parseInt(items[6]);
        String artifact = items[7];
        HeroStats heroStats = new HeroStats(type, level, attack, defense, hitpoints, experience);

        if (artifact.equals("WEAPON")){
            Weapon weapon = new Weapon("Weapon");
            dbHero = Players.newPlayer(type, player, heroStats, weapon);
        }
        else if (artifact.equals("ARMOR")){
            Armor armor = new Armor("Armor");
            dbHero = Players.newPlayer(type, player, heroStats, armor);
        }
        else if (artifact.equals("HELM")){
            Helm helm = new Helm("Helm");
            dbHero = Players.newPlayer(type, player, heroStats, helm);
        }
        return dbHero;
    }
}
