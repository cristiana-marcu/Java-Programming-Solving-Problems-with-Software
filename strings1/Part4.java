import edu.duke.*;
/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void printYoutubeLinks() {
        URLResource page = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : page.words()) {
            String wordLowered = word.toLowerCase();
            int occurence = wordLowered.indexOf("youtube.com");
            if(occurence != -1) {
                //System.out.println(word);
                //System.out.println("**********************************" + occurence + "******************************");
                int startIndex = word.indexOf("\"");
                int stopIndex = word.indexOf("\"", startIndex + 1);
                String url = word.substring(startIndex, stopIndex + 1);
                System.out.println(url);
                
            }
            
        }
    }
}
