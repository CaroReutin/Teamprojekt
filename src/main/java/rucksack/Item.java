package rucksack;

/**
 * Item that can be stolen.
 */
public class Item {
  /**
   * the value of the item.
   */
  private int value;
  /**
   * the weight of the item.
   */
  private int weight;
  /**
   * the name of the item.
   */
  private String name;

  /**
   * Instantiates a new Item.
   *
   * @param value  the value
   * @param weight the weight
   * @param name   the name
   */
  public Item(int value, int weight, String name) {
    this.value = value;
    this.weight = weight;
    this.name = name;
  }

  /**
   * Gets value.
   *
   * @return the value
   */
  public int getValue() {
    return value;
  }


  /**
   * Gets weight.
   *
   * @return the weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

}
