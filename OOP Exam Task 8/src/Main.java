import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("OK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton ok_button = new JButton("OK");
        ok_button.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        frame.dispose();
                    }
                }
        );
        frame.setSize(200, 300);
        frame.add(ok_button);
        frame.setVisible(true);
    }
}