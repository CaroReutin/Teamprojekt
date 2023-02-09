package betatree;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * the Tree.
 */
public class Tree {
  private String solution = "";
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


  public Tree(final int itemAmount,
              final boolean isSmallTree,
              final boolean leftCentric) {
    int rowAmount = 2 * itemAmount - 1;
    int colAmount;
    if (isSmallTree) {
      colAmount = (int) Math.pow(2, itemAmount - 1);
    } else {
      colAmount = (int) Math.pow(2, itemAmount) - 1;
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
              columnsWithButtons.add((j - 1) * stepDistance);
            } else {
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
    buttons.get(0).get(0).setText("Gewicht/Wert");
    buttons.get(0).get(0).setVisible(true);
    treeFrame.add(panel);
    treeFrame.setSize(1000, 750);
    treeFrame.setVisible(false);
    treeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }

  public void addNode(final int row, final int col,
                      final ImageIcon labelIcon, final String buttonText) {
    if (row == buttons.size() - 1) {
      actualButtons.get(col).setText(buttonText);
      actualButtons.get(col).setVisible(true);
    } else {
      buttons.get(row).get(col).setText(buttonText);
      buttons.get(row).get(col).setVisible(true);
    }
    labels.get(row - 1).get(col).setIcon(labelIcon);
    // TODO Center Label Icon
    labels.get(row - 1).get(col).setVisible(true);
    treeFrame.getContentPane().revalidate();
    treeFrame.getContentPane().repaint();
    treeFrame.revalidate();
    treeFrame.repaint();
  }


  /**
   * show the tree.
   */
  public void show() {
    treeFrame.setVisible(true);
  }

  public String getSolution() {
    return solution;
  }
}
