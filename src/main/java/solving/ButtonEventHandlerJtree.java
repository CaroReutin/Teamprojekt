package solving;

import gui.level.GuiLevelPageBacktracking;
import gui.level.GuiManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import jtree.BacktrackingJTree;
import rucksack.BacktrackingItem;
import rucksack.Level;

public class ButtonEventHandlerJtree extends ButtonEventHandler {

  private final BacktrackingJTree tree;
  private int lastDepth;

  public ButtonEventHandlerJtree(final Level level) {
    lastDepth = 0;
    tree = new BacktrackingJTree();
    myLevel = level;
    myLevel.turnIntoBacktracking();
    ArrayList<BacktrackingItem> oldList = level.getBacktrackingItemList();
    ArrayList<BacktrackingItem> logicItemList = new ArrayList<>();
    for (int i = 0; i < oldList.size(); i++) {
      for (int j = 0; j < level.getItemAmountAvailable(i); j++) {
        logicItemList.add(
            new BacktrackingItem(oldList.get(i).getValue(),
                oldList.get(i).getWeight(),
                oldList.get(i).getName(), oldList.get(i).getImageIcon()));
      }
    }
    backtrackingTree = new backtrackingtree.BacktrackingTree(
        level.getCapacity(), logicItemList);
  }

  @Override
  public void addToRucksack(final int itemButtonIndex, final Level level) {
    boolean fromTrashToRucksack =
        level.getBacktrackingItemList().get(itemButtonIndex).getState().equals(
            BacktrackingItem.StateBacktracking.TRASH);
    if (this.backtrackingTree.addToRucksack(
        this.myLevel.getBacktrackingItemList().get(itemButtonIndex))) {
      this.tree.putInBag(backtrackingTree.getCurrentNode());
      lastDepth++;
      if (fromTrashToRucksack) {
        level.setInTrashAmountList(itemButtonIndex,
            level.getInTrashAmountList().get(itemButtonIndex) - 1);
      } else {
        level.setAvailableItemAmountList(itemButtonIndex,
            level.getItemAmountAvailable(itemButtonIndex) - 1);
      }
      level.setInRucksackAmountList(itemButtonIndex,
          level.getItemAmountInRucksack(itemButtonIndex) + 1);
      level.setCurrentValue(level.getCurrentValue()
          + level.getBacktrackingItemList().get(itemButtonIndex).getValue());
      level.setCurrentWeight(level.getCurrentWeight()
          + level.getBacktrackingItemList().get(itemButtonIndex).getWeight());
      level.getBacktrackingItemList().get(itemButtonIndex).setState(
          BacktrackingItem.StateBacktracking.RUCKSACK);
    }
  }

  @Override
  public void addToTrash(final int itemButtonIndex, final Level level) {
    boolean fromRucksackToTrash = level.getBacktrackingItemList().get(itemButtonIndex)
        .getState().equals(BacktrackingItem.StateBacktracking.RUCKSACK);
    ArrayList<Integer> fromTrashToRucksackSubsequent = new ArrayList<>();
    for (int i = itemButtonIndex + 1; i < level.getBacktrackingItemList().size(); i++) {
      if (level.getBacktrackingItemList().get(i).getState().equals(
          BacktrackingItem.StateBacktracking.TRASH)) {
        fromTrashToRucksackSubsequent.add(0);
      } else if (level.getBacktrackingItemList().get(i).getState().equals(
          BacktrackingItem.StateBacktracking.RUCKSACK)) {
        fromTrashToRucksackSubsequent.add(1);
      } else {
        fromTrashToRucksackSubsequent.add(2);
      }
    }
    if (this.backtrackingTree.addToTrash(
        this.myLevel.getBacktrackingItemList().get(itemButtonIndex))) {
      ImageIcon crossedOut = backtrackingTree.getCurrentNode().getItem().getImageIcon();
      BufferedImage crossedOutBuffered = new BufferedImage(
          crossedOut.getIconWidth(),
          crossedOut.getIconHeight(),
          BufferedImage.TYPE_INT_RGB);
      Graphics g = crossedOutBuffered.createGraphics();
      crossedOut.paintIcon(null, g, 0, 0);
      g.dispose();
      ImageIcon not = new ImageIcon("src/main/resources/icons/Not.png");
      not = new ImageIcon(not.getImage()
          .getScaledInstance(AppData.ICON_SIZE, AppData.ICON_SIZE, Image.SCALE_SMOOTH));
      BufferedImage notBuffered = new BufferedImage(
          not.getIconWidth(),
          not.getIconHeight(),
          BufferedImage.TYPE_INT_RGB);
      Graphics g2 = notBuffered.createGraphics();
      not.paintIcon(null, g2, 0, 0);
      g2.dispose();
      for (int y = 0; y < crossedOutBuffered.getHeight(); y++) {
        for (int x = 0; x < crossedOutBuffered.getWidth(); x++) {
          int clr;
          if (notBuffered.getRGB(x, y) == -16777216) {
            clr = crossedOutBuffered.getRGB(x, y);
          } else {
            clr = notBuffered.getRGB(x, y);
          }
          crossedOutBuffered.setRGB(x, y, clr);
        }
      }
      tree.putInTrash(backtrackingTree.getCurrentNode());
      if (fromRucksackToTrash) {
        level.setInRucksackAmountList(itemButtonIndex,
            level.getItemAmountInRucksack(itemButtonIndex) - 1);
        level.setCurrentWeight(level.getCurrentWeight()
            - level.getBacktrackingItemList().get(itemButtonIndex).getWeight());
        level.setCurrentValue(level.getCurrentValue()
            - level.getBacktrackingItemList().get(itemButtonIndex).getValue());
      } else {
        level.setAvailableItemAmountList(itemButtonIndex,
            level.getItemAmountAvailable(itemButtonIndex) - 1);
      }
      for (int i = itemButtonIndex + 1; i < level.getBacktrackingItemList().size(); i++) {
        if (fromTrashToRucksackSubsequent.get(i - itemButtonIndex - 1) == 0) {
          level.setInTrashAmountList(i,
              level.getInTrashAmountList().get(i) - 1);
          level.setAvailableItemAmountList(i, level.getItemAmountAvailable(i) + 1);
          level.getBacktrackingItemList().get(i)
              .setState(BacktrackingItem.StateBacktracking.AVAILABLE);
        } else if (fromTrashToRucksackSubsequent.get(i - itemButtonIndex - 1) == 1) {
          level.setInRucksackAmountList(i,
              level.getItemAmountInRucksack(i) - 1);
          level.setAvailableItemAmountList(i, level.getItemAmountAvailable(i) + 1);
          level.getBacktrackingItemList().get(i)
              .setState(BacktrackingItem.StateBacktracking.AVAILABLE);
          level.setCurrentWeight(level.getCurrentWeight()
              - level.getBacktrackingItemList().get(i).getWeight());
          level.setCurrentValue(level.getCurrentValue()
              - level.getBacktrackingItemList().get(i).getValue());
        }
      }
      level.setInTrashAmountList(itemButtonIndex,
          level.getInTrashAmountList().get(itemButtonIndex) + 1);
      level.getBacktrackingItemList().get(itemButtonIndex).setState(
          BacktrackingItem.StateBacktracking.TRASH);
    }
  }

  @Override
  public void show() {
    tree.show();
  }

  @Override
  public String getSolution() {
    return null;
  }

  @Override
  public void resetLevel(final Level level, final int levelNumber) {
    level.resetLevel();
    tree.close();
    GuiManager.openLevel(new GuiLevelPageBacktracking(level), levelNumber);
  }
}
