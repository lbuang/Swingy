package lbuang.Views;

import lbuang.Read_n_Write.ReadTXT;
import lbuang.Controllers.Controller;
import lbuang.Models.CreateHero.Hero;

import java.util.Scanner;

public class ConsoleView {
   public static void begin() {
        String player;
        int type;
        int createHero;
        int option = 0, play;
        
        Hero hero;

        try {
            createHero = HeroView.playerSetUp();

            if (createHero == 1) {
                player = HeroView.WelcomePlayer();
                type = HeroView.printHeroSelection();
                hero = StartGame.NewHero(player, type);
                play = HeroLevel.printDetails(type, player, hero);
                if (play == 1) {

                    Controller.retreat(hero);
                } else {
                    System.out.println("--- Ok Bye But Please Do Come Again\n\n");
                    System.exit(0);
                }
            }
            else if (createHero == 2) {
                ReadTXT.getDBHeroes();
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    int Countlines = ReadTXT.getLinesCount();

                    if (isDigit(line) == true) {
                        try {
                            int index = Integer.parseInt(line);
                            if (index > 0 && index <= Countlines) {
                                option = index;
                                break;
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Incorrect Input. Please Try again.");
                        }
                    }
                    else
                        System.out.println("Incorrect Input. Please Try again.");
                }
                hero = StartGame.dbHero(ReadTXT.getHero(option));
                Controller.retreat(hero);
            }
        }
        catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public static boolean isDigit(String line)
    {
        for (char c : line.toCharArray()){
            if (Character.isDigit(c) != true)
                return false;
        }
        return true;
    }

}
