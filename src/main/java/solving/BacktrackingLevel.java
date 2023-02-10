package solving;

import java.io.File;
import java.util.Objects;

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
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Backtracking1.zip")).getPath()));

    //Backtracking Level 2
    LEVEL_BACKTRACKING[levelTwo] =
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Backtracking2.zip")).getPath()));

    //Backtracking Level 3
    LEVEL_BACKTRACKING[levelThree] =
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Backtracking3.zip")).getPath()));

    //Backtracking Level 4
    LEVEL_BACKTRACKING[levelFour] =
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Backtracking4.zip")).getPath()));

    //Backtracking Level 5
    LEVEL_BACKTRACKING[levelFive] =
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Backtracking5.zip")).getPath()));

    //Backtracking Level 6
    LEVEL_BACKTRACKING[levelSix] =
      AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
        .getResource("Level/Backtracking6.zip")).getPath()));

    //Backtracking Level 7
    LEVEL_BACKTRACKING[levelSeven] =
     AppData.loadLevel(new File(Objects.requireNonNull(AppData.class.getClassLoader()
       .getResource("Level/Backtracking7.zip")).getPath()));
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
