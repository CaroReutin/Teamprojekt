package rucksack;

public class BacktrackingItem extends Item {
  /**
   * state where the backtracking item is
   */
  private StateBacktracking state;

  /**
   * the states a backtracking item can be in
   */
  public enum StateBacktracking {
    /**
     * if item is in the trash bin
     */
    TRASH,
    /**
     * if item got moved into the rucksack
     */
    RUCKSACK,
    /**
     * if item is not in rucksack nor trash and is available (default)
     */
    AVAILABLE
  }

  /**
   * Instantiates a new Backtracking Item.
   *
   * @param value  the value
   * @param weight the weight
   * @param name   the name
   */
  public BacktrackingItem(final int value,
                          final int weight, final String name) {
    super(value, weight, name);
    this.state = StateBacktracking.AVAILABLE;
  }

  /**
   * sets the state of this item+
   *
   * @return said state
   */
  public StateBacktracking getState() {
    return state;
  }

  /**
   * sets a state for this item
   *
   * @param state said state
   */
  public void setState(StateBacktracking state) {
    this.state = state;
  }
}
