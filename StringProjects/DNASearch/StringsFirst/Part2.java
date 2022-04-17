
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, int startCodon, int endCodon) {
        String result = "";
        String tempResult = "";
        startCodon = dna.indexOf("ATG");
        endCodon = dna.indexOf("TAA", startCodon + 3);
        if (startCodon == -1 || endCodon == -1) {
            return "";
        } else {
        result = dna.substring(startCodon, endCodon + 3);
    }   
    if(result.length() % 3 == 0 && result.startsWith("ATG")) {
            return result.toUpperCase();
        } else if (result.length() % 3 == 0 &&  result.startsWith("atg")) {
            return result.toLowerCase();
        } else return "";
    }

    public void testSimpleGene() {
        String dna = "kjjdksksjatgahsjuktaadjdhj";
        
        int startCodon = dna.indexOf("ATG");
        int endCodon = dna.indexOf("TAA",startCodon+3);
        
        
        System.out.println("DNA strands is " + dna);
        String gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene);
        
        dna = "TAHHSTTJSLLAIIJSJKKSAJTAAJAH";
        System.out.println("DNA strands is " + dna);
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene);
        
        dna = "TAHHSTTATGJSLLAIIJSJKKSAJJAH";
        System.out.println("DNA strands is " + dna);
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene);
        
        dna = "TAHHSTTATGJSLLAIIJSJKKSAJTAAJAH";
        System.out.println("DNA strands is " + dna);
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene);
        
        dna = "TAHHSTTATGJSLLAIIJSJKKSAJKTAAJAH";
        System.out.println("DNA strands is " + dna);
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene);
    }

}
