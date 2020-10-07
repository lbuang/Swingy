package lbuang.Controllers;
import lbuang.Read_n_Write.ReadTXT;
import lbuang.Views.GuiView;
import lbuang.Models.CreateHero.Hero;
import lbuang.Models.Villians.Villian;


import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

public class GuiController extends JFrame {
    private static final long serialVersionUID = 42L;

    private static ArrayList<Villian> enemyArray = new ArrayList<>();
    private static ArrayList<Villian> tempArray = new ArrayList<>();
    private static int ycoordinator;
    private static int xcoordinator;
    private int[][] map;
    private int size, villians, xpos, ypos, oldx, oldy, level;
    private Hero hero;
    private Villian enemy = new Villian();
    private boolean set = false;
    private JTextArea area = new JTextArea();
    private JFrame frame;

    public GuiController(Hero hero, JFrame frame) {
        this.hero = hero;
        this.frame = frame;
    }

    public void setMapSize() {
        size = (hero.getHeroStats().getLevel() - 1) * 5 + 10 - (hero.getHeroStats().getLevel() % 2);
        xcoordinator = size;
        ycoordinator = size;
        map = new int[size][size];
    }

        public void setVillians() {
            this.villians = hero.getHeroStats().getLevel() * 8;
        //area.append(this.enemies + " enemy number\n");
    }

    public void setHeroPosition() {
        int x = 0, y = 0;

        if ((size % 2) == 1) {
            x = (int) (size / 2);
            y = (int) (size / 2);
        } else if ((size % 2) == 0) {
            x = (size / 2);
            y = (size / 2);
        }
        this.xpos = x;
        this.ypos = y;
    }
    public void hasWon() {
        if (hero.getHeroStats().getExperience() > 1000 && hero.getHeroStats().getExperience() < 2450) {
            this.level = 1;
        } else if (hero.getHeroStats().getExperience() >= 2450 && hero.getHeroStats().getExperience() < 4800) {
            this.level = 2;
        } else if (hero.getHeroStats().getExperience() >= 4800 && hero.getHeroStats().getExperience() < 8050) {
            this.level = 3;
        } else if (hero.getHeroStats().getExperience() >= 8050 && hero.getHeroStats().getExperience() < 12200) {
            this.level = 4;
        } else if (hero.getHeroStats().getExperience() == 12200) {
            this.level = 5;
        }

        if (this.level > hero.getHeroStats().getLevel()) {
            hero.getHeroStats().setLevel(this.level);
            ReadTXT.updateFile(hero);
            JOptionPane.showMessageDialog(null, "You won! You can now move to the next level.");
            enemyArray.removeAll(enemyArray);
            area.append(this.level + "\n");
        } else if (this.level == hero.getHeroStats().getLevel()) {
            area.selectAll();
            area.replaceSelection("");
            tempArray.addAll(enemyArray);
            enemyArray.removeAll(enemyArray);
        }
    }


    public void updateHeroPosition(int xpos, int ypos) {
        this.oldx = this.xpos;
        this.oldy = this.ypos;
        this.xpos += xpos;
        if (this.xpos < 0) {
            this.xpos = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            MapDisplay();
        } else if (this.xpos >= this.size) {
            this.xpos = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
           MapDisplay();
        } else {
            area.selectAll();
            area.replaceSelection("");
            MapDisplay();
        }

        this.ypos += ypos;
        if (this.ypos < 0) {
            this.ypos = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            MapDisplay();
        } else if (this.ypos >= this.size) {
            this.ypos = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            MapDisplay();
        } else {
            area.selectAll();
            area.replaceSelection("");
            MapDisplay();
        }
    }


