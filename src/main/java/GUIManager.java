import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIManager {
    // for now i'll put it here, not sure where it is most appropriate to put
    private static ArrayList<String> passwords = new ArrayList<>();
    public static ArrayList<String> getPasswords(){
        return passwords;
    }
    // see comment above

    private void innit() {
        passwords.add("Gr33dy");
    }

    private LevelManager lm = new LevelManager();
    GUIFrontpage guiFrontPage = new GUIFrontpage();

    public void launch(){
        innit();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Optimal Heist");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        guiFrontPage.getFrontPage(frame);
    }

    public void rePaintFrame(Container pane) {
        pane.removeAll();
        pane.revalidate();
        pane.repaint();
    }
}