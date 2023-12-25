import javax.swing.*;

import Calculator_V1.AppInterface;

public class Main{
    public static void main(String args[]) {
        AppInterface frame = new AppInterface();

        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}