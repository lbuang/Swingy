package lbuang.Read_n_Write;

import lbuang.Models.CreateHero.Hero;

import java.io.*;

public class ReadTXT {
   public static int getLinesCount() {
        try {
            File heroFile = new File("Heroes.txt");
            FileReader fileReader = new FileReader(heroFile);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            lineNumberReader.skip(Long.MAX_VALUE);
            int method = lineNumberReader.getLineNumber();
            lineNumberReader.close();
            return method;
        } catch (IOException ioe) {
            ioe.getMessage();
        }
        return -1;
    }

    public static String[] ReadLine() {
        try {
            File heroFile = new File("Heroes.txt");
            FileReader fileReader = new FileReader(heroFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String rail = null;
            String objects[] = new String[getLinesCount()];
            int indicator = 0;

            while ((rail = bufferedReader.readLine()) != null) {
                objects[indicator] = rail;
                indicator++;
            }
            bufferedReader.close();
            return objects;
        } catch (IOException ioe) {
            ioe.getMessage();
        }
        return null;
    }

    public static void getDBHeroes() {
        String objects[];
        int indicator;
        indicator = 0;
        int method;
        method = 1;
        objects = ReadLine();

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("--- Choose Existing SuperHeros ---\n\n");

        while (indicator < getLinesCount()) {
            System.out.println(method++ + ". " + objects[indicator++]);
        }
    }

    public static String getHero(long hero) {
        String objects[] = new String[getLinesCount()];
        objects = ReadLine();
        return objects[(int) hero - 1];
    }

    public static void updateFile(Hero hero) {
        try {
            String[] objects = ReadLine();
            String deleteLine = null;
            String newLine = null;
            File heroFile = new File("Heroes.txt");
            FileWriter fileWriter = new FileWriter(heroFile);

            for (String line : objects) {
                if (line.contains(hero.getnewHero()) && line.contains(hero.getHeroStats().getHeroType()))
                    deleteLine = line;
            }

            newLine = (hero.getHeroStats().getHeroType() + " " + hero.getnewHero() + " " +
                    Integer.toString(hero.getHeroStats().getLevel()) + " " +
                    Integer.toString(hero.getHeroStats().getAttack()) + " " +
                    Integer.toString(hero.getHeroStats().getDefense()) + " " +
                    Integer.toString(hero.getHeroStats().getHitPoints()) + " " +
                    Integer.toString(hero.getHeroStats().getExperience()) + " " +
                    hero.getArtifact().getType().toUpperCase());

            if (newLine != null) {
                for (String line : objects) {
                    if (line.equals(deleteLine))
                        fileWriter.write(newLine + "\n");
                    else
                        fileWriter.write(line + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException ioe) {
            System.out.println("Ebcountered An Error Updating Your SuperHero Statistics: " + ioe);
        }

    }
}
