package layout_testing;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Tor Martin Holen on 30-Jan-17.
 */
public class Layout{
    public static final String ABC = " abcdefghijklmnopqrstuvwxyz"; //todo
    //public static final String ETAO = " etaoinshrdlcumwfgypbvkjxqz"; //Wikipedia
    public static final String ETAO = " etaoinsrhldcumfpgwybvkxjqz"; //Norvig's website
    public static final String QWERTY = " qwertyuiopasdfghjklzxcvbnm";
    public static final String FIRST_LETTER_FREQUENCY = "taoiswcbphfmdrelnguvyjkqxz "; //Norvig's website

    /**
     * Only for accesing some methods
     */
    public Layout() {
    }


    protected enum LayoutProperties {
        SINGLELINE,
        MULTILINE,
        DIAGONALIZE
    }

    protected enum LayoutDictionary {
        NONE,
        DICTIONARY
    }

    protected String layoutName;
    protected int baseEfficiency;
    protected static ArrayList<LayoutResult> resultList = new ArrayList<LayoutResult>();
    protected String testText;
    private Dictionary dictionary;
    private String[] layout;
    private static HashMap<String, String> adaptiveLayout;
    private HashMap<String, String[]> adaptiveLayoutVariant; //todo
    private LayoutProperties properties;
    private LayoutDictionary dictionaryEnabled;

    public void printAdaptiveLayoutCode(){
        String lettersLeft = ABC;
        System.out.println("HashMap<String,String[]> adaptiveLayout = new HashMap<>();");
        while(lettersLeft.length() != 0){
            String letter = lettersLeft.substring(0,1);
            String[] currentLayout = adaptiveLayoutVariant.get(letter);
            String layoutLetters = String.join("", currentLayout);
            String result = diagonalizeLayoutAsString(layoutLetters, 5, 6);
            lettersLeft = lettersLeft.substring(1);
            //String toPrint = Arrays.toString(currentLayout);
            //System.out.println(toPrint);
            //String toPrint = "adaptiveLayout.put(\"" + letter + "\", new String[]{";
            String toPrint = "map.put(\"" + letter + "\", \"" + result + "\");";
            /*for (int i = 0; i < currentLayout.length; i++) {

                toPrint += "\"" + currentLayout[i] + "\",";
            }*/
            /*System.out.println(toPrint.substring(0,toPrint.length()-1) + "});");*/
            System.out.println(toPrint);
        }
    }

    /**
     * Creates a normal layout.
     * @param layoutName Name of the layout
     * @param layout String containing the base structure
     * @param testText Test text it should be tested with
     * @param baseEfficiency Efficiency the most efficient index is at
     * @param rows Number of rows in layout
     * @param columns Number of columns in layout
     * @param properties Enum - Decides which Structure type the layout should be converted to
     * @param dictionaryEnabled Enum - Decides dictionary should be used
     */
    public Layout(String layoutName, String layout, String testText, int baseEfficiency, int rows, int columns, LayoutProperties properties, LayoutDictionary dictionaryEnabled) {
        this(layoutName, testText, baseEfficiency, dictionaryEnabled);

        this.properties = properties;
        this.dictionaryEnabled = dictionaryEnabled;

        if(properties == LayoutProperties.SINGLELINE){
            this.layout = new String[]{layout};
        }
        if(properties == LayoutProperties.MULTILINE){
            this.layout = multilineLayout(layout,rows,columns);
        }
        if(properties == LayoutProperties.DIAGONALIZE){
            this.layout = diagonalizeLayout(layout,rows,columns);
        }

        testLayout(this.testText, dictionaryEnabled);
    }

