package gui.level;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * Class for a GUI background with items.
 */
public class JbackgroundWithItems extends JPanel {

  /**
   * the image of the background of guiLevelPage.
   */
  private final Image background;

  /**
   * the x position of the picture.
   */
  private final int xpos;

  /**
   * the y position of the picture.
   */
  private final int ypos;


  /**
   * Instantiates a new J background panel.
   *
   * @param myBackground the background
   * @param x            the x
   * @param y            the y
   */
  public JbackgroundWithItems(final Image myBackground,
                              final int x, final int y) {
    this.background = myBackground;
    this.xpos = x;
    this.ypos = y;
    setLayout(new BorderLayout());
  }

  /**
   * paints.
   */
  @Override
  protected void paintComponent(final Graphics g) {

    super.paintComponent(g);
    g.drawImage(this.background, this.xpos, this.ypos, this);
  }


}
