package lbuang.Controllers;

import lbuang.Read_n_Write.ReadTXT;
import lbuang.Models.Villians.Villian;
import lbuang.Models.CreateHero.Hero;
import lbuang.Models.Weapons.Armor;
import lbuang.Models.Weapons.Helm;
import lbuang.Models.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleController {
   private static ArrayList<Villian> enemyArray = new ArrayList<>();
    private static ArrayList<Villian> tempArray = new ArrayList<>();
    private static int mapYaxis;
    private static int mapXaxis;
    private static int size;
    private static int[][] map;
    private static Hero hero;
    private int villians;
    private int xcoordinator;
    private int ycoordinator;
    private int level;
    private Villian villian = new Villian();
    private boolean set = false;

    public ConsoleController(Hero hero) {
        this.hero = hero;
    }

    public static void setMapSize() {
        size = (hero.getHeroStats().getLevel() - 1) * 5 + 10 - (hero.getHeroStats().getLevel() % 2);
        mapYaxis = size;
        mapXaxis = size; 
        map = new int[size][size];
    }

    public void setVillians() {
        switch (this.villians = hero.getHeroStats().getLevel() * 8) {
        }
    }

    public void setHeroPosition() {
        ;
        int x = 0, y = 0;

        if ((size % 2) == 1) {
           y = (int) (size / 2); x = (int) (size / 2);
            
        } else if ((size % 2) == 0) {
            x = (size / 2); y = (size / 2);
        }
        this.xcoordinator = x;
        this.ycoordinator = y;
    }

    public void updateHeroPosition(int xpos, int ypos) {
        int coordiatorX = this.xcoordinator;
        int coordiatorY = this.xcoordinator;
        this.xcoordinator += xpos;
        if (this.xcoordinator < 0) {
            this.xcoordinator = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            displayMap();
        } else if (this.xcoordinator >= this.size) {
            this.xcoordinator = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            displayMap();
        } else {
            displayMap();
        }

        this.ycoordinator += ypos;
        if (this.ycoordinator < 0) {
            this.ycoordinator = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            displayMap();
        } else if (this.ycoordinator >= this.size) {
            this.ycoordinator = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            displayMap();
        } else {
            displayMap();
        }
    }

    public void displayMap() {

        if (set == false) {
            setMapSize();
            setHeroPosition();
            setVillians();

            if (tempArray.isEmpty())
                createVillian();
            else
                enemyArray.addAll(tempArray);
            set = true;
        }

        // Map Initialization

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                map[y][x] = 0;
            }
        }

        //Enemy Initialization

        for (Villian villian : enemyArray) {
            map[villian.getVYcoord()][villian.getVXcoord()] = villian.getTypeID();
        }
        // SuperHero Initialization

        map[this.ycoordinator][this.xcoordinator] = 4;

        /* check if hero  has crossed paths with enemy */

        for (Villian villian : enemyArray) {
            boolean meetEnemy = crossedEnemy(this.ycoordinator, this.xcoordinator, villian.getVYcoord(), villian.getVXcoord());
            if (meetEnemy == true)
                break;
        }

        System.out.println("Level: " + hero.getHeroStats().getLevel() + " | \n" +
                "Hitpoints: " + hero.getHeroStats().getHitPoints() + " | \n" +
                "Attack: " + hero.getHeroStats().getAttack() + " | \n" +
                "Experience: " + hero.getHeroStats().getExperience() + " | \n" +
                "Defence: " + hero.getHeroStats().getDefense() +
                "\n\n");

        for (int y = 0; y < mapYaxis; y++) {
            for (int x = 0; x < mapXaxis; x++) {
                switch (map[y][x]) {
                    case 0:
                    System.out.print("*   *");
                    break;
                case 1:
                    System.out.print("* 🃏 *");
                    break;
                case 2:
                    System.out.print("* 👿 *");
                    break;
                default:
                    System.out.print("* 🔥 *");
                    break;
                }
            }
            System.out.println();
        }
    }

    public static void registerVillian(Villian enemy) {
        if (enemyArray.contains(enemy))
            return;
        enemyArray.add(enemy);
    }

    public static void removeVillian(Villian enemy) {
        if (enemyArray.contains(enemy))
            enemyArray.remove(enemy);
    }

    public void createVillian() {
        for (int i = 0; i < this.villians; i++) {
            Random random = new Random();
            int Xposition = random.nextInt(size);
            int Yposition = random.nextInt(size);
            while (Yposition == this.ycoordinator || Xposition == this.xcoordinator) {
                Xposition = random.nextInt(size);
                Yposition = random.nextInt(size);
            }
            villian = Players.newVillian(hero);
            villian.SetValue(Xposition, Yposition);
            registerVillian(villian);
            
        }
    }

    public Villian getCrossedEnemy() {
        for (int a = 0; a < enemyArray.size(); a++) {
            if (enemyArray.get(a).getVYcoord() == this.ycoordinator && enemyArray.get(a).getVXcoord() == this.xcoordinator)
                return enemyArray.get(a);
        }
        return null;
    }

    public void upgradeXP(int type) {
        if (type == 1) {
            int xp;
            if (hero.getHeroStats().getExperience() < 2450) {
                xp = 2450;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 4800) {
                xp = 4800;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 8050) {
                xp = 8050;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 12200) {
                xp = 12200;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 12201) {
                System.out.println("--- Keep It Going! It's Advanturous ---");
                System.exit(0);
            }

            hasWon();
        } else if (type == 2) {
            hero.getHeroStats().setExperience(hero.getHeroStats().getExperience() + villian.getPower());
            ReadTXT.updateFile(hero);
            hasWon();
        }
    }

    public void hasWon() {
        if (hero.getHeroStats().getExperience() > 1000 && hero.getHeroStats().getExperience() < 2450) {
            this.level = 1;
        }
        else if (hero.getHeroStats().getExperience() >= 2450 && hero.getHeroStats().getExperience() < 4800) {
            this.level = 2;
        }
        else if (hero.getHeroStats().getExperience() >= 4800 && hero.getHeroStats().getExperience() < 8050) {
            this.level = 3;
        }
        else if (hero.getHeroStats().getExperience() >= 8050 && hero.getHeroStats().getExperience() < 12200) {
            this.level = 4;
        } else if (hero.getHeroStats().getExperience() == 12200) {
            this.level = 5;
        }

        if (this.level > hero.getHeroStats().getLevel()) {
            hero.getHeroStats().setLevel(this.level);
            ReadTXT.updateFile(hero);

            System.out.println("You Have Deafeted Your Rival\n\n" +
                    "1. Continue Playing\n" +
                    "   Or\n" +
                    "2. Quit\n");

            Scanner Myscanner = new Scanner(System.in);

            while (Myscanner.hasNextLine()) {
                String line = Myscanner.nextLine();

                if (line.matches("\\s*[1-2]\\s*")) {
                    int option = Integer.parseInt(line);

                    if (option == 1) {
                        enemyArray.removeAll(enemyArray);
                        Controller.retreat(hero);
                    } else if (option == 2) {
                        System.out.println("---     See You Next Time on Swingy     ---\n\n");
                        System.exit(0);
                    }
                } else
                    System.out.println("Invalid input. Please Enter valid Input");
            }
        } else if (this.level == hero.getHeroStats().getLevel()) {
            enemyArray.removeAll(enemyArray);
        }
    }

    public boolean crossedEnemy(int YaxisHero, int XaxisHero, int YaxisVillan, int XaxisVillan) {
        if ((XaxisHero == XaxisVillan) && (YaxisHero == YaxisVillan)) {
            System.out.println("You Have Met Your Rival\n\n" +
                    "What Do You Want To Do:\n" +
                    "1. Retreat 🏃 \n" +
                    "2. Attack ⚔️\n");

            Scanner Myscanner1 = new Scanner(System.in);
            while (Myscanner1.hasNextLine()) {
                String line = Myscanner1.nextLine();

                if (line.matches("\\s*[1-2]\\s*")) {
                    int userInput = Integer.parseInt(line);

                    if (userInput == 1) {
                        Random random = new Random();
                        int retreat = random.nextInt(2) + 1;
                        

                        if (retreat == 1) {
                            System.out.println("You Lost 5XP For Running Away\n");
                            System.out.println("Your Current XP: " + (hero.getHeroStats().getExperience() - 5));
                            displayMap();
                        }
                    } else if (userInput == 2) {
                        Villian crossed = getCrossedEnemy();
                        int victory = Controller.attack(hero, crossed);
                        if (victory == 1) {
                            won(crossed);
                            removeVillian(crossed);
                            return true;

                        } else {

                            lost();
                            break;
                        }
                    } else
                        System.out.println("Invalid User Input. Please Enter Valid Input");
                } else
                    System.out.println("Invalid User Input. Please Enter Valid Input");
            }
        }
        return false;
    }

    public void won(Villian crossed) {
        enemyArray.remove(crossed);
        upgradeXP(2);
        if (Controller.inLuck() == true) {
            System.out.println("Rival Killed & He Dropped An Artifact.\n You Can Pick It Up(" + crossed.getArtifact().getType() + ")\n" +
                    "1. Pick It Up\n" +
                    "2. You Have Gained Some Experience Player, Continue...");

            Scanner Myscanner2 = new Scanner(System.in);
            while (Myscanner2.hasNextLine()) {
                String line = Myscanner2.nextLine();

                if (line.matches("\\s*[1-2]\\s*")) {
                    int option = Integer.parseInt(line);
                    if (option == 1) {
                        String type = villian.getArtifact().getType();

                        if (type.equals("Weapon")) {
                            Weapon weapon = new Weapon("Weapon");
                            hero.setArtifact(weapon);
                            hero.getHeroStats().setAttack(65);
                            ReadTXT.updateFile(hero);
                            Controller.retreat(hero);

                        } else if (type.equals("Armor")) {
                            Armor armor = new Armor("Armor");
                            hero.setArtifact(armor);
                            hero.getHeroStats().setDefense(55);
                            ReadTXT.updateFile(hero);
                             Controller.retreat(hero);
                             System.out.println("armor");
                        }
                        else if (type.equals("Helm")) {
                            Helm helm = new Helm("Helm");
                            hero.setArtifact(helm);
                            hero.getHeroStats().setHitPoints(75);
                            ReadTXT.updateFile(hero);
                             Controller.retreat(hero);
                             System.out.println("helm");
                        }
                    } else if (option == 2) {
                        upgradeXP(2);
                    }
                } else
                    System.out.println("Invalid Usert Input. Please Try Again");
            }
        } else {
            upgradeXP(2);
            System.out.println("you Have Won and For That You Have Gained Some Experience Points.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.exit(0);
            }
            Controller.retreat(hero);
        }
    }

    public void lost() {
        System.out.println("\n\n You Have Lost Loser \n\n ----- GAME OVER -----");
        System.exit(0);
    }
}
