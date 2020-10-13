
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences (String stringa, String stringb) {
        int firstOccurence = stringa.indexOf(stringb);
        int secondOccurence = stringa.indexOf(stringb, firstOccurence + stringb.length());
        System.out.println(firstOccurence);
        System.out.println(secondOccurence);
        if(firstOccurence != -1 && secondOccurence != -1) {
            return true;
        }
        return false;
    }
    
    public String lastPart(String stringa, String stringb) {
        int occurence = stringb.indexOf(stringa);
        if(occurence != -1) {
            String result = stringb.substring(occurence + stringa.length());
            return result;
        }
        return stringb;
    }
    
    public void testing() {
        
        //twoOccurences testing
        String string1 = "A story by Abby Long";
        boolean test1 = twoOccurences(string1, "by");
        System.out.println(test1);
        
        String string2 = "banana";
        boolean test2 = twoOccurences(string2, "a");
        System.out.println(test2);
        
        String string3 = "actggtacttactg";
        boolean test3 = twoOccurences(string3, "agt");
        System.out.println(test3);
        
        //lastPart testing
        String stringb4 = "banana";
        String stringa4 = "an";
        String test4 = lastPart(stringa4, stringb4);
        System.out.println("The part of the string after " + stringa4 + " in " + stringb4 + " is " + test4);
        
        String stringb5 = "forest";
        String stringa5 = "zoo";
        String test5 = lastPart(stringa5, stringb5);
        System.out.println("The part of the string after " + stringa5 + " in " + stringb5 + " is " + test5);
    }
}
