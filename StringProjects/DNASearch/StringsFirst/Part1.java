
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        int start = dna.indexOf("ATG");
        if (start == -1) {
            return "";
        }
        int end = dna.indexOf("TAA", start + 3);
        if (end == -1) {
            return "";
        }
        result = dna.substring(start, end + 3);
        
        if((end - start) % 3 == 0) {
            return result;
        } else return "";
    }
    public void testSimpleGene() {
        String dna = "TAHHSTTATGJSLLAIIJSJKKSAJTAAJAH";
        System.out.println("DNA strands is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "TAHHSTTJSLLAIIJSJKKSAJTAAJAH";
        System.out.println("DNA strands is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "TAHHSTTATGJSLLAIIJSJKKSAJJAH";
        System.out.println("DNA strands is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "TAHHSTTATGJSLLAIIJSJKKSAJTAAJAH";
        System.out.println("DNA strands is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "TAHHSTTATGJSLLAIIJSJKKSAJKTAAJAH";
        System.out.println("DNA strands is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }
   
        
        
        
        
        
        
        

}
