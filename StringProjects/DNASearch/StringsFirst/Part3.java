
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int firstOccure = stringb.indexOf(stringa);
        int secondOccure = stringb.indexOf(stringa, firstOccure + 1);
        if (secondOccure == -1) {
            return false;
        } else {
            return true;
        }
    }
     public void testing(){
        String stringa = "by";
        String stringb = "A story by Abby Long";
        System.out.println("stringa = "+stringa + " and stringb = " + stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        
        stringa = "a";
        stringb = "bann aa";
        System.out.println("stringa = "+stringa + " and stringb = " + stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        
        stringa = "anna";
        stringb = "malaannabyla na annie";
        System.out.println("stringa = "+stringa + " and stringb = " + stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        
        stringa = "oko";
        stringb = "bardzomaleokooko";
        System.out.println("stringa = "+stringa + " and stringb = " + stringb);
        System.out.println(twoOccurrences(stringa, stringb));
    }
    public String lastPart(String stringa, String stringb) {
        String result = "";
        int occure = stringb.indexOf(stringa);
        if (occure == -1) {
            return stringb;
        } else result = stringb.substring(stringa.length() + occure);
        return result;
    }
    public void testingLastPart(){
        String stringa = "an";
        String stringb = "banana";
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + lastPart(stringa, stringb));
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + lastPart(stringa, stringb));
        
        stringa = "sex";
        stringb = "sexshop";
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + lastPart(stringa, stringb));
    }

}
