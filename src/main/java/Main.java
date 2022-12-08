public class Main{

    public static void main(String[] args) {
        GUIManager gui = new GUIManager();
        UserDataManager.load();
        gui.launch();
    }
}