package lbuang.Views;

import java.util.Scanner;

public class HeroView {

    public static String WelcomePlayer() {
        System.out.println("Enter Your Name Then Press Enter\n");
        String player = null;
        Scanner Myscanner = new Scanner(System.in);
        while (Myscanner.hasNextLine()) {
            player = Myscanner.nextLine();
            player = player.trim();

            if (player.length() > 0) {
                String[] check = player.split("\\s");
                if (check != null)
                    player = String.join("_", check);
                break;
            } else
                System.out.println("Player name cannot be null, please enter your new hero");
        }
        return player;
    }

    public static int playerSetUp() {
        System.out.println("---LET THE GAMES BEGIN---\n\n" + "  " +
        "       --SWINGY--\n\n" +
        "1. Create new SuperHero\n\n" +
        "2. Select existing SuperHero\n");

        int possibility = 0;
        Scanner Myscanner = new Scanner(System.in);
        while (Myscanner.hasNextLine()) {
            String line = Myscanner.nextLine();

            if (line.matches("\\s*[1-2]\\s*")) {
                possibility = Integer.parseInt(line);
                break;
            } else
                System.out.println("Invalid Input. Plese Try Again Player.");
        }
        return possibility;
    }

    // Choosing of Heros
    public static int printHeroSelection() {
        System.out.println("Choose your class \n" +
                "1. Batman 🦇\n" +
                "2. Spiderman 🕷️\n" +
                "3. BlackPanther 🐆\n");

        int possibility = 0;
        Scanner Myscanner = new Scanner(System.in);
        while (Myscanner.hasNextLine()) {
            String line = Myscanner.nextLine();

            if (line.matches("\\s*[1-3]\\s*")) {
                possibility = Integer.parseInt(line);
                break;
            } else
                System.out.println("Invalid input. Please choose either one of the options below");
        }
        return possibility;
    }

    public static void printDirections() {
        System.out.println("\n\n--- SELECT DIRECTION ---\n\n" 
        + "   " +
        "---THIS IS YOU---\n" +
        "     " +
         "------ 🔥  ------\n\n" +
                "1. Move Up ⬆️\n" +
                "2. Move Down ⬇️\n" +
                "3. Move Left ⬅️\n" +
                "4. Move Right ➡️\n" +
                "5. Quit 😩!!\n");
    }

}
