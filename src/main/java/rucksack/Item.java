package rucksack;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Item that can be stolen.
 */
@XmlRootElement
public class Item implements Serializable {
  /**
   * the value of the item.
   */
  @XmlElement
  private int value;
  /**
   * the weight of the item.
   */
  @XmlElement
  private int weight;
  /**
   * the name of the item.
   */
  @XmlElement
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

}
