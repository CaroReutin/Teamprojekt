package gui.level;

import javax.swing.*;
import java.awt.*;

public class JbackgroundWithItems extends JPanel {

  /**
   * the image of the background of guiLevelPage.
   */
  private final Image background;

  /**
   * the x position of the picture.
   */
  private final int xPos;

  /**
   * the y position of the picture.
   */
  private final int yPos;


  /**
   * Instantiates a new J background panel.
   *
   * @param myBackground the background
   * @param x            the x
   * @param y            the y
   */
  public JbackgroundWithItems(final Image myBackground, final int x, final int y) {
    this.background = myBackground;
    this.xPos = x;
    this.yPos = y;
  }

  /**
   * paints.
   */
  @Override
  protected void paintComponent(final Graphics g) {

    super.paintComponent(g);
    g.drawImage(this.background, this.xPos, this.yPos, this);
  }


}
