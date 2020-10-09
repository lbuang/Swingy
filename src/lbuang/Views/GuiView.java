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
    private String heroInformation;
    private String[] inspect = null;
    private int type;
    private static final long serialVersionUID = 42L;
    private final JFrame WelcomeFrame = new JFrame("Player Creation");
    private final JFrame PlayerFrame = new JFrame("Let The Games Begin...\n\n---Swingy---");
    private final JFrame createHeroFrame = new JFrame("Create Your SuperHero");
    private final JFrame pickHeroFrame = new JFrame("Select Your SuperHero");
    private final JFrame statsFrame = new JFrame("Hero Stats");
    private final JFrame gameFrame = new JFrame("Game In Progress Player");
    private static JFrame gameOverFrame = new JFrame("The Game Is Complete");
    private final JRadioButton spiderman = new JRadioButton("SPIDERMAN ✄");
    private final JRadioButton blackpanther = new JRadioButton("BLACKPANTHER ✄");
    private final JRadioButton batman = new JRadioButton("BATMAN ✄");
    private final String[] objects = ReadTXT.ReadLine();
    private final JList heroList = new JList<>(objects);
    private JLabel label;
    private JLabel label1;
    private JTextField playerIdentification;
    private JTextArea txtArea;
    private JButton welcomeButton;
    private JButton generatePlayer;
    private JButton pickPlayer;
    private Hero hero = new Hero();
    private GuiController map;
    private Font font = new Font("Time New Roman", Font.PLAIN, 40);


    public GuiView (){
    }

    // Second Display
    public void createHeroFrame(){
        label = new JLabel("PLAYER");
        label.setBounds(200,190, 200,30);
        label.setFont(font);
        label.setForeground(Color.green);

        label1 = new JLabel("Enter Your Name");
        label1.setBounds(210,240, 200,30);
        label1.setBackground(Color.black);
        label1.setForeground(Color.green);

        playerIdentification = new JTextField();
        playerIdentification.setBounds(200, 280, 200, 30);
        playerIdentification.setBackground(Color.gray);
        playerIdentification.setCaretColor(Color.red);

        welcomeButton = new JButton("ENTER");
        welcomeButton.setBounds(200, 320, 200, 30);
        welcomeButton.setBackground(Color.green);

        WelcomeFrame.add(label);
        WelcomeFrame.setBackground(Color.red);
        WelcomeFrame.add(label1);
        WelcomeFrame.add(playerIdentification);
        WelcomeFrame.add(welcomeButton);
        WelcomeFrame.setSize(600,600);
        WelcomeFrame.getContentPane().setBackground(Color.yellow);
        WelcomeFrame.setLocationRelativeTo(null);
        WelcomeFrame.setLayout(null);
        WelcomeFrame.setVisible(true);
        WelcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                player = playerIdentification.getText();
                player = player.trim();

                if (player.length() > 0){
                    inspect = player.split("\\s");

                    if (inspect != null)
                        player = String.join("_", inspect);

                    if (player.isEmpty())
                        JOptionPane.showMessageDialog(null, "Name cannot be empty or null!");
                    else{
                        generatePlayer();
                        WelcomeFrame.setVisible(false);
                        WelcomeFrame.dispose();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Blank Spaces Are Not Allowed Player!");

            }
        });
    }

    //First Display
    public void welcomeToGui(){
        generatePlayer = new JButton("Create Player");
        generatePlayer.setBackground(Color.red);
        generatePlayer.setBounds(210,240, 200,30);
        pickPlayer = new JButton("Select Player");
        pickPlayer.setBackground(Color.black);
        pickPlayer.setBounds(210,280, 200,30);
        PlayerFrame.add(generatePlayer);
        PlayerFrame.add(pickPlayer);
        PlayerFrame.setSize(600,600);
        PlayerFrame.setLocationRelativeTo(null);
        PlayerFrame.setLayout(null);
        PlayerFrame.setVisible(true);
        PlayerFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
        PlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        generatePlayer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                createHeroFrame();
                PlayerFrame.dispose();
            }
        });

        pickPlayer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pickPlayer();
                PlayerFrame.setVisible(false);
                PlayerFrame.dispose();
            }
        });
    }

    public void generatePlayer(){
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

        createHeroFrame.add(spiderman);
        createHeroFrame.add(blackpanther);
        createHeroFrame.add(batman);
        createHeroFrame.add(enter);
        createHeroFrame.setSize(600,600);
        createHeroFrame.getContentPane().setBackground(Color.CYAN);
        createHeroFrame.setLocationRelativeTo(null);
        createHeroFrame.setLayout(null);
        createHeroFrame.setVisible(true);
        createHeroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                createHeroFrame.setVisible(false);
                createHeroFrame.dispose();
            }
        });

    }

    public void pickPlayer(){
        JLabel label;
        JButton enter;
        JButton exit;

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
                heroInformation = heroList.getSelectedValue().toString();
                hero = StartGame.dbHero(heroInformation);

            }
        });

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (heroInformation == null)
                    JOptionPane.showMessageDialog(null, "Select Your SuperHero First Player!");
                else{
                    playGame();
                    pickHeroFrame.setVisible(false);
                    pickHeroFrame.dispose();
                }

            }
        });

        exit.addActionListener(e -> pickHeroFrame.dispose());

        pickHeroFrame.add(label);
        pickHeroFrame.add(enter);
        pickHeroFrame.add(exit);
        pickHeroFrame.add(heroList);
        pickHeroFrame.setSize(600, 600);
        pickHeroFrame.setLayout(null);
        pickHeroFrame.setVisible(true);
        pickHeroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


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
                heroInformation = hero.getHeroStats().getHeroType() + " " +
                        player + " " + hero.getHeroStats().getLevel() + " " +
                        hero.getHeroStats().getAttack() + " " + hero.getHeroStats().getAttack() +
                        " " + hero.getHeroStats().getHitPoints() + " " + hero.getHeroStats().getExperience() +
                        " " + artifact.toUpperCase();

                WriteTXT.writeToFile(heroInformation);
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

        txtArea = map.MapDisplay();

        //Up Button
        north = new JButton("Move Up");
        north.setBounds(20,540, 100, 30);

         //Move Up
         north.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(0, -1);
            }
        });

        //Down Button
        south = new JButton("Move Down");
        south.setBounds(140,540, 120, 30);

        //Move Down
        south.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(0, 1);
            }
        });

        //Left Button
        east = new JButton("Move Left");
        east.setBounds(280,540, 100, 30);

        //Move Left
        east.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(-1, 0);
            }
        });

        //Right Button
        west = new JButton("Move Right");
        west.setBounds(400,540, 100, 30);

         //Move Right
         west.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(1, 0);
            }
        });

        

        txtArea.setBounds(50,30, 600, 500);
        gameFrame.add(txtArea);
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
