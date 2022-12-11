package GUI_Level;

import javax.swing.*;
import java.awt.*;

public class JBackgroundPanel extends JPanel {

    private final Image background;

    public JBackgroundPanel(LayoutManager layout, Image background) {
        super(layout);
        this.background = background;
    }

    public JBackgroundPanel(Image background) {
        this.background = background;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, this);
    }

}
