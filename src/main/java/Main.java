import javax.swing.*;

public class Main{

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton close = new JButton("Close");
        close.addActionListener(e -> frame.dispose());
        frame.add(close);
        frame.setTitle("Optimal Heist");
        frame.setSize(400,400);
        frame.setVisible(true);
    }

}