    /**
     * Creates a adaptive layout.
     * @param layoutName Name of the layout
     * @param adaptiveLayout Datastructure containing the layout base structures
     * @param testText Test text it should be tested with
     * @param baseEfficiency Efficiency the most efficient index is at
     * @param rows Number of rows in layout
     * @param columns Number of columns in layout
     * @param type Enum - Decides which Structure type the layout should be converted to
     * @param dictionaryEnabled Enum - Decides dictionary should be used
     */
    public Layout(String layoutName, HashMap<String, String> adaptiveLayout, String testText, int baseEfficiency, int rows, int columns, LayoutProperties type, LayoutDictionary dictionaryEnabled) {
        this(layoutName, testText, baseEfficiency, dictionaryEnabled);
        adaptiveLayout.put(null, FIRST_LETTER_FREQUENCY);
        adaptiveLayout.put(" ", FIRST_LETTER_FREQUENCY);
        this.adaptiveLayout = adaptiveLayout;

        adaptiveLayoutVariant = setAdaptiveLayoutType(rows,columns,type);
        testLayout(testText, dictionaryEnabled);
    }

    private Layout(String layoutName, String testText, int baseEfficiency, LayoutDictionary dictionaryEnabled) {
        this.layoutName = layoutName;
        this.testText = testText;
        this.baseEfficiency = baseEfficiency;
        if(dictionaryEnabled == LayoutDictionary.DICTIONARY){
            this.dictionary = new Dictionary();
        }

    }

    /**
     * Tests and adds test results to a static result list
     * @param testText
     * @param dictionaryEnabled
     */
    public void testLayout(String testText, LayoutDictionary dictionaryEnabled){
        int result = 0;
        if(dictionaryEnabled==LayoutDictionary.NONE){
            result += getTextEfficiency(testText);
        }

        if(dictionaryEnabled==LayoutDictionary.DICTIONARY){
            String[] wordStringArray = testText.split(" ");
            ArrayList<String> words = new ArrayList<>(Arrays.asList(wordStringArray));

            while(words.size()!=0){
                String currentWord = words.get(0);
                System.out.println("\nCurrent word: " + currentWord);
                int manuallyTypedLetters = 3;//(int) Math.floor(currentWord.length()/2.0);
                if(currentWord.length()>=manuallyTypedLetters){
                    String manuallyWrittenWordPart = currentWord.substring(0,manuallyTypedLetters);

                    if(!manuallyWrittenWordPart.equals(currentWord)){
                        result += getDictionaryEfficiency(currentWord, manuallyWrittenWordPart);
                    }else{
                        System.out.println(getTextEfficiency(currentWord + " "));
                        result += getTextEfficiency(currentWord + " ");
                    }
                }else{
                    System.out.println(getTextEfficiency(currentWord + " "));
                    result += getTextEfficiency(currentWord + " ");
                }
                words.remove(0);
            }
        }
        resultList.add(new LayoutResult(layoutName, result)) ;
    }

    /**
     * Calculates word efficiency with dictionary.
     * @param currentWord
     * @param manuallyWritten
     * @return
     */
    private int getDictionaryEfficiency(String currentWord, String manuallyWritten){
        dictionary.findSuggestions(manuallyWritten);
        dictionary.printPrimarySuggestions();
        int lookupIndex = dictionary.findWordIndex(dictionary.getPrimarySuggestions(),currentWord);
        //System.out.println("lookupindex: " + lookupIndex);
        //System.out.println(getTextEfficiency(manuallyWritten));
        int effectiveSteps;
        if(lookupIndex != -1){
            effectiveSteps = getTextEfficiency(manuallyWritten) + lookupIndex + 1;
        }else {
            effectiveSteps = getTextEfficiency(currentWord + " ");
        }

        System.out.println("Effective steps: " + effectiveSteps + ", Index found: " + lookupIndex + "\n");
        //System.out.println("Regular typing: " + getTextEfficiency(currentWord));
        return effectiveSteps;
    }

