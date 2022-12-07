import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;


public class UserDataManagerTest {
    private final String testSaveFolder = "./src/test/resources/saveData";
    private final String testSaveFile = testSaveFolder+"/userData.xml";


    @BeforeEach
    public void cleanUp(){
        UserDataManager.hardReset();
        File save = new File(testSaveFile);
        save.delete();
    }

    @Test
    public void makeNewSave(){
        UserDataManager.load(testSaveFolder);
        StringBuilder newScore = new StringBuilder();
        for (int i = 0; i < UserDataManager.LEVELAMOUNT; i++) {
            newScore.append(UserDataManager.getScore(i));
            newScore.append("|");
        }
        Assertions.assertEquals("0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|",newScore.toString());
    }

    @Test
    public void scoreEqualsLevel(){
        UserDataManager.load(testSaveFolder);
        StringBuilder newScore = new StringBuilder();
        for (int i = 0; i < UserDataManager.LEVELAMOUNT; i++) {
            UserDataManager.newHighScore(i,i);
            newScore.append(UserDataManager.getScore(i));
            newScore.append("|");
        }
        Assertions.assertEquals("0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|",newScore.toString());
        UserDataManager.save(testSaveFolder);
        UserDataManager.softReset();
        UserDataManager.load(testSaveFolder);
        newScore = new StringBuilder();
        for (int i = 0; i < UserDataManager.LEVELAMOUNT; i++) {
            newScore.append(UserDataManager.getScore(i));
            newScore.append("|");
        }
        Assertions.assertEquals("0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|",newScore.toString());
    }
}
