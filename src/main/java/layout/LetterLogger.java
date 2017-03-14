package layout;

import java.io.*;

/**
 * Created by Tor Martin Holen on 23-Jan-17.
 */
public class LetterLogger {
    private UsageStatistics stats;
    private FileReader fileReader;
    private BufferedReader bufferedReader; //Used to sample occurances

    /**
     * Creates a
     */
    public LetterLogger() {
        stats = new UsageStatistics();
        prepareReaders();
    }

    /**
     * Prepares the necessary readers for reading a file with words
     */
    private void prepareReaders() {
        ClassLoader classLoader = getClass().getClassLoader();
        //File file = new File(classLoader.getResource("word.list").getFile());
        File file = new File(classLoader.getResource("wikipediawordlist.txt").getFile());

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Samples the sequential occurrence of letters adding them to a statistic.
     * @throws IOException
     */
    public void sampleLetterOccurrence() throws IOException {
        String currentLine = "";

        while (true) {
            try {
                currentLine = bufferedReader.readLine().toLowerCase();
                System.out.println(currentLine);
                int separatingIndex = currentLine.indexOf(" ");
                //String currentWord = currentLine; // If word.list is used
                String currentWord = currentLine.substring(0, separatingIndex).replaceAll("[^a-z ]", "");
                while (currentWord.length() != 0) {
                    String firstLetter = currentWord.substring(0, 1);
                    if (currentWord.length() > 1) {
                        String secondLetter = currentWord.substring(1, 2);
                        stats.addOccurrence(firstLetter, secondLetter);
                    } else {
                        stats.addOccurrence(firstLetter, " ");
                    }
                    currentWord = currentWord.substring(1);
                }

            } catch (IndexOutOfBoundsException e) {
                // Do nothing
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
                //stats.printStatisticLine();
                System.out.println(currentLine);
                break;
            }
        }
    }


    /*public void sampleLetterOccurrence() throws IOException {
        boolean fileFinished = false;
        String currentLine = "";

        for (int i = 0; i < 10; i++) {
            while (!fileFinished) {
                try {
                    currentLine = bufferedReader.readLine().substring(i).toLowerCase();
                    //System.out.println(currentLine);

                    while (currentLine.length() != 0) {
                        String firstLetter = currentLine.substring(0, 1);
                        if (currentLine.length() > 1) {
                            String secondLetter = currentLine.substring(1, 2);
                            stats.addOccurrence(firstLetter, secondLetter);
                        } else {
                            stats.addOccurrence(firstLetter, " ");
                        }
                        currentLine = currentLine.substring(1);
                    }
                } catch (IndexOutOfBoundsException e) {
                    // Do nothing
                } catch (NullPointerException e) {
                    //e.printStackTrace();
                    //stats.printStatisticLine();
                    break;
                }


            }
            fileFinished = false;
        }
    }*/

    public UsageStatistics getStats() {
        return stats;
    }
}
