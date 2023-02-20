import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.ArrayList;

public class NorthForest extends Forest
{
    private static ArrayList animals;
    private static Player player2 = CentralForest.getPlayer2();

    public NorthForest(String action) // northforest constructor
    {
        super(action);
        animals = new ArrayList<>(Arrays.asList(new String[]{"(Lvl 2) SQUIRREL", "(Lvl 3) PARROT", "(Lvl 4) MONKEY"}));
    }

    public void examine(String action) // examine action for northforest
    {
        if (action.contains("trees"))
        {
            getDisplay().append("\nNumerous Fir and Pine trees.");
        }
        else if (action.contains("plants"))
        {
            getDisplay().append("\nThese plants can be used to heal animals.");
        }
        else
        {
            getDisplay().append("\nYou cannot examine that!");
        }
    }

    public void animals() // animals action for northforest
    {
        for(Object val : animals)
        {
            getDisplay().append(val + "\n");
        }
    }

    public void heal(String action) // heal action for northforest
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

        if (action.contains("SQUIRREL"))
        {
            healDialog.add(new JLabel("Heal SQUIRREL with 1 MEDICAL HERB?"));
            healDialog.add(yesButton);
            healDialog.add(noButton);
            healDialog.setVisible(true);
            yesButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent s)
                {
                    if (s.getSource() == yesButton)
                    {
                        healDialog.dispose();
                        healDialog.setVisible(false);
                        CentralForest.getPlayer2().getInventory().remove("MEDICAL HERB");
                        getDisplay().append("\nSQUIRREL has been healed!");
                        player2.setAnimalsHealed(player2.getAnimalsHealed()+1);
                        NorthForest.getAnimals().remove("(Lvl 2) SQUIRREL");
                        getDisplay().append("\nYour healing skill has advanced to Level 3!");
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
        else if (action.contains("PARROT"))
        {
            if (player2.getLevel()<3)
            {
                getDisplay().append("\nYour level is too low to heal (Lvl 3) PARROT");
            }
            else
            {
                healDialog.add(new JLabel("Heal PARROT with 1 MEDICAL HERB?"));
                healDialog.add(yesButton);
                healDialog.add(noButton);
                healDialog.setVisible(true);
                yesButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent s)
                    {
                        if (s.getSource() == yesButton)
                        {
                            healDialog.dispose();
                            healDialog.setVisible(false);
                            CentralForest.getPlayer2().getInventory().remove("MEDICAL HERB");
                            getDisplay().append("\nPARROT has been healed!");
                            player2.setAnimalsHealed(player2.getAnimalsHealed()+1);
                            NorthForest.getAnimals().remove("(Lvl 3) PARROT");
                            getDisplay().append("\nYour healing skill has advanced to Level 4!");
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
        }
        else if (action.contains("MONKEY"))
        {
            if (player2.getLevel()<4)
            {
                getDisplay().append("\nYour level is too low to heal (Lvl 4) MONKEY");
            }
            else
            {
                healDialog.add(new JLabel("Heal MONKEY with 1 MEDICAL HERB?"));
                healDialog.add(yesButton);
                healDialog.add(noButton);
                healDialog.setVisible(true);
                yesButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent s)
                    {
                        if (s.getSource() == yesButton)
                        {
                            healDialog.dispose();
                            healDialog.setVisible(false);
                            CentralForest.getPlayer2().getInventory().remove("MEDICAL HERB");
                            getDisplay().append("\nMONKEY has been healed!");
                            player2.setAnimalsHealed(player2.getAnimalsHealed()+1);
                            NorthForest.getAnimals().remove("(Lvl 4) MONKEY");
                            getDisplay().append("\nYour healing skill has advanced to Level 5!");
                            player2.setLevel(player2.getLevel()+1);
                            getDisplay().append("\nWell done! All animals in this area have been healed.           \n"+
                                    "You can now move to the final area.....\n");
                            getActionDialog().setVisible(false);
                            chooseForest(2);
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
        }
        else
        {
            getDisplay().append("\nYou cannot heal that!");
        }
    }

    private static ArrayList getAnimals() // returns animals
    {
        return animals;
    }

    public void take(String action) // take action for northforest
    {
        if (action.contains("plants"))
        {
            getDisplay().append("\nYou receive 2 MEDICAL HERBS");
            player2.getInventory().add("MEDICAL HERB");
            player2.getInventory().add("MEDICAL HERB");
        }
        else
        {
            getDisplay().append("You cannot take that!");
        }
    }
}
