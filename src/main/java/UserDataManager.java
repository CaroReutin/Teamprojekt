import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class UserDataManager {
    private static UserData data = new UserData();

    public static final int LEVELAMOUNT = 15;

    public static void newUser(){
        data.newUser();
    }

    /**
     * Tries to get default save location based on OS then calls save(String saveFolder) with that location.
     * Always Overwrites old save file if it exists in default save location.
     */
    public static void save() {
        if (System.getProperty("os.name").contains("Windows")) {
            String appdataPath = System.getenv("APPDATA").replaceAll("\\\\", "/");
            String saveFolder = appdataPath + "/Optimal Heist/savedata";
            save(saveFolder);
        }
    }

    /**
     * Always Overwrites old save file if it exists in saveFolder
     * @param saveFolder the Folder in which userData.save should be saved in
     */
    public static void save(String saveFolder) {
        new File(saveFolder).mkdir();
        String saveFilePath = saveFolder + "/userData.xml";
        try {
            FileOutputStream fos = new FileOutputStream(saveFilePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(UserData.class);
            Marshaller marsh = jaxbContext.createMarshaller();

            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

            marsh.marshal(data,fos);

            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Tries to get default save location based on OS then calls load(String saveFolder) with that location.
     */
    public static void load() {
        String appdataPath = System.getenv("APPDATA").replaceAll("\\\\", "/");
        String saveFolder = appdataPath + "/Optimal Heist/savedata";
        load(saveFolder);
    }

    /**
     *
     * If the userData.save file is not in the specified directory a new User will be created and saved in that directory
     * @param saveFolder the Folder that contains userData.save
     *
     */
    public static void load(String saveFolder){
        new File(saveFolder).mkdir();
        String saveFilePath = saveFolder + "/userData.xml";
        File saveFile = new File(saveFilePath);
        if (!saveFile.exists()) {
            newUser();
            save(saveFolder);
            return;
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(UserData.class);
            Unmarshaller marsh = jaxbContext.createUnmarshaller();

            data = (UserData) marsh.unmarshal(saveFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int getScore(int i) {
        return data.getScore(i);
    }

    public static void newHighScore(int i, int j) {
        data.newHighScore(i,j);
    }

    public static void softReset() {
        data.reset();
    }

    public static void hardReset(){
        data = new UserData();
    }

    public static String dataToString() {
        return data.toString();
    }
}
