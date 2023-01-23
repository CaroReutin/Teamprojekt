package gui.level;

import java.awt.*;
import javax.swing.*;


/**
 * The type J background panel.
 */
public class JBackgroundPanel extends JPanel {

  /**
   * the image of the background of guiLevelPage.
   */
  private final Image background;

  /**
   * Instantiates a new J background panel.
   *
   * @param layout     the layout
   * @param background the background
   */
 /* public JBackgroundPanel(LayoutManager layout, Image background) {
    super(layout);
    this.background = background;
  }*/

  /**
   * Instantiates a new J background panel.
   *
   * @param background the background
   */
  public JBackgroundPanel(Image background) {
    this.background = background;
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    g.drawImage(this.background, 0, 0, this);
  }

}
