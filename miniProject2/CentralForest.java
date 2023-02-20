import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.ArrayList;

public class CentralForest extends Forest
{
  private static ArrayList animals;
  private static Player player2;

  public CentralForest(String action) // centralforest constructor
  {
    super(action);
    animals = new ArrayList<>(Arrays.asList(new String[]{"(Lvl 0) BEAR CUB", "(Lvl 1) FOX"}));
    player2 = getPlayer();
    player2.setInventory(new ArrayList<>(Arrays.asList(new String[]{"MEDICAL HERB", "MEDICAL HERB", "MEDICAL HERB"})));
  }

  public void examine(String action) // examine action for centralforest
  {
    if (action.contains("trees"))
    {
      getDisplay().append("\nA handful of tall Hazel trees.");
    }
    else if (action.contains("shrubs"))
    {
      getDisplay().append("\nOddly shaped plants that you do not know about.");
    }
    else
    {
      getDisplay().append("\nYou cannot examine that!");
    }
  }

  public void animals() // animal action for centralforest
  {
     for(Object val : animals)
     {
       getDisplay().append(val + "\n");
     }
  }

  public void heal(String action) // heal action for centralforest
  {
    JButton yesButton = new JButton("Yes");
    JButton noButton = new JButton("No");
    yesButton.setFocusPainted(false);
    noButton.setFocusPainted(false);
    JDialog healDialog = new JDialog();
    yesButton.setFocusPainted(false);
    noButton.setFocusPainted(false);
    healDialog.setLayout(new GridLayout(3,0));
    healDialog.setSize(500,300);
    healDialog.setFont(new Font("MV Boli", Font.BOLD, 50));
    healDialog.getContentPane().setBackground(new Color(227, 166, 178));
    healDialog.setResizable(false);
    healDialog.setLocationRelativeTo(getDisplay());
    healDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    if (action.contains("BEAR CUB"))
    {
      healDialog.add(new JLabel("Heal BEAR CUB with 1 MEDICAL HERB?"));
      healDialog.add(yesButton);
      healDialog.add(noButton);
      healDialog.setVisible(true);
      getActionDialog().setVisible(false);
      yesButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent s)
        {
          if (s.getSource() == yesButton)
          {
            healDialog.dispose();
            healDialog.setVisible(false);
            CentralForest.getPlayer2().getInventory().remove("MEDICAL HERB");
            getDisplay().append("\nBEAR CUB has been healed!");
            player2.setAnimalsHealed(player2.getAnimalsHealed()+1);
            CentralForest.getAnimals().remove("(Lvl 0) BEAR CUB");
            getDisplay().append("\nYour healing skill has advanced to Level 1!");
            player2.setLevel(player2.getLevel()+1);
            setDisabledButton(false);
          }
        }
      });
      noButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent s)
        {
          if (s.getSource() == noButton)
          {
            healDialog.dispose();
            healDialog.setVisible(false);
          }
        }
      });
    }
    else if (action.contains("FOX"))
    {
      if (player2.getLevel()<1)
      {
        getDisplay().append("\nYour level is too low to heal (Lvl 1) FOX");
      }
      else
      {
        healDialog.add(new JLabel("Heal FOX with 1 MEDICAL HERB?"));
        healDialog.add(yesButton);
        healDialog.add(noButton);
        healDialog.setVisible(true);
        getActionDialog().setVisible(false);
        yesButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent s)
          {
            if (s.getSource() == yesButton)
            {
              healDialog.dispose();
              healDialog.setVisible(false);
              CentralForest.getPlayer2().getInventory().remove("MEDICAL HERB");
              getDisplay().append("\nFOX has been healed!");
              player2.setAnimalsHealed(player2.getAnimalsHealed()+1);
              CentralForest.getAnimals().remove("(Lvl 1) FOX");
              getDisplay().append("\nYour healing skill has advanced to Level 2!");
              player2.setLevel(player2.getLevel()+1);
              getDisplay().append("\nWell done! All animals in this area have been healed.           \n"+
                      "You can now move to the next area.....\n");
              getActionDialog().setVisible(false);
              setDisabledButton(false);
              chooseForest(1);
            }
          }
        });
        noButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent s)
          {
            if (s.getSource() == noButton)
            {
              healDialog.dispose();
              healDialog.setVisible(false);
            }
          }
        });
      }
    }
    else
    {
      getDisplay().append("\nYou cannot heal that!");
    }
  }

  public void take(String action) // take action for centralforest
  {
    if (action.contains("shrubs"))
    {
      getDisplay().append("\nThere is no medical quality in these shrubs");
    }
    else
    {
      getDisplay().append("\nYou cannot take that!");
    }
  }

  public static Player getPlayer2() // returns player2
  {
    return player2;
  }

  public static ArrayList getAnimals() // returns animals
  {
    return animals;
  }
}
