/*
Kishan Kumaran Thanikasalam
OOP Miniproject - Game
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ForestDoctor2
{
  private static Player player;
  private static GameFrame gFrame;
  private static IntroFrame iFrame;
  private static JTextArea display = new JTextArea("Welcome to ...\n", 50, 50);;

  public static void main (String[] param) throws IOException
  {
    new ForestDoctor2().GUISetUp();
  }

  public void GUISetUp () throws IOException // sets up introframe
  {
    iFrame = new IntroFrame();
    iFrame.setLocationRelativeTo(null);
    BufferedImage bufferedImage = ImageIO.read(new File("Picture1.png"));
    Image image = bufferedImage.getScaledInstance(400, 200, Image.SCALE_DEFAULT);
    ImageIcon image2 = new ImageIcon(image);
    Border border1 = BorderFactory.createBevelBorder(0);
    Border border2 = BorderFactory.createBevelBorder(0);

    JPanel options = new JPanel();
    options.setBackground(Color.ORANGE);
    options.setBounds(200, 400, 325, 260);
    options.setOpaque(true);
    options.setBorder(border2);
    options.setLayout(new GridLayout(3, 0, 20, 20));

    JButton playButton = new JButton();
    playButton.setBounds(300, 425, 125, 50);
    playButton.setText("Play");
    playButton.setFont(new Font("Comic Sans", Font.BOLD, 25));
    playButton.setFocusPainted(false);
    playButton.setForeground(Color.darkGray);
    playButton.setBorder(BorderFactory.createRaisedBevelBorder());
    playButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent s) {
        if (s.getSource() == playButton) {
          iFrame.setVisible(false);
          gameStart();
        }
      }
    });

    JButton helpButton = new JButton();
    helpButton.setBounds(300, 550, 125, 50);
    helpButton.setText("Help");
    helpButton.setFont(new Font("Comic Sans", Font.BOLD, 25));
    helpButton.setFocusPainted(false);
    helpButton.setForeground(Color.darkGray);
    helpButton.setBorder(BorderFactory.createRaisedBevelBorder());
    helpButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent s) {
        if (s.getSource() == helpButton) {
          helpDialog();
        }
      }
    });

    JButton aboutButton = new JButton();
    aboutButton.setBounds(300, 425, 125, 50);
    aboutButton.setText("About");
    aboutButton.setFont(new Font("Comic Sans", Font.BOLD, 25));
    aboutButton.setFocusPainted(false);
    aboutButton.setForeground(Color.darkGray);
    aboutButton.setBorder(BorderFactory.createRaisedBevelBorder());
    aboutButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent s) {
        if (s.getSource() == aboutButton) {
          aboutDialog(iFrame);
        }
      }
    });

    options.add(playButton);
    options.add(helpButton);
    options.add(aboutButton);

    JLabel title = new JLabel();
    title.setText("Forest Doctor");
    title.setIcon(image2);
    title.setHorizontalTextPosition(JLabel.CENTER);
    title.setVerticalTextPosition(JLabel.TOP);
    title.setForeground(Color.blue);
    title.setFont(new Font("MV Boli", Font.BOLD, 50));
    title.setIconTextGap(50);
    title.setBackground(new Color(227, 166, 178));
    title.setOpaque(true);
    title.setBorder(border1);
    title.setVerticalAlignment(JLabel.TOP);
    title.setHorizontalAlignment(JLabel.CENTER);
    title.setBounds(50, 50, 625, 625);

    iFrame.setLayout(null);
    iFrame.add(options);
    iFrame.add(title);
    iFrame.setVisible(true);
  }

  public static void quit() // option to quit the game
  {
    JDialog quitDialog = new JDialog();
    JButton yesButton = new JButton("Yes");
    JButton noButton = new JButton("No");
    yesButton.setFocusPainted(false);
    noButton.setFocusPainted(false);
    quitDialog.setLayout(new GridLayout(3,0));
    quitDialog.setSize(500,300);
    quitDialog.setFont(new Font("MV Boli", Font.BOLD, 50));
    quitDialog.setBackground(new Color(227, 166, 178));
    quitDialog.setResizable(false);
    quitDialog.add(new JLabel("Are you sure you want to quit?"));
    quitDialog.add(yesButton);
    quitDialog.add(noButton);
    quitDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    quitDialog.setVisible(true);

    yesButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent s)
      {
        if (s.getSource() == yesButton)
        {
          quitDialog.dispose();
          quitDialog.setVisible(false);
          Forest.getActionDialog().setVisible(false);
          getGameFrame().setVisible(false);
          iFrame.setVisible(true);
        }
      }
    });
    noButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent s)
      {
        if (s.getSource() == noButton)
        {
          quitDialog.dispose();
          quitDialog.setVisible(false);
        }
      }
    });
  }

  public void gameStart() // sets up game window
  {
    gFrame = new GameFrame();
    JLabel title = new JLabel();
    Border border1 = BorderFactory.createBevelBorder(0);

    ScrollPane textDisplay = new ScrollPane();
    textDisplay.setBounds(100, 150, 525, 475);
    getDisplay().setBackground(new Color(188, 236, 165));
    getDisplay().setFont(new Font("Tubular", Font.PLAIN, 15));
    textDisplay.add(getDisplay());
    getDisplay().append("_____   _____     _______     ______   ______   ______\n"+
                      "\\  __\\ \\  __ \\    \\   _  \\   \\  ____\\ \\    __\\ \\_   _\\\n"+
                      " \\ \\___ \\ \\ \\ \\    \\ \\_\\  \\   \\ \\___   \\____      \\ \\\n"+
                      "  \\  __\\ \\ \\ \\ \\    \\   _  \\_  \\  ___\\      \\ \\    \\ \\\n"+
                      "   \\ \\    \\ \\_\\ \\    \\ \\  \\  \\  \\ \\___    __\\  \\    \\ \\\n"+
                      "    \\_\\    \\_____\\    \\_\\  \\__\\  \\_____\\  \\_____\\    \\_\\\n\n"+
                      "     _____    ______   ______   _______   ______   ______     \n"+
                      "    / _   \\  / ___ /  /  ___/  /__  __/  / ___ /  /   _  \\       \n"+
                      "   / / \\  / / /  //  /  /        / /    / /  //  /   /_/ /       \n"+
                      "  / /  / / / /  //  /  /        / /    / /  //  /      _/        \n"+
                      " / /__/ / / /__//  /  /___     / /    / /__//  / /\\  /_          \n"+
                      "/______/ /_____/  /______/    /_/    /_____/  /_/  \\__|       \n");

    title.setText("Forest Doctor Game");
    title.setHorizontalTextPosition(JLabel.CENTER);
    title.setVerticalTextPosition(JLabel.TOP);
    title.setForeground(Color.blue);
    title.setFont(new Font("MV Boli", Font.BOLD, 50));
    title.setIconTextGap(50);
    title.setBackground(new Color(227, 166, 178));
    title.setOpaque(true);
    title.setBorder(border1);
    title.setVerticalAlignment(JLabel.TOP);
    title.setHorizontalAlignment(JLabel.CENTER);
    title.setBounds(50, 50, 625, 625);

    gFrame.setLayout(null);
    gFrame.add(textDisplay);
    gFrame.add(title);
    gFrame.setLocationRelativeTo(null);
    gFrame.setVisible(true);

    JDialog nameDialog = new JDialog();
    JTextField nameField = new JTextField();
    JButton submit = new JButton("Submit");
    submit.setFocusPainted(false);
    nameDialog.setLayout(new GridLayout(3,0));
    nameDialog.setSize(500,300);
    nameDialog.setFont(new Font("MV Boli", Font.BOLD, 50));
    nameDialog.getContentPane().setBackground(new Color(227, 166, 178));
    nameDialog.setResizable(false);
    nameDialog.add(new JLabel("Enter character name: "));
    nameField.setBackground(new Color(188, 236, 165));
    nameDialog.add(nameField);
    nameDialog.add(submit);
    nameDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    submit.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent s)
      {
        if (s.getSource() == submit)
        {
          nameDialog.dispose();
          nameDialog.setVisible(false);
          player = new Player(nameField.getText(), 0, new ArrayList<String>(), 0);
          printWelcome();
          textDisplay.repaint();
          SwingWorker sw1 = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
              Forest.chooseForest(0);
              return null;
            }
          };
          sw1.execute();
        }
      }
    });

    nameDialog.setVisible(true);
  }

  public static void printWelcome() // displays welcome message
  {
    getDisplay().append("\nWelcome to the Forest, Dr " + player.getName() + "! It looks like you got here just\n"+
            "in time. There are many injured animals here that we need you to heal.\n"+
            "You can use the surrounding plants and herbs to improve your skill.\n"+
            "Type 'help' if you need to see the list of commands. To get you started,\n"+
            "we have provided you with 3 MEDICAL HERBS. You have been dropped off in\n"+
            "the CENTRAL FOREST. Good Luck.\n");
  }

  public void gameFinish() throws IOException // game finish message
  {
    JDialog finishDialog = new JDialog(iFrame);
    finishDialog.setLayout(new FlowLayout());
    finishDialog.setBounds(500, 300, 920, 200);
    finishDialog.getContentPane().setBackground(new Color(227, 166, 178));
    String helpString = "Congratulations Dr " + player.getName() + "! You have successfully healed\n"+
            "all the animals and completed the game!";
    Scanner scanner = new Scanner(helpString);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      JLabel aboutLabel = new JLabel(line);
      aboutLabel.setHorizontalAlignment(JLabel.CENTER);
      aboutLabel.setForeground(Color.blue);
      aboutLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
      finishDialog.add(aboutLabel);
    }
    scanner.close();
    JButton closeButton = new JButton("Close");
    closeButton.setFocusPainted(false);
    closeButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        finishDialog.setVisible(false);
      }
    });
    finishDialog.add(closeButton);
    finishDialog.setResizable(false);
    gFrame.setVisible(false);
    new ForestDoctor2().GUISetUp();
    finishDialog.setVisible(true);
  }

  public static Player getPlayer() // returns the player object
  {
    return player;
  }

  public static GameFrame getGameFrame() // returns gameframe object
  {
    return gFrame;
  }

  public static JTextArea getDisplay() // returns display
  {
    return display;
  }

  public static void helpDialog() // displays help commands
  {
    JDialog helpDialog = new JDialog();
    helpDialog.setLayout(new GridLayout(13,0,20,20));
    helpDialog.setBounds(500, 300, 900, 600);
    helpDialog.getContentPane().setBackground(new Color(227, 166, 178));
    String helpString = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+
            "                          < COMMANDS >                             \n"+
            "examine OBJECT - observe an object or place                      \n"+
            "animals          - returns list of injured animals                 \n"+
            "level            - view your healing level                         \n"+
            "heal ANIMAL    - heal an animal                                  \n"+
            "take OBJECT    - adds object to inventory                        \n"+
            "drop OBJECT    - removes object from inventory                   \n"+
            "inventory        - displays the items in your inventory            \n"+
            "help             - displays commands                               \n"+
            "quit             - quits the game to the main menu                 \n"+
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    Scanner scanner = new Scanner(helpString);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      JLabel helpLabel = new JLabel(line);
      helpLabel.setHorizontalAlignment(JLabel.CENTER);
      helpLabel.setForeground(Color.blue);
      helpLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
      helpDialog.add(helpLabel);
    }
    scanner.close();
    JButton closeButton = new JButton("Close");
    closeButton.setFocusPainted(false);
    closeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        helpDialog.setVisible(false);
      }
    });
    helpDialog.add(closeButton);
    helpDialog.setResizable(false);
    helpDialog.setVisible(true);
  }

  public static void aboutDialog(IntroFrame iFrame) // displays about message
  {
    JDialog aboutDialog = new JDialog(iFrame);
    aboutDialog.setLayout(new FlowLayout());
    aboutDialog.setBounds(500, 300, 920, 200);
    aboutDialog.getContentPane().setBackground(new Color(227, 166, 178));
    String helpString = "This is a game where you have to venture through the forest and heal\n"+
            "wounded animals while collecting plants and herbs to improve your healing skill.";
    Scanner scanner = new Scanner(helpString);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      JLabel aboutLabel = new JLabel(line);
      aboutLabel.setHorizontalAlignment(JLabel.CENTER);
      aboutLabel.setForeground(Color.blue);
      aboutLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
      aboutDialog.add(aboutLabel);
    }
    scanner.close();
    JButton closeButton = new JButton("Close");
    closeButton.setFocusPainted(false);
    closeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        aboutDialog.setVisible(false);
      }
    });
    aboutDialog.add(closeButton);
    aboutDialog.setResizable(false);
    aboutDialog.setVisible(true);
  }
}
