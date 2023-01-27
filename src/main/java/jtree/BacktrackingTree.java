package jtree;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import rucksack.Item;
import rucksack.Level;

/**
 * .
 */
public class BacktrackingTree {
  /**
   * .
   */
  private final JTree tree;

  /**
   * .
   *
   * @param level the level
   */
  public BacktrackingTree(final Level level) {
    ArrayList<Item> itemList = new ArrayList<>();
    for (int i = 0; i < level.getItemList().size(); i++) {
      for (int j = 0; j < level.getItemAmountList().get(i); j++) {
        itemList.add(level.getItemList().get(i));
      }
    }
    BacktrackingNode root = new BacktrackingNode(itemList, level.getCapacity());
    tree = new JTree(root);
    tree.setRootVisible(true);
    tree.setVisible(true);
    JFrame frame = new JFrame();
    frame.setSize(400, 400);
    frame.add(tree);
    frame.setVisible(true);
  }

  private static class BacktrackingNode extends DefaultMutableTreeNode {
    BacktrackingNode(final ArrayList<Item> itemsLeft, final boolean included,
                     final int currentWeight, final int currentValue,
                     final int capacity) {
      ArrayList<Item> newItemsLeft = new ArrayList<>(itemsLeft);
      Item myItem = newItemsLeft.get(0);
      String userObject = "";
      if (!included) {
        userObject += "not ";
        userObject += (myItem.getBacktrackingName(itemsLeft.size()));
        userObject += "Weight: " + currentWeight
            + " ".repeat(Math.max(0, 5 - String
            .valueOf(currentWeight).length())) + " | ";
        userObject += "Value: " + currentValue;
      } else {
        userObject += (myItem.getBacktrackingName(itemsLeft.size())) + "  ";
        userObject += "    ";
        int newWeight = currentWeight + myItem.getWeight();
        int newValue = currentValue + myItem.getValue();
        userObject += "Weight: " + newWeight
            + " ".repeat(Math.max(0, 5 - String
            .valueOf(newWeight).length())) + " | ";
        userObject += "Value: " + newValue;
      }
      super.setUserObject(userObject);
      newItemsLeft.remove(0);
      if (newItemsLeft.size() != 0) {
        // Only add Items that can fit
        if (!(currentWeight + myItem.getWeight() > capacity)) {
          super.add(new BacktrackingNode(newItemsLeft, true,
              currentWeight + myItem.getWeight(),
              currentValue + myItem.getValue(), capacity));
        }
        super.add(new BacktrackingNode(newItemsLeft, false,
            currentWeight, currentValue, capacity));
      }
    }

    BacktrackingNode(final ArrayList<Item> itemsLeft, final int capacity) {
      ArrayList<Item> newItemsLeft = new ArrayList<>(itemsLeft);
      super.setUserObject("root");
      if (newItemsLeft.size() != 0) {
        super.add(new BacktrackingNode(newItemsLeft, true, 0, 0, capacity));
        super.add(new BacktrackingNode(newItemsLeft, false, 0, 0, capacity));
      }
    }
  }

}
