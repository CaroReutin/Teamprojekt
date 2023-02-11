package rucksack;

import java.io.Serializable;
import java.util.Objects;
import javax.swing.ImageIcon;
import com.thoughtworks.xstream.annotations.*;

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

  private ImageIcon imageIcon;

  /**
   * Instantiates a new Item.
   *
   * @param itemValue  the Value
   * @param itemWeight the Weight
   * @param itemName   the Name
   */
  public Item(final int itemValue, final int itemWeight,
              final String itemName, final ImageIcon imageIcon) {
    this.value = itemValue;
    this.weight = itemWeight;
    this.name = itemName;
    this.imageIcon = imageIcon;
  }

  /**
   * Only for XML document.
   */
  private Item() {
    this.value = -1;
    this.weight = -1;
    this.name = null;
    this.imageIcon = null;
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

  public ImageIcon getImageIcon() {
    return imageIcon;
  }

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

  @Override
  public int hashCode() {
    return Objects.hash(value, weight, name);
  }

  /**
   * .
   *
   * @param depthRemaining .
   * @return "
   */
  public String getBacktrackingName(final int depthRemaining) {
    int spacesPerDepth = 4;
    assert name != null;
    StringBuilder res = new StringBuilder(name);
    int spacesRemaing = 10 + depthRemaining * spacesPerDepth - name.length();
    res.append(" ".repeat(Math.max(0, spacesRemaing)));
    return res.toString();
  }

  public ImageIcon getIcon() {
    return new ImageIcon(Objects.requireNonNull(getClass()
        .getResource("icons/DefaultBox.png")));
  }

}
