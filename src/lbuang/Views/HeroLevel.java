package lbuang.Views;

import lbuang.Models.CreateHero.Hero;


import java.util.Scanner;

public class HeroLevel extends HeroView {

    public static int printDetails(long userInput, String player, Hero hero) {
        System.out.println("Welcome To --- SWINGY ---\n\n" + player + ", Here Is Your Statistics: ");
        if (userInput == 1) {
            System.out.println("You Are A True Warrior \n" +
                    "Your Level is: " + hero.getHeroStats().getLevel() + "\n" +
                    "Your Attack: " + hero.getHeroStats().getAttack() + "\n" +
                    "Your Defense: " + hero.getHeroStats().getDefense() + "\n" +
                    "Your Experience: " + hero.getHeroStats().getExperience() + "\n" +
                    "And Hit Points: " + hero.getHeroStats().getHitPoints() + "\n\n");
                    gameOption();
        }
        else if (userInput == 2) {
            System.out.println("You Are A True Warrior \n" +
                    "Your Level is: " + hero.getHeroStats().getLevel() + "\n" +
                    "Your Attack: " + hero.getHeroStats().getAttack() + "\n" +
                    "Your Defense: " + hero.getHeroStats().getDefense() + "\n" +
                    "Your Experience: " + hero.getHeroStats().getExperience() + "\n" +
                    "And Hit Points: " + hero.getHeroStats().getHitPoints() + "\n\n");
            gameOption();
        }

        else if (userInput == 3) {
            System.out.println("You Are A True Warrior\n" +
                    "Your Level is: " + hero.getHeroStats().getLevel() + "\n" +
                    "Your Attack: " + hero.getHeroStats().getAttack() + "\n" +
                    "Your Defense: " + hero.getHeroStats().getDefense() + "\n" +
                    "Your Experience: " + hero.getHeroStats().getExperience() + "\n" +
                    "And Hit Points: " + hero.getHeroStats().getHitPoints() + "\n\n");
            gameOption();
        }

        int possibility;
        possibility = 0;
        Scanner Myscanner = new Scanner(System.in);
        while (Myscanner.hasNextLine()) {
            String line = Myscanner.nextLine();

            if (line.matches("\\s*[1-2]\\s*")) {
                possibility = Integer.parseInt(line);
                break;
            } else
                System.out.println("Invalid input. please enter any of the options given below.");
        }
        return possibility;
    }
    public static void gameOption()
    {
        System.out.println( "What Would You Like To Do??\n" +
                "1. Start 🏁\n" +
                " Or \n" +
                "2. Quit game 😩\n");
    }

}
