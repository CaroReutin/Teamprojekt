import java.util.ArrayList;

public class GUIManager {
    // for now i'll put it here, not sure where it is most appropriate to put
    private static ArrayList<String> passwords = new ArrayList<>();
    public static ArrayList<String> getPasswords(){
        return passwords;
    }
    // see comment above

    private void innit() {
        passwords.add("tipTop");
    }

    private LevelManager lm = new LevelManager();
    GUIFrontpage guiFrontpage = new GUIFrontpage();

    public void launch(){
        innit();
        guiFrontpage.openProgrammWindow();
    }
}