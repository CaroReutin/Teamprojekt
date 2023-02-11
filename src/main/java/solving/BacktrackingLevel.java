package solving;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
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
    try {
      InputStream is =
          AppData.class.getClassLoader().getResourceAsStream("Level/Backtracking1.zip");
      File file = File.createTempFile("backtracking", "zip");
      FileUtils.copyInputStreamToFile(is, file);
      //Backtracking Level 1
      LEVEL_BACKTRACKING[levelOne] =
          AppData.loadLevel(file);

      InputStream is2 =
          AppData.class.getClassLoader().getResourceAsStream("Level/Backtracking2.zip");
      File file2 = File.createTempFile("backtracking2", "zip");
      FileUtils.copyInputStreamToFile(is2, file2);
      //Backtracking Level 2
      LEVEL_BACKTRACKING[levelTwo] =
          AppData.loadLevel(file2);

      InputStream is3 =
          AppData.class.getClassLoader().getResourceAsStream("Level/Backtracking3.zip");
      File file3 = File.createTempFile("backtracking3", "zip");
      FileUtils.copyInputStreamToFile(is3, file3);
      //Backtracking Level 3
      LEVEL_BACKTRACKING[levelThree] =
          AppData.loadLevel(file3);

      InputStream is4 =
          AppData.class.getClassLoader().getResourceAsStream("Level/Backtracking4.zip");
      File file4 = File.createTempFile("backtracking4", "zip");
      FileUtils.copyInputStreamToFile(is4, file4);
      //Backtracking Level 4
      LEVEL_BACKTRACKING[levelFour] =
          AppData.loadLevel(file4);

      InputStream is5 =
          AppData.class.getClassLoader().getResourceAsStream("Level/Backtracking5.zip");
      File file5 = File.createTempFile("backtracking5", "zip");
      FileUtils.copyInputStreamToFile(is5, file5);
      //Backtracking Level 5
      LEVEL_BACKTRACKING[levelFive] =
          AppData.loadLevel(file5);

      InputStream is6 =
          AppData.class.getClassLoader().getResourceAsStream("Level/Backtracking6.zip");
      File file6 = File.createTempFile("backtracking6", "zip");
      FileUtils.copyInputStreamToFile(is6, file6);
      //Backtracking Level 6
      LEVEL_BACKTRACKING[levelSix] =
          AppData.loadLevel(file6);

      InputStream is7 =
          AppData.class.getClassLoader().getResourceAsStream("Level/Backtracking7.zip");
      File file7 = File.createTempFile("backtracking7", "zip");
      FileUtils.copyInputStreamToFile(is7, file7);
      //Backtracking Level 7
      LEVEL_BACKTRACKING[levelSeven] =
          AppData.loadLevel(file7);
    } catch (IOException e) {
      e.printStackTrace();
    }
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
