import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Forest extends ForestDoctor2
{
  private static ArrayList animals;
  private static Forest forest;
  private static String action;
  private static JDialog actionDialog;
  private static JButton submit;
  private static Boolean disabledButton=false;

  public Forest(String action) // constructor for Forest class
  {
    this.animals=new ArrayList<>();
  }

  public static void chooseForest(int forestNum) // choose which forest to run
  {
    if (forestNum==0)
    {
      getDisplay().append("\n                         < CENTRAL FOREST >                           \n"+
                         "A clearing with few trees and shrubs where the light level is highest.\n");
      forest = new CentralForest("");
      gameLoop(forest);
    }
    else if (forestNum==1)
    {
      getDisplay().append("\n                         < NORTH FOREST >                             \n"+
                       "An area of many trees and plants and has a lower light level.         \n");
      forest = new NorthForest("");
      gameLoop(forest);
    }
    else if (forestNum==2)
    {
      getDisplay().append("\n                         < EAST FOREST >                           \n"+
                        "An area almost completely covered with trees that block out the sun.\n");
      forest = new EastForest("");
      gameLoop(forest);
    }
  }

  static void gameLoop (Forest forest) // gameloop
  {
    actionDialog = new JDialog();
    JTextField actionField = new JTextField();
    submit = new JButton("Submit");
    submit.setFocusPainted(false);
    actionDialog.setLayout(new GridLayout(3,0));
    actionDialog.setSize(500,300);
    actionDialog.setFont(new Font("MV Boli", Font.BOLD, 50));
    actionDialog.getContentPane().setBackground(new Color(227, 166, 178));
    actionDialog.setResizable(false);
    actionDialog.add(new JLabel("Enter action: "));
    actionField.setBackground(new Color(188, 236, 165));
    actionDialog.add(actionField);
    actionDialog.add(submit);
    actionDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    actionDialog.setVisible(true);
    submit.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent s)
      {
        if (s.getSource() == submit && !disabledButton)
        {
          actionDialog.dispose();
          actionDialog.setVisible(false);
          action = actionField.getText();
          commands();
          gameLoop(forest);
        }
      }
    });
  }

  public static void commands() // commands the user can call
  {
      if (action.contains("examine"))
      {
          forest.examine(action);
      }
      else if (action.contains("animals"))
      {
          getDisplay().append("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                  "                              < ANIMALS >                      \n");
          forest.animals();
          getDisplay().append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
      }
      else if (action.contains("heal"))
      {
          if (CentralForest.getPlayer2().getInventory().size()<1)
          {
              getDisplay().append("\nYour inventory is empty!");
          }
          else if (CentralForest.getPlayer2().getInventory().size()>=1)
          {
              disabledButton=true;
              forest.heal(action);
              disabledButton=getDisabledButton();
          }
      }
      else if (action.contains("level"))
      {
          getDisplay().append("\nYour healing skill is level " + getPlayer().getLevel() + ".");
      }
      else if (action.contains("take"))
      {
          forest.take(action);
      }
      else if (action.contains("drop"))
      {
          drop();
      }
      else if (action.contains("inventory"))
      {
          getDisplay().append("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+
                  "                              < INVENTORY >                               \n");
          for(int i=0; i<getPlayer().getInventory().size(); i++)
          {
              getDisplay().append(getPlayer().getInventory().get(i) + "\n");
          }
          getDisplay().append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
      }
      else if (action.contains("help"))
      {
          helpDialog();
      }
      else if (action.contains("quit"))
      {
          actionDialog.dispose();
          quit();
      }
      else
      {
          getDisplay().append("\nYou cannot do that!");
      }
  }

  public static JDialog getActionDialog() // returns actiondialog
  {
      return actionDialog;
  }

  protected abstract void animals();

  protected static void drop()
  {
      JButton yesButton = new JButton("Yes");
      JButton noButton = new JButton("No");
      yesButton.setFocusPainted(false);
      noButton.setFocusPainted(false);
      JDialog dropDialog = new JDialog();
      yesButton.setFocusPainted(false);
      noButton.setFocusPainted(false);
      dropDialog.setLayout(new GridLayout(3,0));
      dropDialog.setSize(500,300);
      dropDialog.setFont(new Font("MV Boli", Font.BOLD, 50));
      dropDialog.setBackground(new Color(227, 166, 178));
      dropDialog.setResizable(false);
      dropDialog.add(new JLabel("Do you want to drop MEDICAL HERB? (this action is irreversible)"));
      dropDialog.add(yesButton);
      dropDialog.add(noButton);
      dropDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

      if (CentralForest.getPlayer2().getInventory().size()<1)
      {
        getDisplay().append("Your inventory is empty.");
      }
      else if (action.contains("MEDICAL HERB"))
      {
        dropDialog.setVisible(true);
        getActionDialog().setVisible(false);
        yesButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent s)
          {
            if (s.getSource() == yesButton)
            {
              dropDialog.dispose();
              dropDialog.setVisible(false);
              CentralForest.getPlayer2().getInventory().remove("MEDICAL HERB");
              getDisplay().append("MEDICAL HERB has been removed from your inventory");
            }
          }
        });
        noButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent s)
          {
            if (s.getSource() == noButton)
            {
              dropDialog.dispose();
              dropDialog.setVisible(false);
            }
          }
        });
      }
      else
      {
        getDisplay().append("\nYou cannot drop that!");
      }
  }

    protected abstract void take(String action);

    protected abstract void heal(String action);

    protected abstract void examine(String action);

    public static void setDisabledButton(Boolean value)
    {
        disabledButton=value;
    }

    public static Boolean getDisabledButton()
    {
        return disabledButton;
    }
}
