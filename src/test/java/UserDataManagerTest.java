import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;


public class UserDataManagerTest {
    private final String testSaveFolder = "./src/test/resources/saveData";
    private final String testSaveFile = testSaveFolder+"/userData.xml";


    @BeforeEach
    public void cleanUp(){
        UserDataManager.reset();
        File save = new File(testSaveFile);
        save.delete();
    }

    @Test
    public void makeNewSave(){
        UserDataManager.load(testSaveFolder);
        Assertions.assertEquals("0|0|0|0|0|0|0|0|0|0|0|0|0|0|0",UserDataManager.dataToString());
    }

    @Test
    public void scoreEqualsLevel(){
        UserDataManager.load(testSaveFolder);
        for (int i = 0; i < UserDataManager.LEVELAMOUNT; i++) {
            UserDataManager.newHighScore(i,i);
        }
        Assertions.assertEquals("0|1|2|3|4|5|6|7|8|9|10|11|12|13|14",UserDataManager.dataToString());
        UserDataManager.save(testSaveFolder);
        UserDataManager.reset();
        UserDataManager.load(testSaveFolder);
        Assertions.assertEquals("0|1|2|3|4|5|6|7|8|9|10|11|12|13|14",UserDataManager.dataToString());
    }
}
