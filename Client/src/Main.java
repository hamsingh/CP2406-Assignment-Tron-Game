import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main implements Runnable{

    String userName;
    final JTextField name = new JTextField("Please Enter Your Name");
    Map multiPlayer;
    LocalMultiPlayer threePlayer;

    public void run() {
        // Main Frame
        final JFrame frame = new JFrame("Light Cycles");
        frame.setBackground(Color.BLACK);
        frame.setMinimumSize(new Dimension(800, 800));
        //Dimension dMAX = frame.getMaximumSize();
        frame.setPreferredSize(new Dimension(800, 850));

        ////////////////
        // MAIN PANEL //
        ////////////////
        //main_bg = ImageIO.read("main_bg.img") // Add image for background
        final JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new BorderLayout());
        mainMenu.setBackground(Color.BLACK);

        // Main Menu BG Image
        /*
        final JComponent BG = new JComponent(){
            public void paintComponent(Graphics gc){
                super.paintComponent(gc);
                gc.drawImage(gc, "main_bg.jpg");
            }
        };
        */

        // Panel for Main Menu Buttons
        final JPanel menuButtons = new JPanel();
        menuButtons.setLayout(new GridLayout(3, 1));
        menuButtons.setBackground(Color.BLACK);

        // Add Main Menu Buttons WORK OUT HOW TO MAKE BUTTONS A CERTAIN SIZE
        final JButton playButton = new JButton("PLAY");
        playButton.setBounds(50,20,600,600);
        //play.setSize(new Dimension (50,20));
        //play.setLocation(600,600);
        menuButtons.add(playButton);
        final JButton instructionsButton = new JButton("INSTRUCTIONS");
        instructionsButton.setBounds(50,20,600,600);
        //instructions.setSize(new Dimension (50,20));
        //instructions.setLocation(600,500);
        menuButtons.add(instructionsButton);
        final JButton quitButton = new JButton("QUIT");
        quitButton.setBounds(50,20,600,600);
        //quit.setSize(new Dimension (50,20));
        //quit.setLocation(600,400);
        menuButtons.add(quitButton);

        // Button Position
        mainMenu.add(menuButtons, BorderLayout.CENTER);

        // Add Panel to Frame
        frame.add(mainMenu);


        ////////////////
        // PLAY PANEL //
        ////////////////
        final JPanel playMenu = new JPanel();
        playMenu.setLayout(new BorderLayout());
        playMenu.setBackground(Color.BLACK);

        // Panel for Play Menu Buttons
        final JPanel playButtons = new JPanel();
        playButtons.setLayout(new GridLayout(2, 1));
        playButtons.setBackground(Color.BLACK);

        // Add Play Menu Buttons
        final JButton localMultiButton = new JButton("Play Three Player");
        playButtons.add(localMultiButton);
        final JButton onlineButton = new JButton("Play Online");
        playButtons.add(onlineButton);

        playMenu.add(playButtons, BorderLayout.CENTER);


        //////////////////
        // ONLINE PANEL //
        //////////////////
        final JPanel onlineMenu = new JPanel();
        onlineMenu.setLayout(new BorderLayout());
        onlineMenu.setBackground(Color.BLACK);

        // Panel for Play Menu Buttons
        final JPanel onlineButtons = new JPanel();
        onlineButtons.setLayout(new GridLayout(2, 1));
        onlineButtons.setBackground(Color.BLACK);

        // Add Play Menu Buttons
        // buttons for playMenuUpper
        final JButton joinButton = new JButton("JOIN");
        onlineButtons.add(joinButton);
        // Input Name
        onlineButtons.add(name);

        onlineMenu.add(onlineButtons, BorderLayout.CENTER);

        ////////////////////////
        // INSTRUCTIONS PANEL //
        ////////////////////////
        final JPanel instructionMenu = new JPanel();
        instructionMenu.setLayout(new BorderLayout());
        instructionMenu.setBackground(Color.BLACK);
        // Display image as instructions page. with rules etc.

        ///////////////
        // JOIN MENU //
        ///////////////
        final JPanel testLevelMenu = new JPanel();
        testLevelMenu.setLayout(new GridLayout(1,3));
        testLevelMenu.setBackground(Color.BLACK);

        // Score Label
        final JLabel multiscore = new JLabel("   Score: 0");
        multiscore.setForeground(Color.WHITE);
        multiscore.setBackground(Color.BLACK);
        testLevelMenu.add(multiscore);


        // Restart and Exit Buttons
        final JButton resetButton = new JButton("RESTART");
        testLevelMenu.add(resetButton);
        final JButton multiexitButton = new JButton("EXIT");
        testLevelMenu.add(multiexitButton);

        ///////////////////////
        // THREE PLAYER MENU //
        ///////////////////////
        final JPanel threeMenu = new JPanel();
        threeMenu.setLayout(new GridLayout(1,3));
        threeMenu.setBackground(Color.BLACK);

        // Score Label
        final JLabel score = new JLabel("   Score: 0");
        score.setForeground(Color.WHITE);
        score.setBackground(Color.BLACK);
        threeMenu.add(score);


        // Restart and Exit Buttons
        final JButton threeresetButton = new JButton("RESTART");
        threeMenu.add(threeresetButton);
        final JButton threeexitButton = new JButton("EXIT");
        threeMenu.add(threeexitButton);


        /////////////////////
        // Action Listeners//
        /////////////////////

        // Play Button Action
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainMenu);
                frame.add(playMenu);
                frame.update(frame.getGraphics());
                playMenu.revalidate();
            }
        });

        // Instruction Button Action
        instructionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                if (instructOn) {
                    mainMenu.remove(instrPict);
                    mainMenu.add(pict);
                    instructions.setIcon(
                            new ImageIcon("instructions_before.png"));
                } else if (!instructOn) {
                    mainMenu.remove(pict);
                    mainMenu.add(instrPict);
                    instructions.setIcon(new ImageIcon("main_menu.png"));
                }
                mainMenu.revalidate();
                frame.repaint();
                instructOn = !instructOn;
                */
            }
        });

        // Quit Button Action
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        onlineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(playMenu);
                frame.add(onlineMenu);
                frame.update(frame.getGraphics());
                onlineMenu.revalidate();
            }
        });

        localMultiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create Test Level
                threePlayer = new LocalMultiPlayer(score);
                threePlayer.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                frame.remove(playMenu);
                frame.setLayout(new BorderLayout());
                frame.add(threePlayer, BorderLayout.CENTER);
                frame.add(threeMenu, BorderLayout.SOUTH);
                frame.update(frame.getGraphics());
                threePlayer.requestFocusInWindow();
                threePlayer.revalidate();
                threePlayer.reset();
            }
        });

        joinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getName() != "") {
                    multiPlayer = new Map(score, userName);
                    // Create Test Level
                    multiPlayer.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    frame.remove(onlineMenu);
                    frame.setLayout(new BorderLayout());
                    frame.add(multiPlayer, BorderLayout.CENTER);
                    frame.add(testLevelMenu, BorderLayout.SOUTH);
                    frame.update(frame.getGraphics());
                    multiPlayer.requestFocusInWindow();
                    multiPlayer.revalidate();
                    multiPlayer.reset();
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Please Enter Name");
                }
            }
        });

        multiexitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(testLevelMenu);
                frame.remove(multiPlayer);
                frame.add(mainMenu);
                frame.update(frame.getGraphics());
                mainMenu.revalidate();
            }
        });

        threeexitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(threeMenu);
                frame.remove(threePlayer);
                frame.add(mainMenu);
                frame.update(frame.getGraphics());
                mainMenu.revalidate();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                multiPlayer.reset();
            }
        });

        threeresetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                threePlayer.reset();
            }
        });

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

        public static void main(String[] args){
            SwingUtilities.invokeLater(new Main());
        }

        public String getName(){
            return userName = name.getText();
        }
}

