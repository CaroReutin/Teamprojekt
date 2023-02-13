package betatree;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import solving.AppData;

/**
 * The Tree showing all possible combinations.
 */
public class Tree {
  /**
   * The solution String.
   */
  private String solution = null;
  /**
   * The frame that shows the tree.
   */
  private final JFrame treeFrame = new JFrame();
  /**
   * The buttons matching to the nodes in nodes Arraylist.
   */
  private final ArrayList<ArrayList<JLabel>> buttons = new ArrayList<>();
  /**
   * The actual buttons matching to the nodes in nodes Arraylist.
   * Only the last row.
   */
  private final ArrayList<JButton> actualButtons = new ArrayList<>();
  /**
   * The labels matching to the buttons in buttons Arraylist.
   */
  private final ArrayList<ArrayList<JLabel>> labels = new ArrayList<>();
  /**
   * The default scalar.
   */
  private double defaultScalar;
  /**
   * The value of the default scalar with five items.
   */
  private static final double SMALL_DEFAULT_SCALAR = 1.5;
  /**
   * The font for the buttons of the tree.
   */
  private final Font treeButtonFont = new Font("Arial",
      Font.BOLD + Font.ITALIC, 10);
  /**
   * The font for the labels on the tree.
   */
  private final Font treeLabelFont = new Font("Arial",
      Font.BOLD + Font.ITALIC, 15);

  /**
   * Maximum amount of items in backtracking levels.
   */
  private static final int MAX_AMOUNT = 5;

  /**
   * Constructor of the tree.
   *
   * @param itemAmount Number of different items the tree contains.
   * @param isSmallTree Boolean value whether it is a small tree
   *                    to adjust the default scalar.
   * @param leftCentric Boolean value telling
   *                    whether to put labels on the left side or not.
   */
  public Tree(final int itemAmount,
              final boolean isSmallTree,
              final boolean leftCentric) {
    if (itemAmount < MAX_AMOUNT) {
      defaultScalar = 2;
    } else if (itemAmount == MAX_AMOUNT) {
      defaultScalar = SMALL_DEFAULT_SCALAR;
    } else {
      defaultScalar = 2;
    }
    int rowAmount = 2 * itemAmount - 1;
    int colAmount;
    if (isSmallTree) {
      colAmount = (int) Math.pow(2, itemAmount - 1);
    } else {
      colAmount = (int) Math.pow(2, itemAmount) - 1;
      defaultScalar *= 2;
    }
    Panel panel = new Panel(new GridLayout(rowAmount, colAmount));
    for (int i = 0; i < itemAmount; i++) {
      buttons.add(new ArrayList<>());
      labels.add(new ArrayList<>());
    }
    ArrayList<Integer> columnsWithButtons;
    int rowCounter = 0;
    int rowCounter2 = 0;
    for (int i = 0; i < rowAmount; i++) {
      int stepDistance;
      if (i % 2 == 0) {
        columnsWithButtons = new ArrayList<>();
        stepDistance = (colAmount + 1) / (int) Math.pow(2, i / 2 + 1);
        if (stepDistance == 0) {
          for (int j = 0; j < colAmount; j++) {
            columnsWithButtons.add(j);
          }
        } else {
          for (int j = 1; j * stepDistance <= colAmount; j += 2) {
            if (leftCentric) {
              if (i == 0) {
                columnsWithButtons.add((j - 1) * stepDistance + 1);
                columnsWithButtons.add((j - 1) * stepDistance + 2);
              }
              columnsWithButtons.add((j - 1) * stepDistance);
            } else {
              if (i == 0) {
                columnsWithButtons.add(j * stepDistance - 2);
                columnsWithButtons.add(j * stepDistance);
              }
              columnsWithButtons.add(j * stepDistance - 1);
            }
          }
        }
        int colCounter = 0;
        for (int j = 0; j < colAmount; j++) {
          if (columnsWithButtons.contains(j) && (i + 1 == rowAmount)) {
            actualButtons.add(new JButton(""));
            actualButtons.get(colCounter).setVisible(false);
            int finalColCounter = colCounter;
            actualButtons.get(colCounter).addActionListener(e -> {
              this.solution = Integer.toBinaryString(finalColCounter);
            });
            panel.add(actualButtons.get(colCounter));
            colCounter++;
          } else if (columnsWithButtons.contains(j)) {
            buttons.get(rowCounter).add(new JLabel(""));
            buttons.get(rowCounter).get(colCounter).setVisible(false);
            panel.add(buttons.get(rowCounter).get(colCounter));
            colCounter++;
          } else {
            panel.add(new JLabel(""));
          }
        }
        rowCounter++;
      } else {
        columnsWithButtons = new ArrayList<>();
        stepDistance = (colAmount + 1) / (int) Math.pow(2, (i + 1) / 2 + 1);
        if (stepDistance == 0) {
          for (int j = 0; j < colAmount; j++) {
            columnsWithButtons.add(j);
          }
        } else {
          for (int j = 1; j * stepDistance <= colAmount; j += 2) {
            if (leftCentric) {
              columnsWithButtons.add((j - 1) * stepDistance);
            } else {
              columnsWithButtons.add(j * stepDistance - 1);
            }
          }
        }
        int colCounter = 0;
        for (int j = 0; j < colAmount; j++) {
          if (columnsWithButtons.contains(j)) {
            labels.get(rowCounter2).add(new JLabel(""));
            labels.get(rowCounter2).get(colCounter).setVisible(false);
            panel.add(labels.get(rowCounter2).get(colCounter));
            colCounter++;
          } else {
            panel.add(new JLabel(""));
          }
        }
        rowCounter2++;
      }
    }
    buttons.get(0).get(0).setText("Gewicht");
    //To big for 800x600
    //buttons.get(0).get(0).setFont(treeLabelFont);
    buttons.get(0).get(0).setFont(treeButtonFont);
    buttons.get(0).get(0).setVisible(true);
    buttons.get(0).get(1).setText("/");
    buttons.get(0).get(1).setFont(treeButtonFont);
    buttons.get(0).get(1).setVisible(true);
    buttons.get(0).get(2).setText("Wert");
    buttons.get(0).get(2).setFont(treeButtonFont);
    buttons.get(0).get(2).setVisible(true);
    treeFrame.add(panel);
    treeFrame.setSize(AppData.MINIMUM_WIDTH, AppData.MINIMUM_HEIGHT);
    treeFrame.setVisible(false);
    treeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }

