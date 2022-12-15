
import GUI_Level.GUIManager;
import Solving.*;

public class Main{
    public static void main(String[] args) {
        AppData.initialize();
        UserDataManager.load();

        GUIManager guiManager = new GUIManager();
        guiManager.launch();
    }
}