import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rucksack.Item;
import rucksack.Level;
import solving.CustomLevelManager;

public class CustomLevelManagerTest {
  @BeforeAll
  public static void deleteOldFiles() {
    File customlevelFolder = new File("./src/test/resources/customLevel");
    try {
      FileUtils.deleteDirectory(customlevelFolder);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    customlevelFolder.mkdirs();
  }

  @BeforeEach
  public void deleteTempFolder() {
    File customlevelFolder = new File("./src/test/resources/customLevel/temp");
    try {
      FileUtils.deleteDirectory(customlevelFolder);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    customlevelFolder.mkdirs();
  }

  @Test
  public void save() {
    ArrayList<Item> itemList = new ArrayList<>();
    itemList.add(new Item(3, 4, "Test"));
    itemList.add(new Item(3, 2, "Item"));
    itemList.add(new Item(5, 6, "Three"));
    ArrayList<Integer> itemAmountList = new ArrayList<>();
    itemAmountList.add(2);
    itemAmountList.add(5);
    itemAmountList.add(4);
    Level testLevel = new Level(itemList, itemAmountList, Level.Robber.BACKTRACKING_BANDIT, -1, 20);
    String identifier = "save";
    String testResourcesPath = "./src/test/resources/customLevel";
    ArrayList<Integer> validItems = new ArrayList<>();
    validItems.add(0);
    validItems.add(1);
    validItems.add(2);
    CustomLevelManager.save(testResourcesPath+"/temp",testResourcesPath, identifier, testLevel, validItems);
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
    CustomLevelManager.save(testResourcesPath+"/temp",testResourcesPath, identifier, testLevel, validItems);
  }

  @Test
  public void zipTest1() {
    ArrayList<Item> itemList = new ArrayList<>();
    itemList.add(new Item(4, 3, "Guter Gegenstand"));
    itemList.add(new Item(5, 3, "Besserer Gegenstand"));
    ArrayList<Integer> itemAmountList = new ArrayList<>();
    itemAmountList.add(1);
    itemAmountList.add(1);
    String testResourcesPath = "./src/test/resources/customLevel";
    try {
      ImageIcon stern = new ImageIcon("./src/test/resources/pngs/stern.png");
      File destination = new File(testResourcesPath + "/temp/picture1.png");
      if (destination.exists()) {
        FileUtils.delete(destination);
      }
      FileUtils.copyFile(new File("./src/test/resources/pngs/stern.png"), destination);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    ArrayList<Integer> validItems = new ArrayList<>();
    validItems.add(0);
    validItems.add(1);
    Level testLevel = new Level(itemList, itemAmountList, Level.Robber.BACKTRACKING_BANDIT, -1, 4);
    String identifier = "Levelbezeichner";
    CustomLevelManager.save(testResourcesPath+"/temp",testResourcesPath, identifier, testLevel, validItems);
  }
}
