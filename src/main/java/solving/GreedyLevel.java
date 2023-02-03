package solving;

import java.io.File;
import rucksack.Level;


/**
 * The type Greedy level.
 */
public final class GreedyLevel {
  /**
   * do not make.
   */
  private GreedyLevel() {

  }

  /**
   * The Greedy level.
   */
  private static final Level[] LEVEL_GREEDY = new Level[7];

  /**
   * Initialize greedy.
   */
  public static void initializeGreedy() {
    // Greedy Level 1
    LEVEL_GREEDY[0] =
      AppData.loadLevel(new File("src/main/resources/level/Greedy1.zip"));

    // Greedy Level 2
    LEVEL_GREEDY[1] =
      AppData.loadLevel(new File("src/main/resources/level/Greedy2.zip"));

    //Greedy Level 3
    LEVEL_GREEDY[2] =
      AppData.loadLevel(new File("src/main/resources/level/Greedy3.zip"));


    //Greedy Level 4
    LEVEL_GREEDY[3] =
      AppData.loadLevel(new File("src/main/resources/level/Greedy4.zip"));


    //Greedy Level 5
    LEVEL_GREEDY[4] =
      AppData.loadLevel(new File("src/main/resources/level/Greedy5.zip"));


    //Greedy Level 6
    LEVEL_GREEDY[5] =
      AppData.loadLevel(new File("src/main/resources/level/Greedy6.zip"));


    //Greedy Level 7
    LEVEL_GREEDY[6] =
      AppData.loadLevel(new File("src/main/resources/level/Greedy7.zip"));

  }

  /**
   * Gets level greedy.
   *
   * @param level the level
   * @return the level greedy
   */
  public static Level getLevelGreedy(final int level) {
    return LEVEL_GREEDY[level];
  }


}
