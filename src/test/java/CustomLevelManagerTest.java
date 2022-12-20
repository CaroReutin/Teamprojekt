import org.junit.jupiter.api.Test;
import rucksack.Item;
import rucksack.Level;
import solving.CustomLevelManager;

import java.util.ArrayList;

public class CustomLevelManagerTest {
  @Test
  public void zippedImageIsSameAsZipped(){
    //TODO
  }

  @Test
  public void saveAndLoadLevel(){
    //TODO
  }

  @Test
  public void zipAndUnzipLevel(){
    //TODO
  }

  @Test
  public void save(){
    ArrayList<Item> itemList = new ArrayList<>();
    itemList.add(new Item(3,4,"Test"));
    itemList.add(new Item(3,2,"Item"));
    itemList.add(new Item(5,6,"These should be generated from AppData and not with the Constructor"));
    ArrayList<Integer> itemAmountList = new ArrayList<>();
    itemAmountList.add(2);
    itemAmountList.add(5);
    itemAmountList.add(4);
    Level testLevel = new Level(itemList,itemAmountList,Level.Robber.BACKTRACKING_BANDIT,-1,20);
    String identifier = "test";
    String testResourcesPath = "./src/test/resources/customLevel";
    CustomLevelManager.save(testResourcesPath,identifier,testLevel);
  }
}