    /**
     * Calculates word efficiency in layout
     * @param text
     * @return
     */
    private int getTextEfficiency(String text){
        int result = 0;

        if(adaptiveLayoutVariant == null){
            //System.out.println("regular triggered");
            while (text.length() != 0) {
                String nextLetter = text.substring(0, 1);
                text = text.substring(1);
                result += findMultilineLetterEfficiency(nextLetter, layout);
            }
        }

        if(layout == null){
            //System.out.println("adaptive triggered");
            String previousLetter = null;
            while (text.length() != 0) {
                String nextLetter = text.substring(0, 1);
                text = text.substring(1);

                String[] layout = adaptiveLayoutVariant.get(previousLetter);
                result += findMultilineLetterEfficiency(nextLetter, layout);
                /*System.out.println("Previous letter: " + previousLetter);
                printLayout(layout);
                System.out.println(result);*/
                previousLetter = nextLetter;
            }
        }

        return result;
    }

    private void printLayout(String[] layout){
        for (String row: layout) {
            System.out.println(row);
        }
    }

    /**
     * Finds the efficiency of a letter in a given layout.
     * @param nextLetter Letter to find
     * @param layout Layout searched for letter
     * @return Letter efficiency
     * @throws RuntimeException Letter isn't in layout
     */
    protected int findMultilineLetterEfficiency(String nextLetter, String[] layout) throws RuntimeException {

        for (int i = 0; i < layout.length; i++) {
            if (layout[i].contains(nextLetter)) {
                int result = i + layout[i].indexOf(nextLetter) + baseEfficiency;
                //printLayout(layout);
                //System.out.println(result + "-" + nextLetter+ "\n");
                return result;
            }
        }
        throw new RuntimeException("Character \""+ nextLetter + "\" was not found");

    }

    public static void printResults(String testText){
        System.out.println("------Results------");
        System.out.println("Steps needed to write test-text");
        System.out.println("Test-text length: " + testText.length());
        sortList(resultList);

        DecimalFormat twoDecimals = new DecimalFormat("#.00");
        for (int i = 0; i < resultList.size(); i++) {
            LayoutResult layoutResult = resultList.get(i);
            double stepsPerLetter = (double)layoutResult.getLayoutResult()/testText.length();
            String result = layoutResult.getLayoutName() + "\n\t\t" + "Steps:\t" + layoutResult.getLayoutResult() + "\t" + "Avg steps/letter: \t" + twoDecimals.format(stepsPerLetter);
            System.out.println("\t" + result);
        }
        System.out.println("------End------");
        resultList = new ArrayList<>();
    }

    /**
     * Prints results in a latex friendly format
     * @param testText
     */
    public static void printResultsLatex(String testText){
        System.out.println("------Results Latex friendly------");
        System.out.println("Test-text length: " + testText.length());
        sortListReverse(resultList);

        DecimalFormat twoDecimals = new DecimalFormat("#.00");
        for (int i = 0; i < resultList.size(); i++) {
            LayoutResult layoutResult = resultList.get(i);
            double stepsPerLetter = (double)layoutResult.getLayoutResult()/testText.length();
            String result = i+1 + " & " + layoutResult.getLayoutName() + " & " + layoutResult.getLayoutResult() + " & " + twoDecimals.format(stepsPerLetter);
            System.out.println(result + " \\\\ \\hline");
        }
        System.out.println("------End------");
        resultList = new ArrayList<>();
    }

    /**
     * Sorts list worst to best
     * @param list List to sort
     */
    public static void sortList(ArrayList<LayoutResult> list){
        Collections.sort(list, (l1, l2) -> {
            if(l1.getLayoutResult() >= l2.getLayoutResult()){
                return -1;
            }else{
                return 1;
            }
        });
    }


    /**
     * Sorts list best to worst
     * @param list List to sort
     */
    public static void sortListReverse(ArrayList<LayoutResult> list){
        Collections.sort(list, (l1, l2) -> {
            if(l1.getLayoutResult() >= l2.getLayoutResult()){
                return 1;
            }else{
                return -1;
            }
        });
    }

    /**
     * Class used to contain test results
     */
    protected class LayoutResult {
        private String layoutName;
        private int layoutResult;

