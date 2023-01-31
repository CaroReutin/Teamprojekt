package betaTree;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import rucksack.Item;
import rucksack.Level;

public class TreeSmol {
  public TreeSmol(final Level level) {
    ArrayList<Item> items = new ArrayList<>();
    items.add(new Item(0, 0, "root"));
    for (int i = 0; i < level.getItemList().size(); i++) {
      for (int j = 0; j < level.getItemAmountList().get(i); j++) {
        items.add(level.getItemList().get(i));
      }
    }
    int itemAmount = items.size();
    int rowAmount = (int) 2 * itemAmount - 1;
    int colAmount = (int) Math.pow(2, itemAmount) - 1;
    JFrame treeFrame = new JFrame();
    Panel panel = new Panel(new GridLayout(rowAmount, colAmount));
    ArrayList<Integer> columnsWithButtons = new ArrayList<>();
    for (int i = 0; i < rowAmount; i++) {
      if (i % 2 == 0) {
        columnsWithButtons = new ArrayList<>();
        int stepDistance = (colAmount + 1) / (int) Math.pow(2, i / 2 + 1);
        System.out.println(stepDistance);
        for (int j = 1; j * stepDistance <= colAmount; j += 2) {
          columnsWithButtons.add(j * stepDistance - 1);
        }
        for (int j = 0; j < colAmount; j++) {
          if (columnsWithButtons.contains(j)) {
            panel.add(new JButton("0/0/0"));
          } else {
            panel.add(new JLabel(""));
          }
        }
      } else {
        columnsWithButtons = new ArrayList<>();
        int stepDistance = (colAmount + 1) / (int) Math.pow(2, (i + 1) / 2 + 1);
        System.out.println(stepDistance);
        for (int j = 1; j * stepDistance <= colAmount; j += 2) {
          columnsWithButtons.add(j * stepDistance - 1);
        }
        for (int j = 0; j < colAmount; j++) {
          if (columnsWithButtons.contains(j)) {
            panel.add(new JLabel(items.get((i + 1) / 2).getIcon()));
          } else {
            panel.add(new JLabel(""));
          }
        }
      }

    }
    treeFrame.add(panel);
    treeFrame.setSize(1000, 1000);
    treeFrame.setVisible(true);
    treeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
