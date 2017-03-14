package layout_testing;

import java.util.HashMap;

/**
 * Created by Tor Martin Holen on 27-Jan-17.
 */
public class LayoutTester {

    private HashMap<String, String> adaptiveLayout;
    private int baseEfficiency;
    private int defaultLayoutRows;
    private int defaultLayoutColumns;
    private String testText;

    private Layout adaptive;

    /**
     * Provides the layout tester with some default values for testing.
     * @param adaptiveLayout The data used for the adaptive layout
     * @param baseEfficiency Default Base efficiency of most layout
     * @param defaultLayoutRows Default row number for multiline layouts
     * @param defaultLayoutColumns Default column number for multiline layouts
     */
    public LayoutTester(HashMap<String, String> adaptiveLayout, int baseEfficiency, int defaultLayoutRows, int defaultLayoutColumns) {
        this.adaptiveLayout = adaptiveLayout;
        this.baseEfficiency = baseEfficiency;
        this.defaultLayoutRows = defaultLayoutRows;
        this.defaultLayoutColumns = defaultLayoutColumns;
    }

    /**
     * Test different layouts and prints the result.
     *
     * @param testText
     */
    public void testText(String testText){
        testText = testText.toLowerCase().replaceAll("[^a-z ]", "");
        int singleLineBaseEfficiency = 1;
        Layout aSL = new Layout("Adaptive Single-line Layout", adaptiveLayout, testText, singleLineBaseEfficiency, 1, 28, Layout.LayoutProperties.SINGLELINE, Layout.LayoutDictionary.NONE);
        Layout aSDL = new Layout("Adaptive Single-line Dictionary Layout", adaptiveLayout, testText, singleLineBaseEfficiency, 1, 28, Layout.LayoutProperties.SINGLELINE, Layout.LayoutDictionary.DICTIONARY);
        Layout aML = new Layout("Adaptive Multiline Layout", adaptiveLayout, testText, baseEfficiency, defaultLayoutRows, defaultLayoutColumns, Layout.LayoutProperties.MULTILINE, Layout.LayoutDictionary.NONE);
        Layout aMDL = new Layout("Adaptive Multiline Dictionary Layout", adaptiveLayout, testText, baseEfficiency, defaultLayoutRows, defaultLayoutColumns, Layout.LayoutProperties.MULTILINE, Layout.LayoutDictionary.DICTIONARY);
        Layout aDL = new Layout("Adaptive Diagonalized Layout", adaptiveLayout, testText, baseEfficiency, defaultLayoutRows, defaultLayoutColumns, Layout.LayoutProperties.DIAGONALIZE, Layout.LayoutDictionary.NONE);
        Layout aDDL = new Layout("Adaptive Diagonalized Dictionary Layout", adaptiveLayout, testText, baseEfficiency, defaultLayoutRows, defaultLayoutColumns, Layout.LayoutProperties.DIAGONALIZE, Layout.LayoutDictionary.DICTIONARY);

        Layout abcSL = new Layout("ABC Single-line Layout", Layout.ABC, testText, singleLineBaseEfficiency, 1, 28, Layout.LayoutProperties.SINGLELINE, Layout.LayoutDictionary.NONE );
        Layout abcSDL = new Layout("ABC Single-line Dictionary Layout", Layout.ABC, testText, singleLineBaseEfficiency, 1, 28, Layout.LayoutProperties.SINGLELINE, Layout.LayoutDictionary.DICTIONARY );
        Layout abcML = new Layout("ABC Multi-line Layout", Layout.ABC, testText, baseEfficiency, defaultLayoutRows, defaultLayoutColumns, Layout.LayoutProperties.MULTILINE, Layout.LayoutDictionary.NONE );
        Layout abcMDL= new Layout("ABC Multi-line Dictionary Layout", Layout.ABC, testText, baseEfficiency, defaultLayoutRows, defaultLayoutColumns, Layout.LayoutProperties.MULTILINE, Layout.LayoutDictionary.DICTIONARY );

        Layout qwertyML = new Layout("QWERTY Multi-line Layout", Layout.QWERTY, testText, baseEfficiency, 3, 10, Layout.LayoutProperties.MULTILINE, Layout.LayoutDictionary.NONE );
        Layout qwertyMDL= new Layout("QWERTY Multi-line Dictionary Layout", Layout.QWERTY, testText, baseEfficiency, 3, 10, Layout.LayoutProperties.MULTILINE, Layout.LayoutDictionary.DICTIONARY );

        Layout etaoSL = new Layout("ETAO Single-line Layout", Layout.ETAO, testText, singleLineBaseEfficiency, 1, 28, Layout.LayoutProperties.SINGLELINE, Layout.LayoutDictionary.NONE );
        Layout etaoML = new Layout("ETAO Multi-line Layout", Layout.ETAO, testText, baseEfficiency, defaultLayoutRows, defaultLayoutColumns, Layout.LayoutProperties.MULTILINE, Layout.LayoutDictionary.NONE );
        Layout etaoDL = new Layout("ETAO Diagonalized Layout", Layout.ETAO, testText, baseEfficiency, defaultLayoutRows, defaultLayoutColumns, Layout.LayoutProperties.DIAGONALIZE, Layout.LayoutDictionary.NONE);
        Layout etaoSDL = new Layout("ETAO Single-line Dictionary Layout", Layout.ETAO, testText, singleLineBaseEfficiency, 1, 28, Layout.LayoutProperties.SINGLELINE, Layout.LayoutDictionary.DICTIONARY );
        Layout etaoMDL = new Layout("ETAO Multi-line Dictionary Layout", Layout.ETAO, testText, baseEfficiency, defaultLayoutRows, defaultLayoutColumns, Layout.LayoutProperties.MULTILINE, Layout.LayoutDictionary.DICTIONARY );
        Layout etaoDDL = new Layout("ETAO Diagonalized Dictionary Layout", Layout.ETAO, testText, baseEfficiency, defaultLayoutRows, defaultLayoutColumns, Layout.LayoutProperties.DIAGONALIZE, Layout.LayoutDictionary.DICTIONARY );

        Layout.printResultsLatex(testText);

        adaptive = aMDL;
    }

    /**
     * Must be called after testText method, this method is used for printing code to implement the adaptive layout in other projects
     */
    public void printAdaptiveLayoutCode(){
        adaptive.printAdaptiveLayoutCode();
    }



}



