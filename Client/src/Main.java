import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main implements Runnable{


    public void run() {
        // Main Frame
        final JFrame frame = new JFrame("Light Cycles");
        frame.setBackground(Color.BLACK);
        frame.setMinimumSize(new Dimension(800, 800));
        //Dimension dMAX = frame.getMaximumSize();
        frame.setPreferredSize(new Dimension(1200, 800));

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
        playButtons.setLayout(new GridLayout(1, 1));
        playButtons.setBackground(Color.BLACK);


        // Add Play Menu Buttons


        ////////////////////////
        // INSTRUCTIONS PANEL //
        ////////////////////////
        final JPanel instructionMenu = new JPanel();
        instructionMenu.setLayout(new BorderLayout());
        instructionMenu.setBackground(Color.BLACK);
        // Display image as instructions page. with rules etc.

        /////////////////////
        // Action Listeners//
        /////////////////////

        // Play Button Action
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainMenu);
                frame.add(playMenu);
                //frame.update(frame.getGraphics());
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


        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
        public static void main(String[] args){
            SwingUtilities.invokeLater(new Main());
        }
}

