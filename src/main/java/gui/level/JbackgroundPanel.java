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
   * the x position of the picture.
   */
  private final int x;



  /**
   * Instantiates a new J background panel.
   *
   * @param myBackground the background
   * @param x            the x
   * @param y            the y
   */
  public JbackgroundPanel(final Image myBackground, int x) {
    this.background = myBackground;
    this.x = x;
  }

  /**
   * paints.
   */
  @Override
  protected void paintComponent(final Graphics g) {

    super.paintComponent(g);
    g.drawImage(this.background, this.x, 0, this);
  }
}
