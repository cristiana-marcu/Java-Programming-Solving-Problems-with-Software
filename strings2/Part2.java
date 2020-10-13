
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int startIndex = 0;
        int count = 0;
        while(true) {
            startIndex = stringb.indexOf(stringa, startIndex);
            if(startIndex != -1) {
                count++;
                startIndex = startIndex + stringa.length();
            } else {
                break;
            }
        }
        return count;
    }
    
    public void testhowMany() {
        int test1 = howMany("ma","mamamama");
        System.out.println(test1);
    }
}
