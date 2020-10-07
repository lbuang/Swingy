package lbuang.Controllers;


import lbuang.Models.CreateHero.Batman;
import lbuang.Models.CreateHero.BlackPanther;
import lbuang.Models.CreateHero.Hero;
import lbuang.Models.CreateHero.HeroStats;
import lbuang.Models.CreateHero.Spiderman;
import lbuang.Models.Villians.Joker;
//import lbuang.Models.Villians.Thanos;
import lbuang.Models.Weapons.Armor;
import lbuang.Models.Weapons.Artifacts;
import lbuang.Models.Weapons.Helm;
import lbuang.Models.Weapons.Weapon;

import java.util.Random;

public class Players {
   public static Hero newPlayer(String hero, String player, HeroStats stats, Artifacts artifact){

            if (hero.equals("Spiderman")){
                return new Spiderman(player, stats, artifact);
            }
            else if (hero.equals("Batman")){
                return new Batman(player, stats, artifact);
            }
            else if (hero.equals("BlackPanther")){
                return new BlackPanther(player, stats, artifact);
            }
            else
                return null;
        }

        public static Joker newVillian(Hero hero){
            Random random = new Random();
            int villian = random.nextInt(2) + 1;
            String artifact = Artifacts.randomiseArtifact();
            int level = 0, attack = 0, defense = 0, hitpoints = 0, experience = 0;

            if (villian == 1){
                if (artifact.equals("WEAPON")){
                    Weapon weapon = new Weapon("Weapon");
                    level = hero.getHeroStats().getLevel();
                    attack = 100 + weapon.getAttack();
                    defense = 100;
                    hitpoints = 100;
                    experience = 0;
                    return new Joker(level, attack, defense, hitpoints, experience, weapon);
                }
                else if (artifact.equals("ARMOR")){
                    Armor armor = new Armor("Armor");
                    level = hero.getHeroStats().getLevel();
                    attack = 100;
                    defense = 100 + armor.getDefense();
                    hitpoints = 100;
                    experience = 0;
                    return new Joker(level, attack, defense, hitpoints, experience, armor);
                }
                else if (artifact.equals("HELM")){
                    Helm helm = new Helm("Helm");
                    level = hero.getHeroStats().getLevel();
                    attack = 100 + helm.getHitPoints();
                    defense = 100;
                    hitpoints = 100;
                    experience = 0;
                    return new Joker(level, attack, defense, hitpoints, experience, helm);
                }
            }
            else if (villian == 2){
                if (artifact.equals("WEAPON")){
                    Weapon weapon = new Weapon("Weapon");
                    level = hero.getHeroStats().getLevel();
                    attack = 100 + weapon.getAttack();
                    defense = 100;
                    hitpoints = 100;
                    experience = 0;
                    return new Joker(level, attack, defense, hitpoints, experience, weapon);
                }
                else if (artifact.equals("ARMOR")){
                    Armor armor = new Armor("Armor");
                    level = hero.getHeroStats().getLevel();
                    attack = 100;
                    defense = 100 + armor.getDefense();
                    hitpoints = 100;
                    experience = 0;
                    return new Joker(level, attack, defense, hitpoints, experience, armor);
                }
                else if (artifact.equals("HELM")){
                    Helm helm = new Helm("Helm");
                    level = hero.getHeroStats().getLevel();
                    attack = 100 + helm.getHitPoints();
                    defense = 100;
                    hitpoints = 100;
                    experience = 0;
                    return new Joker(level, attack, defense, hitpoints, experience, helm);
                }
            }
            return null;
        }
        

        
}
