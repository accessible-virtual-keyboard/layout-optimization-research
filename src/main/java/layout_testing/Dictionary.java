package layout_testing;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * Created by Tor Martin Holen on 31-Jan-17.
 */
public class Dictionary {
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private ArrayList<DictionaryEntry> dictionary;
    private ArrayList<DictionaryEntry> primarySuggestions;
    private ArrayList<DictionaryEntry> secondarySuggestions;
    private SortingOrder preferredOrder = SortingOrder.FREQUENCY_HIGH_TO_LOW;
    private String textWritten = "";

    /**
     * Constructs a dictionary
     */
    public Dictionary() {
        dictionary = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("wikipediawordlist.txt").getPath());
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String currentLine;
        while (true) {
            try {
                currentLine = bufferedReader.readLine().toLowerCase();//Produces null pointer when no more lines
                int separatingIndex = currentLine.indexOf(" ");
                String currentWord = currentLine.substring(0,separatingIndex);
                //System.out.println(currentLine);
                int frequency = Integer.parseInt(currentLine.substring(++separatingIndex).trim());
                int reducedFrequency = Math.round(frequency/77);
                dictionary.add(new DictionaryEntry(currentWord,reducedFrequency));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                break;
            }
        }

        fixDuplicateEntries();
        //printDictionary();
    }

    public SortingOrder getPreferredOrder() {
        return preferredOrder;
    }

    public void setPreferredOrder(SortingOrder preferredOrder) {
        this.preferredOrder = preferredOrder;
    }

    public enum SortingOrder{
        ALPHABETICALLY_A_TO_Z,
        ALPHABETICALLY_Z_TO_A,
        FREQUENCY_HIGH_TO_LOW,
        FREQUENCY_LOW_TO_HIGH,
        CURRENT_ORDER
    }

    /**
     * Sorts list according to an order from the SortingOrder enum
     * @param list List to sort
     * @param order SortingOrder Enum
     */
    private void sortList(ArrayList<DictionaryEntry> list, final SortingOrder order){
        Comparator<DictionaryEntry> comparator = null;
        if(order != SortingOrder.CURRENT_ORDER){
            if(order == SortingOrder.ALPHABETICALLY_A_TO_Z){
                comparator = Comparator.comparing(DictionaryEntry::getWord);
            }
            if(order == SortingOrder.ALPHABETICALLY_Z_TO_A){
                comparator = (o1, o2) -> o2.getWord().compareTo(o1.getWord());
            }
            if(order == SortingOrder.FREQUENCY_HIGH_TO_LOW){
                comparator = (o1, o2) -> Integer.compare(o2.getFrequency(), o1.getFrequency());
            }
            if(order == SortingOrder.FREQUENCY_LOW_TO_HIGH){
                comparator = Comparator.comparingInt(DictionaryEntry::getFrequency);
            }
            list.sort(comparator);
        }
    }

    /**
     * Use with a list that contains duplicate entries to fix them
     */
    private void fixDuplicateEntries(){
        sortList(dictionary, SortingOrder.ALPHABETICALLY_A_TO_Z);
        DictionaryEntry previousEntry = null;
        for (int i = 0; i < dictionary.size(); i++) {
            DictionaryEntry currentEntry = dictionary.get(i);
            if(previousEntry != null && previousEntry.getWord().equals(currentEntry.getWord())){
                previousEntry.setFrequency(previousEntry.getFrequency() + currentEntry.getFrequency());
                dictionary.remove(currentEntry);
                i--;
            }else{
                previousEntry = currentEntry;
            }
        }

        sortList(dictionary, preferredOrder);
    }

    /**
     * Finds suggestions based upon whats already written
     * @param textWritten Currently written text
     */
    public void findSuggestions(String textWritten){
        primarySuggestions = new ArrayList<>();
        secondarySuggestions = new ArrayList<>();
        this.textWritten = textWritten;

        for (DictionaryEntry currentEntry : dictionary) {
            String currentWord = currentEntry.getWord();

            if( currentWord.startsWith(textWritten)){
                primarySuggestions.add(currentEntry);
            }
            else if (currentWord.contains(textWritten) && !currentWord.startsWith(textWritten)){
                secondarySuggestions.add(currentEntry);
            }
        }

        sortList(secondarySuggestions, preferredOrder);
    }

    public void printPrimarySuggestions(){
        printSuggestions(primarySuggestions, "Primary");
    }

    public void printSecondarySuggestions(){
        printSuggestions(secondarySuggestions, "Secondary");
    }

    public ArrayList<DictionaryEntry> getPrimarySuggestions() {
        return primarySuggestions;
    }

    public ArrayList<DictionaryEntry> getSecondarySuggestions() {
        return secondarySuggestions;
    }

    public ArrayList<DictionaryEntry> getSuggestions() {
        ArrayList<DictionaryEntry> allSuggestions = getPrimarySuggestions();
        allSuggestions.addAll(getSecondarySuggestions());
        printSuggestions(allSuggestions,"All");
        return allSuggestions;
    }

    /**
     * Prints suggestions
     * @param suggestions List containing suggestions
     * @param suggestionType Title for which type of suggestions this is
     */
    private void printSuggestions(ArrayList<DictionaryEntry> suggestions, String suggestionType){
        System.out.println(suggestionType + " suggestion for: \"" + textWritten + "\"");
        printList(suggestions);
    }

    public void printDictionary(){
        printList(dictionary);
    }


    /**
     * Prints a list with the word and frequency
     * @param list
     */
    public void printList(ArrayList<DictionaryEntry> list){
        for (DictionaryEntry entry : list) {
            System.out.println(entry.getWord() + " - " + entry.getFrequency());
        }
    }

    /**
     * Gets which index current word is in the list that is being searched.
     * @param list List to search.
     * @param targetWord Word to find.
     * @return
     */
    public int findWordIndex(ArrayList<DictionaryEntry> list, String targetWord){
        for (int i = 0; i < list.size(); i++) {
            String currentWord = list.get(i).getWord();
            if(Objects.equals(currentWord, targetWord)){
                return i;
            }
        }
        return -1;
    }

}
