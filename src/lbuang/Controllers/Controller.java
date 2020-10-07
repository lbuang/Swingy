package lbuang.Controllers;


import lbuang.Models.Villians.Villian;
import lbuang.Read_n_Write.ReadTXT;
import java.util.Random;
import lbuang.Views.HeroView;
import java.io.Console;
import lbuang.Models.CreateHero.Hero;

public class Controller {
   public Controller() {
    }

    public static void retreat(Hero hero) {
        ConsoleController DisplayMap = new ConsoleController(hero);

        DisplayMap.displayMap();
        HeroView.printDirections();
        Console console = System.console();
        while (true) {
            String line = console.readLine();


            if (line.matches("\\s*[1-5]\\s*")) {
                int route = Integer.parseInt(line);

                if (route == 1) {
                    DisplayMap.updateHeroPosition(0, -1);
                    DisplayMap.displayMap();
                    HeroView.printDirections();
                    
                } else if (route == 2) {
                    DisplayMap.updateHeroPosition(0, 1);
                    DisplayMap.displayMap();
                    HeroView.printDirections();
                    
                } else if (route == 3) {
                    DisplayMap.updateHeroPosition(-1, 0);
                    DisplayMap.displayMap();
                    HeroView.printDirections();
                    
                }
                else if (route == 4) {
                    DisplayMap.updateHeroPosition(1, 0);
                    DisplayMap.displayMap();
                    HeroView.printDirections();
                    
                }
                    else if(route == 5)
                    {
                        System.exit(0);
                    }
            }
            else {
                System.out.println("Invalid User Input");

            }
        }
    }

    public static boolean inLuck() {
        Random random = new Random();
        int luck = random.nextInt(2) + 1;

        if (luck == 1)
            return true;
        return false;
    }

    public static int attack(Hero hero, Villian enemy) {
        int attack = 0, won = 0, hit = 0;
        Random random = new Random();

        if (inLuck() == true || hero.getHeroStats().getPower() > enemy.getPower()) {
            attack = 1;
        }

        if (hero.getHeroStats().getHitPoints() > 0) {
            while (hero.getHeroStats().getHitPoints() > 0 && enemy.getHitPoints() > 0) {
                System.out.println("Hero hp: " + hero.getHeroStats().getHitPoints());
                System.out.println("Rival hp: " + enemy.getHitPoints());
                if (attack == 0) {
                    hit = random.nextInt(30) + 1;
                    if (enemy.getHitPoints() > 0) {
                        hero.getHeroStats().setHitPoints(-hit);
                        ReadTXT.updateFile(hero);
                        System.out.println("You Fought Your Rival And You Lost " + hit + " HitPoints.");

                        if (hero.getHeroStats().getHitPoints() <= 0) {
                            won = 0;
                            break;
                        }
                        attack = 1;
                    }
                } else if (attack == 1) {
                    hit = random.nextInt(50) + 1;
                    if (hero.getHeroStats().getHitPoints() > 0) {
                        enemy.setHitPoints(-hit);
                        System.out.println("You Fought Your Rival And Gave " + hit + " Damage.");
                        if (enemy.getHitPoints() <= 0) {
                            won = 1;
                            break;
                        }
                        attack = 0;
                    }
                }
            }
        }
        else
            System.out.println("Too Weak Go Regain Your Power Player!\n\n" +
                    "Your HP Is " + hero.getHeroStats().getHitPoints());
        return won;
    }
}
