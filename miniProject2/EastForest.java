import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class EastForest extends Forest
{
    private static ArrayList animals;
    private static Player player2 = CentralForest.getPlayer2();

    public EastForest(String action) // eastforest constructor
    {
        super(action);
        animals = new ArrayList<>(Arrays.asList(new String[]{"(Lvl 5) LION", "(Lvl 6) TIGER", "(Lvl 7) WOLF"}));
    }

    public void examine(String action) // examine action for eastforest
    {
        if (action.contains("trees"))
        {
            getDisplay().append("\nMany tall and wide trees of varied types, including oaks and healing leaves.");
        }
        else
        {
            getDisplay().append("\nYou cannot examine that!");
        }
    }

    public void animals() // animals action for eastforest
    {
        for(Object val : animals)
        {
            getDisplay().append(val + "\n");
        }
    }

    public void heal(String action) // heal action for eastforest
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

        if (action.contains("LION"))
        {
            healDialog.add(new JLabel("Heal LION with 1 MEDICAL HERB?"));
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
                        getDisplay().append("\nLION has been healed!");
                        player2.setAnimalsHealed(player2.getAnimalsHealed()+1);
                        EastForest.getAnimals().remove("(Lvl 5) LION");
                        getDisplay().append("\nYour healing skill has advanced to Level 6!");
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
        else if (action.contains("TIGER") )
        {
            if (player2.getLevel()<6)
            {
                getDisplay().append("\nYour level is too low to heal (Lvl 6) TIGER");
            }
            else
            {
                healDialog.add(new JLabel("Heal TIGER with 1 MEDICAL HERB?"));
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
                            getDisplay().append("\nTIGER has been healed!");
                            player2.setAnimalsHealed(player2.getAnimalsHealed()+1);
                            EastForest.getAnimals().remove("(Lvl 6) TIGER");
                            getDisplay().append("\nYour healing skill has advanced to Level 7!");
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
        else if (action.contains("WOLF"))
        {
            if (player2.getLevel()<7)
            {
                getDisplay().append("\nYour level is too low to heal (Lvl 7) WOLF");
            }
            else
            {
                healDialog.add(new JLabel("Heal WOLF with 1 MEDICAL HERB?"));
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
                            getDisplay().append("\nWOLF has been healed!");
                            player2.setAnimalsHealed(player2.getAnimalsHealed()+1);
                            EastForest.getAnimals().remove("(Lvl 7) WOLF");
                            getDisplay().append("\nYour healing skill has advanced to Level 8!");
                            player2.setLevel(player2.getLevel()+1);
                            setDisabledButton(false);
                            getActionDialog().dispose();
                            try {
                                gameFinish();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
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

    public void take(String action) // take action for eastforest
    {
        if (action.contains("leaves") || action.contains("LEAVES"))
        {
            getDisplay().append("\nYou receive 3 MEDICAL HERBS");
            player2.getInventory().add("MEDICAL HERB");
            player2.getInventory().add("MEDICAL HERB");
            player2.getInventory().add("MEDICAL HERB");
        }
        else
        {
            getDisplay().append("\nYou cannot take that!");
        }
    }

    public static ArrayList getAnimals() // returns animals
    {
        return animals;
    }
}
