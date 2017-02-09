package layout;

/**
 * Help class used for letter UsageStatistics class
 * Created by Tor Martin Holen on 27-Jan-17.
 */
public class LetterStatistics {
    String parentLetter;
    String letter;
    private int occurrences;

    public LetterStatistics(String parentLetter, String letter, int occurrences) {
        this.parentLetter = parentLetter;
        this.letter = letter;
        this.occurrences = occurrences;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    public String getParentLetter() {
        return parentLetter;
    }

}
