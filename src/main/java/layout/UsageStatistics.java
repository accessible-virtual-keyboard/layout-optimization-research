package layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Class that handles letter usage statistics
 * <p>
 * Created by Tor Martin Holen on 26-Jan-17.
 */
public class UsageStatistics {
/*    private final String outerAlphabet = "abcdefghijklmnopqrstuvwxyz -'";
    private final String innerAlphabet = "abcdefghijklmnopqrstuvwxyz -'";*/

    private final String outerAlphabet = "abcdefghijklmnopqrstuvwxyz";
    private final String innerAlphabet = "abcdefghijklmnopqrstuvwxyz ";
    private int index; // Global index variable used in accessing findOptimalLayout useful for print methods used simultaneously

    private HashMap<String, HashMap<String, Integer>> statistics;
    private HashMap<String, String> optimalLayouts;


    /**
     * Default constructor for initializing the required datastructures
     */
    public UsageStatistics() {
        setupStatistics();
        //printStatistics();
    }

    /**
     * Initializes statistics HashMap
     */
    private void setupStatistics() {
        statistics = new HashMap<String, HashMap<String, Integer>>();
        String alphabet = outerAlphabet;
        while (alphabet.length() != 0) {
            statistics.put(alphabet.substring(0, 1), setupInnerStatistics());
            alphabet = alphabet.substring(1);
        }
        //printStatistics();
    }

    /**
     * Help method for initializing the statistics HashMap
     *
     * @return HashMap<String, Integer</> keys according to the alphabet and with value data equal to 0.
     */
    private HashMap<String, Integer> setupInnerStatistics() {
        String alphabet = innerAlphabet;
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        while (alphabet.length() != 0) {
            result.put(alphabet.substring(0, 1), 0);
            alphabet = alphabet.substring(1);
        }
        return result;
    }

    /**
     * Prints the statistics HashMap
     */
    public void printStatistics() {
        String result = statistics.toString().replaceAll("},", "}\n\n");
        System.out.println(result);
    }

    /**
     * Prints the statistics in a copy friendly format.
     */
    public void printStatisticsCopyFriendly() {
        /*String result = statistics.toString().replaceAll("},", "}\n\n");
        System.out.println(result);*/

        String letters = outerAlphabet;
        String topTableRow = "N/A";
        ArrayList<String> stringsToPrint = new ArrayList<>();

        while (letters.length() != 0) {
            String current = letters.substring(0, 1);
            letters = letters.substring(1);

            topTableRow += "\t" + current;
            String stringToPrint = current;

            String innerLetters = innerAlphabet;
            while (innerLetters.length() != 0) {
                String innerCurrent = innerLetters.substring(0, 1);
                innerLetters = innerLetters.substring(1);

                //stringToPrint += "\t" + ((statistics.get(current).get(innerCurrent)/102753.0 * 1000.0));
                stringToPrint += "\t" + statistics.get(current).get(innerCurrent);
            }
            stringsToPrint.add(stringToPrint);
        }

        System.out.println(topTableRow);
        for (String str : stringsToPrint) {
            System.out.println(str);
        }
    }


    public void printStatisticLine() {
        String result = statistics.toString().replaceAll("},", "}");
        System.out.println(result);
    }


    /**
     * Adds and occurrence to the statistics HashMap
     *
     * @param firstLetter  Represents the first letter that has been typed
     * @param secondLetter Represents the letter that is going to be typed and this keys value is incremented by one
     */
    public void addOccurrence(String firstLetter, String secondLetter) {
        HashMap<String, Integer> nestedMap = statistics.get(firstLetter);
        nestedMap.put(secondLetter, nestedMap.get(secondLetter) + 1);
    }

    /**
     * Calculates and prints which layout strings is most optimal according to the data in the statistics HashMap.
     */
    public void findOptimalLayouts() {
        optimalLayouts = new HashMap<String, String>();
        String firstAlphabet = outerAlphabet;
        while (firstAlphabet.length() != 0) {
            String parentLetter = firstAlphabet.substring(0, 1);
            HashMap<String, Integer> nestedMap = statistics.get(parentLetter);
            String secondAlphabet = innerAlphabet;
            ArrayList<LetterStatistics> letterStatisticsArrayList = new ArrayList<LetterStatistics>();

            while (secondAlphabet.length() != 0) {
                String childLetter = secondAlphabet.substring(0, 1);
                int occurrences = nestedMap.get(childLetter);
                LetterStatistics letterStatistics = new LetterStatistics(parentLetter, childLetter, occurrences);
                letterStatisticsArrayList.add(letterStatistics);
                secondAlphabet = secondAlphabet.substring(1);
            }

            sortList(letterStatisticsArrayList);
            addLayoutToHashMap(letterStatisticsArrayList);

            //printLayoutCopyFriendly(letterStatisticsArrayList);// Only one at a time
            printLayoutDataSpreadsheetFriendly(letterStatisticsArrayList); // Only one at a time
            index++;

            firstAlphabet = firstAlphabet.substring(1);
        }
        ;
    }

    private void printLayout(ArrayList<LetterStatistics> list) {
        System.out.print("\t\t" + outerAlphabet.charAt(index) + "&");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).getLetter().replace(" ", "\\_")); // + ": " +  list.get(i).getOccurrences() + "   ");
        }
        System.out.println("\\\\ \\hline" +
                "");
    }

    private void printLayoutCopyFriendly(ArrayList<LetterStatistics> list) {
        System.out.print(outerAlphabet.charAt(index) + "\t");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).getLetter()); //+ ": " +  list.get(i).getOccurrences() + "   ");
        }
        System.out.println();
    }

    private void printLayoutDataSpreadsheetFriendly(ArrayList<LetterStatistics> list) {

        String lettersToPrint = outerAlphabet.charAt(index) + ":\t";
        String numbersToPrint = outerAlphabet.charAt(index) + ":\t";
        for (int i = 0; i < list.size(); i++) {
            lettersToPrint += list.get(i).getLetter().replace(" ", "_") + "\t"; //+ ": " +  list.get(i).getOccurrences() + "   ");
            numbersToPrint += list.get(i).getOccurrences() + "\t";
        }
        System.out.println(lettersToPrint);
        System.out.println(numbersToPrint);
        System.out.println();
    }

    private void addLayoutToHashMap(ArrayList<LetterStatistics> list) {
        String layout = "";
        for (int i = 0; i < list.size(); i++) {
            layout += list.get(i).getLetter();
        }
        optimalLayouts.put(list.get(0).getParentLetter(), layout);
    }

    private void printOccurences(ArrayList<LetterStatistics> list) {
        System.out.print(outerAlphabet.charAt(index) + "=");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).getLetter() + ": " + list.get(i).getOccurrences() + "   ");
        }
        System.out.println("\n");
    }

    public void sortList(ArrayList<LetterStatistics> list) {
        Collections.sort(list, new Comparator<LetterStatistics>() {
            public int compare(LetterStatistics l1, LetterStatistics l2) {
                if (l1.getOccurrences() >= l2.getOccurrences()) {
                    return -1;
                } else {
                    return 1;
                }

            }
        });
    }

    public HashMap<String, String> getOptimalLayouts() {
        return optimalLayouts;
    }
}
