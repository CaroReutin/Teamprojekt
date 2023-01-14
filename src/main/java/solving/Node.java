package solving;

import rucksack.Item;

public class Node {
  private Node leftChildren;
  private Node rightChildren;
  private int currentWeight;
  private int currentValue;
  private Item item;
  private boolean putInRucksack;


  public Node(int currentWeight, int currentValue, Item item, boolean putInRucksack) {
    this.currentValue = currentValue;
    this.currentWeight = currentWeight;
    this.item = item;
    this.putInRucksack = putInRucksack;
    leftChildren = null;
    rightChildren = null;
  }

  public void setLeftChildren(Node leftChildren) {
    this.leftChildren = leftChildren;
  }

  public void setRightChildren(Node rightChildren) {
    this.rightChildren = rightChildren;
  }

  public int getCurrentValue() {
    return currentValue;
  }

  public int getCurrentWeight() {
    return currentWeight;
  }

  public Node getLeftChildren() {
    return leftChildren;
  }

  public Node getRightChildren() {
    return rightChildren;
  }



  public boolean isPutInRucksack() {
    return putInRucksack;
  }
}
