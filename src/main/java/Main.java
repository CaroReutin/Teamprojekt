
import GUI_Level.GUIManager;
import Solving.UserDataManager;

public class Main{
    public static void main(String[] args) {

        UserDataManager.load();

        GUIManager guiManager = new GUIManager();
        guiManager.launch();


    }
}