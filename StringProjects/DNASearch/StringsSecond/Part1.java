
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int length = currIndex - startIndex;
            if (length % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return -1;
    }
    
    public void testFindStopCodon() {
        String dna ="xxxccccsssTAAzzzxxxcccTAAzzz";
        int dex = findStopCodon(dna,0,"TAA");
      
        if (dex != -1) System.out.println("error");
        
        dex = findStopCodon(dna,10,"TAA");
        
        if (dex != 22) System.out.println("error");
        System.out.println("tests finished");
        
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG",  where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex =  findStopCodon(dna, startIndex, "TAA");
        int tagIndex =  findStopCodon(dna, startIndex, "TAG");
        int tgaIndex =  findStopCodon(dna, startIndex, "TGA");
        //int minIndex = Math.min(taaIndex, Math.min(tagIndex,tgaIndex));
        
        //if (minIndex == dna.length()) {
        //    return  "";
        //} else {
        //    return  dna.substring(startIndex, minIndex + 3);
        //} 
        int minIndex = 0;
        if (taaIndex == -1 || 
            (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || 
            (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void testFindGene() {
        String  dna  ="ZZZXXXCCCTAAKKKTAGHHHTGA";
        System.out.println(dna);
        String gene =  findGene(dna, 0);
        System.out.println("gene is " +  gene);
        
        dna  ="ZZZXXXATGCCCKKKTAAHHHTSA";
        System.out.println(dna);
        gene =  findGene(dna, 0);
        System.out.println("gene is " +  gene);
        
        dna  ="ZZZXXXATGCCCKKKTGAHHHTAA";
        System.out.println(dna);
        gene =  findGene(dna, 0);
        System.out.println("gene is " +  gene);
        
        dna  ="ZZZXXXATGCCCKKKTZZHHHTSA";
        System.out.println(dna);
        gene =  findGene(dna, 0);
        System.out.println("gene is " +  gene);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        //int where = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) +
                         currentGene.length();
        
    
        }
    }
    
    public void testPrintAllGenes()  {
        String dna = "AATGCTAACTAGCTGACTAAT";
        printAllGenes(dna);
    }
}
    
    