/*
package layout_testing;

import java.util.HashMap;

*/
/**
 * Created by Tor Martin Holen on 30-Jan-17.
 *//*

public class AdaptiveLayout extends Layout{

    private static HashMap<String, String> adaptiveLayout;
    private HashMap<String, String[]> adaptiveLayoutVariant;
    public AdaptiveLayout(String layoutName, HashMap<String, String> adaptiveLayout, String testText, int baseEfficiency, int rows, int columns, LayoutProperties type, LayoutDictionary dictionary) {
        super(layoutName,testText, baseEfficiency);
        if(this.adaptiveLayout == null){
            adaptiveLayout.put(null, ETAO);
            adaptiveLayout.put(" ", ETAO);
            this.adaptiveLayout = adaptiveLayout;
        }
        adaptiveLayoutVariant = setAdaptiveLayoutType(rows,columns,type);
        testLayout(testText, dictionary);
    }

    public void testLayout(String testText , LayoutDictionary dictionary) {
        int result = 0;
        if(dictionary == LayoutDictionary.NONE){
            result += getTextEfficiency(testText);
        }

        if(dictionary == LayoutDictionary.DICTIONARY){

        }
        addResult(new LayoutResult(layoutName, result));
    }

*/
/*    private int getTextEfficiency(String text){
        int result = 0;
        String previousLetter = null;
        while (testText.length() != 0) {
            String nextLetter = testText.substring(0, 1);
            testText = testText.substring(1);

            String[] layout = adaptiveLayoutVariant.get(previousLetter);
            result += findMultilineLetterEfficiency(nextLetter, layout);
            previousLetter = nextLetter;
        }
        return result;
    }*//*


*/
/*    private HashMap<String, String[]> setAdaptiveLayoutType(int rows, int columns, LayoutProperties layoutType) {
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

            //System.out.println(Arrays.toString(result));
            if (letter.equals(" ")) {
                adaptiveLayoutResult.put(null, result); //Considering the case when there is no previous letter.
            }
            adaptiveLayoutResult.put(letter, result);
        }
        return adaptiveLayoutResult;
    }*//*



}
*/
