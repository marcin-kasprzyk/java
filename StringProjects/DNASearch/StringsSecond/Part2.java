
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int totalOccure = 0;
        int start = 0;
        
        while (true) {
            int index = stringb.indexOf(stringa, start);
            
            if (index == -1) {
                break;
            }
            
            totalOccure++;
            start = index + stringa.length();
        }
        
        return totalOccure;
    }
    
    
    public void testHowMany(){
        String stringa = "TAA";
        String stringb = "ASTAATAAKKKTAAATTAA";
        int result = howMany(stringa, stringb);
        System.out.println(result);
        
        stringa = "AA";
        stringb = "TAAAAAAA";
        result = howMany(stringa, stringb);
        System.out.println(result);
        
        
    }
    
}
