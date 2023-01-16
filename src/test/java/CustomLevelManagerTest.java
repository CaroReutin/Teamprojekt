import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.ImageIcon;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rucksack.Item;
import rucksack.Level;
import solving.CustomLevelManager;

public class CustomLevelManagerTest {
  @BeforeAll
  public static void deleteOldFiles(){
    File customlevelFolder = new File("./src/test/resources/customLevel");
    for(File file: Objects.requireNonNull(customlevelFolder.listFiles()))
      if (!file.isDirectory())
        file.delete();
    new File("./src/test/resources/customLevel/temp").delete();
  }

  @Test
  public void zippedImageIsSameAsUnzipped(){
    ArrayList<Item> itemList = new ArrayList<>();
    itemList.add(new Item(3,4,"Test"));
    itemList.add(new Item(3,2,"Item"));
    itemList.add(new Item(5,6,"Three"));
    ArrayList<Integer> itemAmountList = new ArrayList<>();
    itemAmountList.add(2);
    itemAmountList.add(5);
    itemAmountList.add(4);
    Level testLevel = new Level(itemList,itemAmountList,Level.Robber.BACKTRACKING_BANDIT,-1,20);
    String identifier = "zippedImageIsSameAsUnzipped";
    String testResourcesPath = "./src/test/resources/customLevel";
    try {
      ImageIcon rucksack = new ImageIcon("./src/test/resources/pngs/rucksack.png");
      File destination = new File(testResourcesPath + "/temp/picture2.png");
      if (destination.exists()) {
        FileUtils.delete(destination);
      }
      FileUtils.copyFile(new File("./src/test/resources/pngs/rucksack.png"), destination);
      ImageIcon stern = new ImageIcon("./src/test/resources/pngs/stern.png");
      destination = new File(testResourcesPath + "/temp/picture0.png");
      if (destination.exists()) {
        FileUtils.delete(destination);
      }
      FileUtils.copyFile(new File("./src/test/resources/pngs/stern.png"), destination);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    CustomLevelManager.save(testResourcesPath,identifier,testLevel);
    // TODO unzip "./src/test/resources/customLevel/"+ identifier + ".zip"
    // TODO compare Images
  }

  @Test
  public void saveAndLoadLevel(){
    ArrayList<Item> itemList = new ArrayList<>();
    itemList.add(new Item(3,4,"Test"));
    itemList.add(new Item(3,2,"Item"));
    itemList.add(new Item(5,6,"Three"));
    ArrayList<Integer> itemAmountList = new ArrayList<>();
    itemAmountList.add(2);
    itemAmountList.add(5);
    itemAmountList.add(4);
    Level testLevel = new Level(itemList,itemAmountList,Level.Robber.BACKTRACKING_BANDIT,-1,20);
    String identifier = "saveAndLoadLevel";
    String testResourcesPath = "./src/test/resources/customLevel";
    CustomLevelManager.save(testResourcesPath,identifier,testLevel);
    // Save with pictures
    try {
      ImageIcon rucksack = new ImageIcon("./src/test/resources/pngs/rucksack.png");
      File destination = new File(testResourcesPath + "/temp/picture0.png");
      if (destination.exists()) {
        FileUtils.delete(destination);
      }
      FileUtils.copyFile(new File("./src/test/resources/pngs/rucksack.png"), destination);
      ImageIcon stern = new ImageIcon("./src/test/resources/pngs/stern.png");
      destination = new File(testResourcesPath + "/temp/picture2.png");
      if (destination.exists()) {
        FileUtils.delete(destination);
      }
      FileUtils.copyFile(new File("./src/test/resources/pngs/stern.png"), destination);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    CustomLevelManager.save(testResourcesPath,identifier,testLevel);
    // TODO CustomLevelManager.load(...)
  }

  @Test
  public void save(){
    ArrayList<Item> itemList = new ArrayList<>();
    itemList.add(new Item(3,4,"Test"));
    itemList.add(new Item(3,2,"Item"));
    itemList.add(new Item(5,6,"Three"));
    ArrayList<Integer> itemAmountList = new ArrayList<>();
    itemAmountList.add(2);
    itemAmountList.add(5);
    itemAmountList.add(4);
    Level testLevel = new Level(itemList,itemAmountList,Level.Robber.BACKTRACKING_BANDIT,-1,20);
    String identifier = "save";
    String testResourcesPath = "./src/test/resources/customLevel";
    CustomLevelManager.save(testResourcesPath,identifier,testLevel);
    // Save with pictures
    try {
      ImageIcon rucksack = new ImageIcon("./src/test/resources/pngs/rucksack.png");
      File destination = new File(testResourcesPath + "/temp/picture0.png");
      if (destination.exists()) {
        FileUtils.delete(destination);
      }
      FileUtils.copyFile(new File("./src/test/resources/pngs/rucksack.png"), destination);
      ImageIcon stern = new ImageIcon("./src/test/resources/pngs/stern.png");
      destination = new File(testResourcesPath + "/temp/picture2.png");
      if (destination.exists()) {
        FileUtils.delete(destination);
      }
      FileUtils.copyFile(new File("./src/test/resources/pngs/stern.png"), destination);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    CustomLevelManager.save(testResourcesPath,identifier,testLevel);
  }
}
