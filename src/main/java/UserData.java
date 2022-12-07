import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Do not make a UserData object, use the static methods in UserDataManager instead.
 */
@XmlRootElement
public class UserData implements Serializable {
    @XmlElement

    private ArrayList<Integer> bestLevelScores = new ArrayList<>();

    public void newUser(){
        for (int i = 0; i < UserDataManager.LEVELAMOUNT; i++) {
            bestLevelScores.add(0);
        }
    }

    public void newHighScore(int level,int score){
        bestLevelScores.set(level,score);
    }

    public int getScore(int level){
        return bestLevelScores.get(level);
    }

    public void reset() {
        bestLevelScores = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder scores = new StringBuilder();
        for (Integer bestLevelScore : bestLevelScores) {
            scores.append(bestLevelScore);
            scores.append("|");
        }
        return scores.toString();
    }
}
