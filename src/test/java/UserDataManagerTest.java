import solving.AppData;
import solving.UserDataManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;


public class UserDataManagerTest {
  private final String testSaveFolder = "./src/test/resources/saveData";
  private final String testSaveFile = testSaveFolder + "/userData.xml";


  @BeforeEach
  public void cleanUp() {
    UserDataManager.reset();
    File save = new File(testSaveFile);
    boolean ignoreResult = save.delete();
  }

  @Test
  public void makeNewSave() {
    UserDataManager.load(testSaveFolder);
    Assertions.assertEquals("0|0|0|0|0|0|0|0|0|0|0|0|0|0|0", UserDataManager.dataToString());
  }

  @Test
  public void scoreEqualsLevel() {
    UserDataManager.load(testSaveFolder);
    for (int i = 0; i < AppData.LEVELAMOUNT; i++) {
      UserDataManager.newHighScore(i, i);
    }
    Assertions.assertEquals("0|1|2|3|4|5|6|7|8|9|10|11|12|13|14", UserDataManager.dataToString());
    UserDataManager.save(testSaveFolder);
    UserDataManager.reset();
    UserDataManager.load(testSaveFolder);
    Assertions.assertEquals("0|1|2|3|4|5|6|7|8|9|10|11|12|13|14", UserDataManager.dataToString());
    for (int i = 0; i < AppData.LEVELAMOUNT; i++) {
      Assertions.assertEquals(UserDataManager.getScore(i), i);
    }
  }

  @Test
  public void edgeCases() {
    UserDataManager.load(testSaveFolder);
    Assertions.assertThrows(IndexOutOfBoundsException.class,
        () -> UserDataManager.newHighScore(-1, 0));
    Assertions.assertThrows(IndexOutOfBoundsException.class,
        () -> UserDataManager.newHighScore(15, 0));
    UserDataManager.newHighScore(2, Integer.MIN_VALUE);
    UserDataManager.newHighScore(3, Integer.MAX_VALUE);
    Assertions.assertEquals("0|0|-2147483648|2147483647|0|0|0|0|0|0|0|0|0|0|0",
        UserDataManager.dataToString());
    UserDataManager.save(testSaveFolder);
    UserDataManager.reset();
    UserDataManager.load(testSaveFolder);
    Assertions.assertEquals("0|0|-2147483648|2147483647|0|0|0|0|0|0|0|0|0|0|0",
        UserDataManager.dataToString());
  }
}
