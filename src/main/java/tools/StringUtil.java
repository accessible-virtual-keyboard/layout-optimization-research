package tools;

/**
 * Created by Tor Martin Holen on 10-Feb-17.
 */
public abstract class StringUtil {
    /**
     * Used for printing data to console that easily can be copied into a spreadsheet
     * @param base String to add tabs between every character
     */
    public static void StringTabInjection(String base){
        for(int i = 0; i < base.length(); i+=2){
            base = base.substring(0,i) + "\t" + base.substring(i);
        }
        System.out.println(base);
    }
}