  /**
   * Method for adding a node to the tree.
   *
   * @param row Number of the row to put the node.
   * @param col Number of the column to put the node.
   * @param labelIcon Picture (icon) of the item portrayed by the node.
   * @param buttonText Text at the button.
   */
  public void addNode(final int row, final int col,
                      final ImageIcon labelIcon, final String buttonText) {
    if (row == buttons.size() - 1) {
      actualButtons.get(col).setText(buttonText);
      actualButtons.get(col).setFont(treeButtonFont);
      actualButtons.get(col).setMargin(new Insets(0, 0, 0, 0));
      actualButtons.get(col).setVisible(true);
    } else {
      buttons.get(row).get(col).setText(buttonText);
      buttons.get(row).get(col).setFont(treeLabelFont);
      buttons.get(row).get(col).setVisible(true);
    }
    BufferedImage temp = new BufferedImage(labelIcon.getIconWidth(),
        labelIcon.getIconHeight(),
        BufferedImage.TYPE_INT_RGB);
    Graphics g = temp.createGraphics();
    labelIcon.paintIcon(null, g, 0, 0);
    g.dispose();
    int size = (int) Math.min(
            2 * AppData.MINIMUM_WIDTH
                    / (Math.round(actualButtons.size() * defaultScalar)),
            2 * AppData.MINIMUM_HEIGHT
                    / (Math.round(actualButtons.size() * defaultScalar)));
    labels.get(row - 1).get(col).setIcon(new ImageIcon(
        resize(temp, size, size)));
    //labels.get(row - 1).get(col).setIcon(labelIcon);
    labels.get(row - 1).get(col).setVisible(true);
    treeFrame.getContentPane().revalidate();
    treeFrame.getContentPane().repaint();
    treeFrame.revalidate();
    treeFrame.repaint();
  }

  /**
   * Method for resizing an image into the wanted dimensions.
   *
   * @param image The image ought to be resized.
   * @param width The width the image is supposed to have.
   * @param height The height the image is supposed to have.
   * @return The resized image.
   */
  public static BufferedImage resize(final BufferedImage image,
                                     final int width,
                                     final int height) {
    BufferedImage bi = new BufferedImage(width, height,
            BufferedImage.TRANSLUCENT);
    Graphics2D g2d = bi.createGraphics();
    g2d.addRenderingHints(
        new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));
    g2d.drawImage(image, 0, 0, width, height, null);
    g2d.dispose();
    return bi;
  }

  /**
   * show the tree.
   */
  public void show() {
    treeFrame.setVisible(true);
  }

  /**
   * Method for getting the solution as a String.
   *
   * @return String of the solution.
   */
  public String getSolution() {
    if (solution == null) {
      return null;
    } else if (solution.length() < buttons.size()) {
      return "0".repeat(buttons.size() - solution.length() - 1) + solution;
    }
    return solution;
  }

  /**
   * Method for shutting down the tree and disposing the current input.
   */
  public void close() {
    treeFrame.dispose();
  }
}
