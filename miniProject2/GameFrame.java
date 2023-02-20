import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    GameFrame()
    {
        this.setTitle("Forest Doctor Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(750,750);
        this.setLayout(null);

        ImageIcon image = new ImageIcon("Picture1.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(63, 241, 96));
    }
}
