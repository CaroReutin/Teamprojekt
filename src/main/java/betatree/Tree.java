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
 * the Tree.
 */
public class Tree {
  private String solution = null;
  /**
   * the frame that shows the tree.
   */
  private final JFrame treeFrame = new JFrame();
  /**
   * the buttons matching to the nodes in nodes Arraylist.
   */
  private final ArrayList<ArrayList<JLabel>> buttons = new ArrayList<>();
  /**
   * the actual buttons matching to the nodes in nodes Arraylist.
   * only the last row.
   */
  private final ArrayList<JButton> actualButtons = new ArrayList<>();
  /**
   * the labels matching to the buttons in buttons Arraylist.
   */
  private final ArrayList<ArrayList<JLabel>> labels = new ArrayList<>();
  /**
   * default scalar
   */
  private double defaultScalar;
  private final Font treeButtonFont = new Font("Arial",
      Font.BOLD + Font.ITALIC, 10);
  private final Font treeLabelFont = new Font("Arial",
      Font.BOLD + Font.ITALIC, 15);


  public Tree(final int itemAmount,
              final boolean isSmallTree,
              final boolean leftCentric) {
    if (itemAmount <= 4) {
      defaultScalar = 2;
    } else if (itemAmount == 5) {
      defaultScalar = 1.5;
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
    int size = (int) Math.min(2 * AppData.MINIMUM_WIDTH
        / (Math.round(actualButtons.size() * defaultScalar)), 2 * AppData.MINIMUM_HEIGHT
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

  public static BufferedImage resize(BufferedImage image, int width, int height) {
    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
    Graphics2D g2d = bi.createGraphics();
    g2d.addRenderingHints(
        new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
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

  public String getSolution() {
    if (solution == null) {
      return null;
    } else if (solution.length() < buttons.size()) {
      return "0".repeat(buttons.size() - solution.length() - 1) + solution;
    }
    return solution;
  }

  public void close() {
    treeFrame.dispose();
  }
}
