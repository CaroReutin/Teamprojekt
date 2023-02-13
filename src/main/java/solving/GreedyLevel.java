package solving;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import rucksack.Level;

/**
 * The type Greedy level.
 */
public final class GreedyLevel {

  /**
   * the level 7.
   */
  private static final int LEVEL_SEVEN = 7;
  /**
   * the level 6.
   */
  private static final int LEVEL_SIX = 6;
  /**
   * the level 5.
   */
  private static final int LEVEL_FIVE = 5;
  /**
   * the level4.
   */
  private static final int LEVEL_FOUR = 4;
  /**
   * the level 3.
   */
  private static final int LEVEL_THREE = 3;

  /**
   * do not make.
   */
  private GreedyLevel() {

  }

  /**
   * The Greedy level.
   */
  private static final Level[] LEVEL_GREEDY = new Level[LEVEL_SEVEN];

  /**
   * Initialize greedy.
   */
  public static void initializeGreedy() {
    try {
      InputStream is =
          AppData.class.getClassLoader().getResourceAsStream(
          "Level/Greedy1.zip");
      File file = File.createTempFile("Greedy1", "zip");
      FileUtils.copyInputStreamToFile(is, file);
      //Backtracking Level 1
      LEVEL_GREEDY[0] =
        AppData.loadLevel(file);

      InputStream is2 =
          AppData.class.getClassLoader().getResourceAsStream(
          "Level/Greedy2.zip");
      File file2 = File.createTempFile("Greedy2", "zip");
      FileUtils.copyInputStreamToFile(is2, file2);
      //Backtracking Level 2
      LEVEL_GREEDY[1] =
        AppData.loadLevel(file2);

      InputStream is3 =
          AppData.class.getClassLoader().getResourceAsStream(
          "Level/Greedy3.zip");
      File file3 = File.createTempFile("Greedy3", "zip");
      FileUtils.copyInputStreamToFile(is3, file3);
      //Backtracking Level 3
      LEVEL_GREEDY[2] =
        AppData.loadLevel(file3);

      InputStream is4 =
            AppData.class.getClassLoader().getResourceAsStream(
          "Level/Greedy4.zip");
      File file4 = File.createTempFile("Greedy4", "zip");
      FileUtils.copyInputStreamToFile(is4, file4);
      //Backtracking Level 4
      LEVEL_GREEDY[LEVEL_THREE] =
        AppData.loadLevel(file4);

      InputStream is5 =
          AppData.class.getClassLoader().getResourceAsStream(
          "Level/Greedy5.zip");
      File file5 = File.createTempFile("Greedy5", "zip");
      FileUtils.copyInputStreamToFile(is5, file5);
      //Backtracking Level 5
      LEVEL_GREEDY[LEVEL_FOUR] =
        AppData.loadLevel(file5);

      InputStream is6 =
          AppData.class.getClassLoader().getResourceAsStream(
          "Level/Greedy6.zip");
      File file6 = File.createTempFile("Greedy6", "zip");
      FileUtils.copyInputStreamToFile(is6, file6);
      //Backtracking Level 6
      LEVEL_GREEDY[LEVEL_FIVE] =
        AppData.loadLevel(file6);

      InputStream is7 =
          AppData.class.getClassLoader().getResourceAsStream(
          "Level/Greedy7.zip");
      File file7 = File.createTempFile("Greedy7", "zip");
      FileUtils.copyInputStreamToFile(is7, file7);
      //Backtracking Level 7
      LEVEL_GREEDY[LEVEL_SIX] =
        AppData.loadLevel(file7);
    } catch (IOException e) {
      e.printStackTrace();
    }
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
