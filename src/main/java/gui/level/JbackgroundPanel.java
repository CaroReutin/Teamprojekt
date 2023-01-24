package gui.level;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;


/**
 * The type J background panel.
 */
public class JbackgroundPanel extends JPanel {

  /**
   * the image of the background of guiLevelPage.
   */
  private final Image background;


  /**
   * Instantiates a new J background panel.
   *
   * @param myBackground the background
   */
  public JbackgroundPanel(final Image myBackground) {
    this.background = myBackground;
  }

  /**
   * paints.
   */
  @Override
  protected void paintComponent(final Graphics g) {

    super.paintComponent(g);
    g.drawImage(this.background, 0, 0, this);
  }
}
