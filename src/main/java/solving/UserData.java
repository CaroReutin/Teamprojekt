package solving;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// In case this becomes an issue later fyi
// < will be saved as &lt;
// & will be saved as &amp;
// > will be saved as &gt;
// \" will be saved as "
// !§$%/()=?"+#*~'`sèé^°,.;:_-@|äÄ were as saved correctly

/**
 * Do not make a Solving.UserData object,
 * use the static methods in UserDataManager instead.
 */
@XmlRootElement
public class UserData implements Serializable {
  /**
   * the best level scores.
   */
  @XmlElement
  private ArrayList<Integer> bestLevelScores = new ArrayList<>();

  /**
   * Overwrites the old bestLevelScores with 15 0s.
   */
  public void newUser() {
    bestLevelScores = new ArrayList<>();
    for (int i = 0; i < AppData.LEVELAMOUNT; i++) {
      bestLevelScores.add(0);
    }
  }

  /**
   * There is no check whether the newHighScore is actually higher than before.
   *
   * @param level the index of the level
   * @param score the new high score
   * @throws IndexOutOfBoundsException if there is no entry for level
   */
  public void newHighScore(final int level, final int score)
          throws IndexOutOfBoundsException {
    bestLevelScores.set(level, score);
  }

  /**
   * Gets score.
   *
   * @param level the index of the level
   * @return returns the current high score
   * @throws IndexOutOfBoundsException if there is no entry for level
   */
  public int getScore(final int level) throws IndexOutOfBoundsException {
    return bestLevelScores.get(level);
  }

  /**
   * Changes the format to string.
   *
   * @return returns the Scores in format x1|x2|...|x14|x15
   * where xn is the score of the nth Level
   */
  @Override
  public String toString() {
    StringBuilder scores = new StringBuilder();
    for (Integer bestLevelScore : bestLevelScores) {
      scores.append(bestLevelScore);
      scores.append("|");
    }
    return scores.substring(0, scores.length() - 1);
  }
}
