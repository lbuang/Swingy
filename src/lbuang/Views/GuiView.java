package lbuang.Views;

import lbuang.Models.CreateHero.*;
import lbuang.Read_n_Write.ReadTXT;
import lbuang.Read_n_Write.WriteTXT;
import lbuang.Controllers.GuiController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiView extends JFrame {
    private String player;
    private String artifact;
    private String heroData;
    private String[] check = null;
    private int type;
    private static final long serialVersionUID = 42L;
    private final JFrame welcomeFrame = new JFrame("Player Creation");
    private final JFrame PlayerFrame = new JFrame("Let The Games Begin...\n\n---Swingy---");
    private final JFrame createFrame = new JFrame("Create Your SuperHero");
    private final JFrame selectFrame = new JFrame("Select Your SuperHero");
    private final JFrame statsFrame = new JFrame("Hero Stats");
    private final JFrame gameFrame = new JFrame("Game In Progress Player");
    private static JFrame gameOverFrame = new JFrame("The Game Is Complete");
    private final JRadioButton spiderman = new JRadioButton("SPIDERMAN ✄");
    private final JRadioButton blackpanther = new JRadioButton("BLACKPANTHER ✄");
    private final JRadioButton batman = new JRadioButton("BATMAN ✄");
    private final String[] items = ReadTXT.ReadLine();
    private final JList heroList = new JList<>(items);
    private JLabel label;
    private JLabel label1;
    private JTextField playerName;
    private JTextArea area;
    private JButton welcomeButton, createPlayer, selectPlayer;
    private Hero hero = new Hero();
    private GuiController map;
    private Font font = new Font("Time New Roman", Font.PLAIN, 40);


    public GuiView (){
    }

    // Second Display
    public void createFrame(){
        label = new JLabel("PLAYER");
        label.setBounds(200,190, 200,30);
        label.setFont(font);
        label.setForeground(Color.green);

        label1 = new JLabel("Enter Your Name");
        label1.setBounds(210,240, 200,30);
        label1.setBackground(Color.black);
        label1.setForeground(Color.green);

        playerName = new JTextField();
        playerName.setBounds(200, 280, 200, 30);
        playerName.setBackground(Color.gray);
        playerName.setCaretColor(Color.red);

        welcomeButton = new JButton("ENTER");
        welcomeButton.setBounds(200, 320, 200, 30);
        welcomeButton.setBackground(Color.green);

        welcomeFrame.add(label);
        welcomeFrame.setBackground(Color.red);
        welcomeFrame.add(label1);
        welcomeFrame.add(playerName);
        welcomeFrame.add(welcomeButton);
        welcomeFrame.setSize(600,600);
        welcomeFrame.getContentPane().setBackground(Color.yellow);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setLayout(null);
        welcomeFrame.setVisible(true);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                player = playerName.getText();
                player = player.trim();

                if (player.length() > 0){
                    check = player.split("\\s");

                    if (check != null)
                        player = String.join("_", check);

                    if (player.isEmpty())
                        JOptionPane.showMessageDialog(null, "Name cannot be empty or null!");
                    else{
                        createPlayer();
                        welcomeFrame.setVisible(false);
                        welcomeFrame.dispose();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Blank Spaces Are Not Allowed Player!");

            }
        });
    }

    //First Display
    public void welcomeToGui(){
        createPlayer = new JButton("Create Player");
        createPlayer.setBackground(Color.red);
        createPlayer.setBounds(210,240, 200,30);
        selectPlayer = new JButton("Select Player");
        selectPlayer.setBackground(Color.black);
        //selectPlayer.setBackground(Color.LIGHT_GRAY);
        selectPlayer.setBounds(210,280, 200,30);
        PlayerFrame.add(createPlayer);
        PlayerFrame.add(selectPlayer);
        PlayerFrame.setSize(600,600);
        PlayerFrame.setLocationRelativeTo(null);
        PlayerFrame.setLayout(null);
        PlayerFrame.setVisible(true);
        PlayerFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
        PlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPlayer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                createFrame();
                PlayerFrame.dispose();
            }
        });

        selectPlayer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPlayer();
                PlayerFrame.setVisible(false);
                PlayerFrame.dispose();
            }
        });
    }

    public void createPlayer(){
        ButtonGroup group = new ButtonGroup();
        JButton enter;

        enter = new JButton("CONTINUE");

        spiderman.setBounds(210,220, 200,30);
        blackpanther.setBounds(210, 240, 200, 30);
        batman.setBounds(210,260, 200,30);
        enter.setBounds(210,300, 200,30);

        group.add(spiderman);
        group.add(blackpanther);
        group.add(batman);

        createFrame.add(spiderman);
        createFrame.add(blackpanther);
        createFrame.add(batman);
        createFrame.add(enter);
        createFrame.setSize(600,600);
        createFrame.getContentPane().setBackground(Color.CYAN);
        createFrame.setLocationRelativeTo(null);
        createFrame.setLayout(null);
        createFrame.setVisible(true);
        createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (blackpanther.isSelected())
                    type = 3;
                if (spiderman.isSelected())
                    type = 2;
                else if (batman.isSelected())
                    type = 1;
                heroStats();
                createFrame.setVisible(false);
                createFrame.dispose();
            }
        });

    }

    public void selectPlayer(){
        JLabel label;
        JButton enter, exit;

        label= new JLabel("Select Existing SuperHero");
        label.setBounds(20, 20, 200, 30);

        heroList.setBounds(20, 50, 350, 520);
        enter = new JButton("Continue");
        enter.setBounds(400, 50, 100, 30);
        exit = new JButton("Quit Game");
        exit.setBounds(400, 105, 100, 30);

        heroList.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                heroData = heroList.getSelectedValue().toString();
                hero = StartGame.dbHero(heroData);

            }
        });

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (heroData == null)
                    JOptionPane.showMessageDialog(null, "Select Your SuperHero First Player!");
                else{
                    playGame();
                    selectFrame.setVisible(false);
                    selectFrame.dispose();
                }

            }
        });

        exit.addActionListener(e -> selectFrame.dispose());

        selectFrame.add(label);
        selectFrame.add(enter);
        selectFrame.add(exit);
        selectFrame.add(heroList);
        selectFrame.setSize(600, 600);
        selectFrame.setLocationRelativeTo(null);
        selectFrame.setLayout(null);
        selectFrame.setVisible(true);
        selectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void heroStats(){
        hero = StartGame.NewHero(player, type);
        JLabel lbl1;
        JLabel lbl2;
        JLabel lbl3;
        JLabel lbl4;
        JLabel lbl5;
        JLabel lbl6;
        JLabel lbl7;
        JLabel lbl8;
        JButton enter;

        
        lbl1 = new JLabel("Hero: " + player);
        lbl1.setBounds(200,220, 200,30);
        String heroType;
        lbl2 = new JLabel("Hero: " + (heroType = hero.getHeroStats().getHeroType()));
        lbl2.setBounds(200,240, 200,30);
        int level;
        lbl3 = new JLabel("Level: " +  (level = hero.getHeroStats().getLevel()));
        lbl3.setBounds(200,260, 200,30);
        int attack;
        lbl4 = new JLabel("Attack: " + (attack = hero.getHeroStats().getAttack()));
        lbl4.setBounds(200,280, 200,30);
        int defense;
        lbl5 = new JLabel("Defense: " + (defense = hero.getHeroStats().getDefense()));
        lbl5.setBounds(200,300, 200,30);
        int hitpoints;
        lbl6 = new JLabel("Hitpoints: " + (hitpoints = hero.getHeroStats().getHitPoints()));
        lbl6.setBounds(200,320, 200,30);
        lbl7 = new JLabel("Artifact: " + (artifact = hero.getArtifact().getType()));
        lbl7.setBounds(200,340, 200,30);
        lbl8 = new JLabel("YOUR HERO STATS");
        lbl8.setBounds(200,200, 200,30);
        enter = new JButton("Enter");
        enter.setBounds(200,370, 200,30);

        statsFrame.add(lbl1);
        statsFrame.add(lbl2);
        statsFrame.add(lbl3);
        statsFrame.add(lbl4);
        statsFrame.add(lbl5);
        statsFrame.add(lbl6);
        statsFrame.add(lbl7);
        statsFrame.add(lbl8);
        statsFrame.add(enter);
        statsFrame.setSize(600, 600);
        statsFrame.setLocationRelativeTo(null);
        statsFrame.setLayout(null);
        statsFrame.setVisible(true);
        statsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                heroData = hero.getHeroStats().getHeroType() + " " +
                        player + " " + hero.getHeroStats().getLevel() + " " +
                        hero.getHeroStats().getAttack() + " " + hero.getHeroStats().getAttack() +
                        " " + hero.getHeroStats().getHitPoints() + " " + hero.getHeroStats().getExperience() +
                        " " + artifact.toUpperCase();

                WriteTXT.writeToFile(heroData);
                WriteTXT.closeFile();
                playGame();
                statsFrame.setVisible(false);
                statsFrame.dispose();
            }
        });
    }

    public void playGame(){
        JButton north, south, east, west;

        map = new GuiController(hero, gameFrame);

        area = map.MapDisplay();


        //Down Button
        south = new JButton("Move Down");
        south.setBounds(140,540, 120, 30);

        //Right Button
        west = new JButton("Move Right");
        west.setBounds(400,540, 100, 30);

        //Up Button
        north = new JButton("Move Up");
        north.setBounds(20,540, 100, 30);

        //Left Button
        east = new JButton("Move Left");
        east.setBounds(280,540, 100, 30);

        //Move Up
        north.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(0, -1);
            }
        });

        //Move Down
        south.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(0, 1);
            }
        });

        //Move Left
        east.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(-1, 0);
            }
        });

        //Move Right
        west.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(1, 0);
            }
        });

        area.setBounds(50,30, 600, 500);
        gameFrame.add(area);
        gameFrame.add(north);
        gameFrame.add(south);
        gameFrame.add(east);
        gameFrame.add(west);
        gameFrame.setSize(850, 650);
        gameFrame.getContentPane().setBackground(Color.gray);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void gameOver(){
        JLabel label;
        JButton close;

        label = new JLabel("--- It's Adventure Time ---");
        label.setBounds(200,240, 400,30);
        close = new JButton("- COMPLETE GAME -");
        close.setBounds(210,280, 200,30);

        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameOverFrame.dispose();
                System.exit(0);
            }
        });

        gameOverFrame.add(label);
        gameOverFrame.add(close);
        gameOverFrame.setSize(600, 600);
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setLayout(null);
        gameOverFrame.setVisible(true);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