    public JTextArea MapDisplay() {

        if (set == false) {
            setMapSize();
            setHeroPosition();
            setVillians();
            createVillians();
            set = true;
        }

        /* initialize map array to zeros */

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                map[y][x] = 0;
            }
        }

        //Villian random initialization

        for (Villian villian : enemyArray) {
            map[villian.getVYcoord()][villian.getVXcoord()] = villian.getTypeID();
        }

        //initializing hero

        map[this.ypos][this.xpos] = 4;

        //Villian collision

        for (Villian enemy : enemyArray) {
            boolean t = crossedEnemy(this.ypos, this.xpos, enemy.getVYcoord(), enemy.getVXcoord());
            if (t == true) {
                enemyArray.remove(enemy);
                set = false;
                MapDisplay();
                break;
            }

        }

        area.append("Level: " + String.valueOf(hero.getHeroStats().getLevel()) + " | \n" +
                "Attack: " + hero.getHeroStats().getAttack() + " | \n" +
                "Defence: " + hero.getHeroStats().getDefense() + " | \n" +
                "Hit points: " + String.valueOf(hero.getHeroStats().getHitPoints()) + " |\n " +
                "Experience: " + String.valueOf(hero.getHeroStats().getExperience()) + "\n\n");

        for (int y = 0; y < ycoordinator; y++) {
            for (int x = 0; x < xcoordinator; x++) {
                switch (map[y][x]) {
                    case 0:
                        area.append(" *   *");
                        break;
                    case 1:
                        area.append(" * ♛ *");
                        break;
                    case 2:
                        area.append("* ☤ *");
                        break;
                    default:
                        area.append("*✄ *");
                        break;
                }
            }
            area.append("\n");
        }

        return area;
    }

    public static void registerVillian(Villian enemy) {
        if (enemyArray.contains(enemy))
            return;
        enemyArray.add(enemy);
    }


    public void createVillians() {
        for (int i = 0; i < this.villians; i++) {
            Random random = new Random();
            int Xposition = random.nextInt(size);
            int Yposition = random.nextInt(size);
            while (Yposition == this.ypos || Xposition == this.xpos) {
                Xposition = random.nextInt(size);
                Yposition = random.nextInt(size);
            }
            enemy = Players.newVillian(hero);
            enemy.SetValue(Xposition, Yposition);
            registerVillian(enemy);
        }
    }

    public Villian getCrossedVillian() {
        for (int i = 0; i < enemyArray.size(); i++) {
            if (enemyArray.get(i).getVYcoord() == this.ypos && enemyArray.get(i).getVXcoord() == this.xpos)
                return enemyArray.get(i);
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
            } else if (hero.getHeroStats().getExperience() < 12200) {
                System.out.println("--- Game complete, You're Now A Master. ---\n\n");
                GuiView.gameOver();
            }
            hasWon();
        } else if (type == 2) {
            hasWon();
        }
    }

    public boolean crossedEnemy(int hy, int hx, int ey, int ex) {
        if ((hx == ex) && (hy == ey)) {
            enemy = getCrossedVillian();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "You've Met One On One With Your Rival, Ready To Fight?", "Retreat or Attack?", dialogButton);
            if (dialogResult == 0) {
                if (attack() == 1) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "You Lost Player\n\n ---|GAME OVER|---");
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

                }
            } else {
                Random random = new Random();
                int retreat = random.nextInt(2) + 1;

                if (retreat == 1) {
                    area.selectAll();
                    area.replaceSelection("");
                    area.append("Keep On Running\n\n");
                    this.ypos = this.oldy;
                    this.xpos = this.oldx;
                } else {
                    if (attack() == 1) {
                        enemyArray.remove(enemy);
                        upgradeXP(2);
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "You Lost Player\n\n ---|GAME OVER|---");
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                }

            }
        }
        return false;
    }


    public boolean inLuck() {
        Random random = new Random();
        int luck = random.nextInt(2) + 1;

        if (luck == 1)
            return true;
        return false;
    }

    public int attack() {
        int attack = 0, won = 0, hit = 0;
        Random random = new Random();

        if (inLuck() == true || hero.getHeroStats().getPower() > enemy.getPower()) {
            attack = 1;
        }

        if (hero.getHeroStats().getHitPoints() > 0) {
            while (hero.getHeroStats().getHitPoints() > 0 && enemy.getHitPoints() > 0) {
                if (attack == 0) {
                    hit = random.nextInt(20) + 1;
                    if (enemy.getHitPoints() > 0) {
                        hero.getHeroStats().setHitPoints(-hit);
                        ReadTXT.updateFile(hero);

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
                        if (enemy.getHitPoints() <= 0) {
                            won = 1;
                            break;
                        }
                        attack = 0;
                    }
                }
            }
        } else
            JOptionPane.showMessageDialog(null, "You Don't Have Enough Power TO Fight, Just Relax And Come Back Later Player");
        return won;
    }
}

