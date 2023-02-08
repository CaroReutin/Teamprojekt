package solving;

import java.io.File;
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

  private static final int levelOne = 0;

  private static final int levelTwo = 1;

  private static final int levelThree = 2;

  private static final int levelFour = 3;

  private static final int levelFive = 4;

  private static final int levelSix = 5;

  private static final int levelSeven = 6;


  /**
   * The Backtracking level.
   */
  private static final Level[] LEVEL_BACKTRACKING = new Level[7];

  /**
   * Initialize backtracking.
   */
  public static void initializeBacktracking() {
    //Backtracking Level 1
    LEVEL_BACKTRACKING[levelOne] =
      AppData.loadLevel(new File("src/main/resources/Level/Backtracking1.zip"));

    //Backtracking Level 2
    LEVEL_BACKTRACKING[levelTwo] =
      AppData.loadLevel(new File("src/main/resources/Level/Backtracking2.zip"));

    //Backtracking Level 3
    LEVEL_BACKTRACKING[levelThree] =
      AppData.loadLevel(new File("src/main/resources/Level/Backtracking3.zip"));

    //Backtracking Level 4
    LEVEL_BACKTRACKING[levelFour] =
      AppData.loadLevel(new File("src/main/resources/Level/Backtracking4.zip"));

    //Backtracking Level 5
    LEVEL_BACKTRACKING[levelFive] =
      AppData.loadLevel(new File("src/main/resources/Level/Backtracking5.zip"));

    //Backtracking Level 6
    LEVEL_BACKTRACKING[levelSix] =
      AppData.loadLevel(new File("src/main/resources/Level/Backtracking6.zip"));

    //Backtracking Level 7
    LEVEL_BACKTRACKING[levelSeven] =
     AppData.loadLevel(new File("src/main/resources/Level/Backtracking7.zip"));
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
