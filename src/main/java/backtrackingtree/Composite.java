package backtrackingtree;

import java.util.ArrayList;
import rucksack.Item;


/**
 * this class is part of the design pattern composite and
 * represents the composite in that pattern.
 */
public class Composite extends Component {


  /**
   * the children of this node.
   */
  private final ArrayList<Component> children;

  /**
   * the maximum capacity of the rucksack.
   */
  private int capacity;

  /**
   * a list where every item from the level is saved + empty item.
   * The order is descending (absteigend).
   */
  private final ArrayList<Item> itemList;

  /**
   * the constructor for this  class.
   *
   * @param item           the item of the composite
   * @param weightBefore   the weight before the new one gets calculated
   * @param valueBefore    the value before the new one
   * @param maxCapacity    the maximum capacity of the bag
   * @param sortedItemList list of the items of the level
   * @param parent         parent of this node
   */
  public Composite(final Item item, final int weightBefore,
                   final int valueBefore, final int maxCapacity,
                   final ArrayList<Item> sortedItemList,
                   final Component parent) {
    super(item, weightBefore, valueBefore, maxCapacity, parent);

    children = new ArrayList<>(2);
    itemList = sortedItemList;
  }

  /**
   * method adds a child.
   *
   * @param childItem is added as next to this instance
   * @return true if child got added successfully
   */
  @Override
  public boolean add(final Item childItem) {
    if (isItemAllowed(childItem)) {
      if (isEmptyItem(childItem)) {
        children.add(0, new Composite(childItem, getCurrentWeight(),
                getCurrentValue(), getCapacity(), itemList, this));
      } else {
        children.add(1, new Composite(childItem, getCurrentWeight(),
                getCurrentValue(), getCapacity(), itemList, this));
      }
      System.out.println(childItem.getName() + " wurde hinzugefügt");
      return true;
    } else {
      System.out.println("Item darf nicht hinzugefügt werden");
      return false;
    }

  }

  private boolean isItemAllowed(final Item item) {
    // if you want to put an item from the rucksack to the bin
    if (isEmptyItem(item)) {
      return true;
    }


    // query in case that the item is not empty and is the next
    // more lightweight item after the item this instance
    int itemIndex = itemList.indexOf(item);
    if (itemIndex == itemList.indexOf(this.getItem()) + 1
            && capacity >= getCurrentWeight() + item.getWeight()) {
      return true;
    } else {
      System.out.println("Item macht Rucksack zu schwer oder"
              + "Item ist das nicht das schwerste Verfügbare");
      return false;
    }

  }

  private boolean isEmptyItem(final Item item) {
    return item.getValue() == 0 && item.getWeight() == 0;
  }
}
