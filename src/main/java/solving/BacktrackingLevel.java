package solving;

import java.io.File;
import java.util.ArrayList;
import rucksack.Item;
import rucksack.Level;

/**
 * The type Backtracking level.
 */
public final class BacktrackingLevel {
  /**
   * do not make.
   */
  private BacktrackingLevel() {

  }

  /**
   * The Backtracking level.
   */
  private static final Level[] LEVEL_BACKTRACKING = new Level[7];

  /**
   * Initialize backtracking.
   */
  public static void initializeBacktracking() {
    //Backtracking Level 1

    LEVEL_BACKTRACKING[0] = AppData.loadLevel(new File("src/main/resources/level/Backtracking1.zip"));


    //Backtracking Level 2

    LEVEL_BACKTRACKING[1] =
        AppData.loadLevel(new File("src/main/resources/level/Backtracking2.zip"));


    //Backtracking Level 3

    LEVEL_BACKTRACKING[2] =
        AppData.loadLevel(new File("src/main/resources/level/Backtracking3.zip"));

    //Backtracking Level 4

    LEVEL_BACKTRACKING[3] =
        AppData.loadLevel(new File("src/main/resources/level/Backtracking4.zip"));

    //Backtracking Level 5

    LEVEL_BACKTRACKING[4] =
        AppData.loadLevel(new File("src/main/resources/level/Backtracking5.zip"));

    //Backtracking Level 6

    LEVEL_BACKTRACKING[5] =
        AppData.loadLevel(new File("src/main/resources/level/Backtracking6.zip"));

    //Backtracking Level 7

    //LEVEL_BACKTRACKING[6] = AppData.loadLevel(new File("src/main/resources/level/Backtracking7.zip"));
  }

  /**
   * Gets level backtracking.
   *
   * @param level the level
   * @return the level backtracking
   */
  public static Level getLevelBacktracking(final int level) {
    return LEVEL_BACKTRACKING[level];
  }

}
