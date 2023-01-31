package rucksack;

import java.io.Serializable;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Item that can be stolen.
 */
@XmlRootElement
public class Item implements Serializable {
  /**
   * the value of the item.
   */
  @XmlElement
  private final int value;
  /**
   * the weight of the item.
   */
  @XmlElement
  private final int weight;
  /**
   * the name of the item.
   */
  @XmlElement
  private final String name;

  /**
   * Instantiates a new Item.
   *
   * @param itemValue  the Value
   * @param itemWeight the Weight
   * @param itemName   the Name
   */
  public Item(final int itemValue, final int itemWeight,
              final String itemName) {
    this.value = itemValue;
    this.weight = itemWeight;
    this.name = itemName;
  }

  /**
   * Only for XML document.
   */
  private Item() {
    this.value = -1;
    this.weight = -1;
    this.name = null;
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
   * .
   *
   * @param depthRemaining .
   * @return "
   */
  public String getBacktrackingName(final int depthRemaining) {
    // TODO fix depth
    int spacesPerDepth = 4;
    assert name != null;
    StringBuilder res = new StringBuilder(name);
    int spacesRemaing = 10 + depthRemaining * spacesPerDepth - name.length();
    res.append(" ".repeat(Math.max(0, spacesRemaing)));
    return res.toString();
  }

  public ImageIcon getIcon() {
    return new ImageIcon(Objects.requireNonNull(getClass()
        .getResource("/stern.png")));
  }
}
