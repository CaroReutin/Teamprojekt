import gui.level.GuiManager;
import rucksack.BacktrackingItem;
import rucksack.Item;
import rucksack.Level;
import solving.AppData;
import solving.UserDataManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Main.
 */
public final class Main {
  /**
   * do not make.
   */
  private Main() {

  }


  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(final String[] args) throws IOException, JAXBException {

    BacktrackingItem testItem = new BacktrackingItem(2, 2, "test");
    ArrayList <Item> testList = new ArrayList<Item>();
    ArrayList <Integer> testIntList = new ArrayList<Integer>();
    testList.add(testItem);
    testIntList.add(3);
    Level testLevel = new Level(testList, testIntList, -1,4);

      String levelPath = "C:/Users/miken/Documents/Studium/Teamprojekt/target/testlLevel.xml";
      FileOutputStream fos = new FileOutputStream(levelPath);
      JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
      Marshaller marsh = jaxbContext.createMarshaller();

      marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      marsh.marshal(testLevel, fos);

      fos.close();


    AppData.initialize();
    AppData.initializeBeginningLevel();
    AppData.initializeItems();
    UserDataManager.load();

    GuiManager guiManager = new GuiManager();
    guiManager.launch();
  }
}
