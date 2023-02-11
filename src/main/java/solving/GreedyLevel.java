package solving;

import java.io.File;
import java.util.Objects;

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
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Greedy1.zip")).getPath()));

    // Greedy Level 2
    LEVEL_GREEDY[1] =
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Greedy2.zip")).getPath()));

    //Greedy Level 3
    LEVEL_GREEDY[2] =
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Greedy3.zip")).getPath()));


    //Greedy Level 4
    LEVEL_GREEDY[3] =
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Greedy4.zip")).getPath()));


    //Greedy Level 5
    LEVEL_GREEDY[4] =
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Greedy5.zip")).getPath()));


    //Greedy Level 6
    LEVEL_GREEDY[5] =
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Greedy6.zip")).getPath()));


    //Greedy Level 7
    LEVEL_GREEDY[6] =
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Greedy7.zip")).getPath()));

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
