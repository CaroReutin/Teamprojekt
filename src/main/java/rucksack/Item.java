package rucksack;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import java.util.Objects;
import javax.swing.ImageIcon;

/**
 * Item that can be stolen.
 */
@XStreamAlias("Item")
public class Item implements Serializable {
  /**
   * the value of the item.
   */
  @XStreamAlias("Value")
  private final int value;
  /**
   * the weight of the item.
   */
  @XStreamAlias("Weight")
  private final int weight;
  /**
   * the name of the item.
   */
  @XStreamAlias("Name")
  private final String name;

  /**
   * The icon portraying the item.
   */
  private final ImageIcon imageIcon;

  /**
   * Instantiates a new item.
   *
   * @param itemValue  the value
   * @param itemWeight the weight
   * @param itemName   the name
   * @param myImageIcon the icon
   */
  public Item(final int itemValue, final int itemWeight,
              final String itemName, final ImageIcon myImageIcon) {
    this.value = itemValue;
    this.weight = itemWeight;
    this.name = itemName;
    this.imageIcon = myImageIcon;
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

  /**
   * Prints the relevant info of the Item.
   *
   * @return returns name , Weight: weight , Value: value in 3 Lines
   */
  @Override
  public String toString() {
    return name + "\nWeight: " + weight + "\nValue: " + value;
  }

  /**
   * Method for getting the image icon of an item.
   *
   * @return the icon
   */
  public ImageIcon getImageIcon() {
    return imageIcon;
  }

  /**
   * Method for checking whether two items are equal.
   *
   * @param o the object to compare
   * @return the boolean value whether the items are equal
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return value == item.value && weight
        == item.weight && Objects.equals(name, item.name);
  }

  /**
   * Method for creating the hash code of an item.
   * The hash value is determined by the attributes value, weight and name.
   *
   * @return the hash code of an item
   */
  @Override
  public int hashCode() {
    return Objects.hash(value, weight, name);
  }

}