        public LayoutResult(String layoutName, int layoutResult) {
            this.layoutName = layoutName;
            this.layoutResult = layoutResult;
        }

        public String getLayoutName() {
            return layoutName;
        }

        public int getLayoutResult() {
            return layoutResult;
        }
    }

    public void addResult(LayoutResult layoutResult) {
        resultList.add(layoutResult);
    }


    /**
     * Modifies base layout to an efficient diagonalized-multiline structure
     * @param layout base layout string
     * @param rows number of rows
     * @param columns number of columns
     * @return Returns diagonalized string array structure.
     */
    protected String[] diagonalizeLayout(String layout, int rows, int columns) {
        String[] result = new String[rows];
        int lowestIndexSum = 0;
        while (layout.length() != 0) {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    if (lowestIndexSum == col + row) {
                        //System.out.println(layout.substring(0,1));
                        if (result[row] == null) {
                            result[row] = "";
                        }
                        result[row] += layout.substring(0, 1);
                        layout = layout.substring(1);
                    }
                }
            }
            lowestIndexSum++;
        }
        //System.out.println(Arrays.toString(result));
        return result;
    }

    /**
     * Modifies base layout to an efficient diagonalized-multiline structure
     * @param layout base layout string
     * @param rows number of rows
     * @param columns number of columns
     * @return Returns diagonalized string array structure.
     */
    public String diagonalizeLayoutAsString(String layout, int rows, int columns) {
        String result = "";
        String[] temp = diagonalizeLayout(layout,rows,columns);
        result = String.join("", temp);
        //System.out.println(Arrays.toString(result));
        return result;
    }


    /**
     * Modifies base layout to an multiline structure
     * @param layout base layout string
     * @param rows Number of rows
     * @param columns Number of columns
     * @return Returns multiline layout String array structure
     */
    protected String[] multilineLayout(String layout, int rows, int columns) {
        String[] result = new String[rows];
        while (layout.length() != 0) {
            for (int row = 0; row < rows; row++) {
                //System.out.println(layout.substring(0,1));
                if (result[row] == null) {
                    result[row] = "";
                }
                if (layout.length() <= columns) {
                    result[row] += layout;
                    layout = "";
                } else {
                    result[row] += layout.substring(0, columns);
                    layout = layout.substring(columns);
                }

            }
        }
        //printLayout(result);
        //System.out.println(Arrays.toString(result));
        return result;
    }

    /**
     * Modifies base addaptive layout to it's appropriate setting based upon which layouttype is selected
     * @param rows Number of rows
     * @param columns Number of columns
     * @param layoutType Singleline, Multiline or Diagonalized
     * @return Wanted structured layout
     */
    private HashMap<String, String[]> setAdaptiveLayoutType(int rows, int columns, LayoutProperties layoutType) {
        HashMap<String, String[]> adaptiveLayoutResult = new HashMap<String, String[]>();
        String letters = ABC;
        while (letters.length() != 0) {
            if (letters.length() > columns * rows) {
                System.out.println("Requirement: Columns * rows >= 27");
                break;
            }
            String letter = letters.substring(0, 1);
            String layout = adaptiveLayout.get(letter);
            letters = letters.substring(1);

            String[] result = new String[rows];
            if (layoutType == LayoutProperties.DIAGONALIZE) {
                result = diagonalizeLayout(layout, rows, columns);
            }
            if (layoutType == LayoutProperties.MULTILINE) {
                result = multilineLayout(layout, rows, columns);
            }
            if(layoutType == LayoutProperties.SINGLELINE){
                result = new String[]{adaptiveLayout.get(letter)};
            }

            //System.out.println(Arrays.toString(result));
            if (letter.equals(" ")) {
                adaptiveLayoutResult.put(null, result); //Considering the case when there is no previous letter.
            }
            adaptiveLayoutResult.put(letter, result);
        }
        return adaptiveLayoutResult;
    }
}
