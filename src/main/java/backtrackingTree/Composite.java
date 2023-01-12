package backtrackingTree;

import rucksack.Item;

import java.util.ArrayList;

/**
 * this class is part of the design pattern composite and represents the composit in that pattern.
 */
public class Composite extends Component {


  /**
   * the children of this node
   */
  private ArrayList<Component> children;

  /**
   * the maximum capacity of the rucksack
   */
  private int capacity;

  /**
   * a list where every item from the level is saved + empty item.
   * The order is descending (absteigend)
   */
  private final ArrayList<Item> itemList;


  public Composite(Item item, int weightBefore, int valueBefore, int capacity, ArrayList<Item> sortedItemList, Component parent) {
    super(item, weightBefore, valueBefore, capacity, parent);

    children = new ArrayList<>(2);
    itemList = sortedItemList;
  }

  @Override
  public boolean add(Item item) {
    if (isItemAllowed(item)) {
      if (isEmptyItem(item)) {
        children.add(0, new Composite(item, getCurrentWeight(), getCurrentValue(), getCapacity(), itemList, this));
      } else {
        children.add(1, new Composite(item, getCurrentWeight(), getCurrentValue(), getCapacity(), itemList, this));
      }
      System.out.println(item.getName() + " wurde hinzugefuegt");
      return true;
    } else {
      System.out.println("Item darf nicht hinzugefügt werden");
      return false;
    }

  }

  @Override
  public boolean delete(Component component) {
    return false;
  }


  private boolean isItemAllowed(Item item) {
    // if you want to put an item from the rucksack to the bin
    if (isEmptyItem(item)) {
      return true;
    }


    // query in case that the item is not empty and is the next
    // lightweighted item after the item this instance
    int itemIndex = itemList.indexOf(item);
    if (itemIndex == itemList.indexOf(this.getItem()) + 1 && capacity >= getCurrentWeight() + item.getWeight()) {
      return true;
    } else {
      System.out.println("Item macht Rucksack zu schwer oder Item ist das nicht das schwerste Verfuegbare");
      return false;
    }

  }

  private boolean isEmptyItem(Item item) {
    return item.getValue() == 0 && item.getWeight() == 0;
  }
}
