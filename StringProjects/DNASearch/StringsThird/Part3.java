
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.Math;
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.toUpperCase().indexOf(stopCodon, startIndex + 3);
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
    
    public String findGene(String dna, int where) {
        int startIndex = dna.toUpperCase().indexOf("ATG",  where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex =  findStopCodon(dna, startIndex, "TAA");
        int tagIndex =  findStopCodon(dna, startIndex, "TAG");
        int tgaIndex =  findStopCodon(dna, startIndex, "TGA");
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
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            //System.out.println(currentGene);
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) +
                         currentGene.length();
        }
        return  geneList;
    }
    
    public void testGetAllGenes() {
        String dna = "TCCAGCAGATTGTGAGATGTGAACTTCCAAGACTAATAAGCACCTCATAACGGTACCATCATAATTCCCACGGGAAAAGTCTTTCGTCTCCCTAATGAGGGCCGTTCGGCTGATGGTATCCTTGGAATCCCAACACCCTCCCGTAGTAAATGCCGATAGTTTAACGCTTTGACGGACCCATGGTTGGTCGTTACAGATACCAGACTGACTACCCCTATTTCAATCACGGCGAGACACTTCAAAGGGGAGCCACTTTGGATGCGTAACTGGAGGTGACGTCAAAACATACTTTTCTAGGCGTGTAAGGGACGGAAGGTATGCAAAAAAGGATTAGACGACAACGTTCCAGATGAGGGAAGCCTCAATACTACGGCTTCAAATTAAACAGGGACACTTATATTCTTAGGAGAGACACGGGAGCCAACGTCTCGGGGGTCGCTATTTCGGGGACTAAGCGCTGGCTCATCGTCGTATAAGAGCGAGACGCTACAGTACATCTATAGAGTGTGCTAAGGATCACTTGCATAACCTAGAAGCCAGGTGATCCGACTAATCCTGACCCATAGGCTCAGGTTGGTTT";
        StorageResource geneList = getAllGenes(dna);
        
        System.out.println("testing getAllGenes on " + dna);
        for(String gene : geneList.data()) {
            System.out.println("Gene: " + gene);
        }   
    }
    
    public double cgRatio(String dna) {
        double occure = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.toUpperCase().charAt(i) == 'C' || dna.toUpperCase().charAt(i) == 'G') {
                occure++;
            }
        }
        return occure/(double)dna.length();
    }
    
    public void processGenes(StorageResource sr) {
        int lengthCount = 0;
        int cgRatioCount = 0;
        int longest = Integer.MIN_VALUE;
        for (String gene : sr.data()) {
            int currentLength = gene.length();
            double  cgRatio = cgRatio(gene);
            
            System.out.println("CG Ratio " + cgRatio);
            
            if (currentLength > 60) {
                System.out.println("Longer then 60 " + gene);
                lengthCount++;
            }
            
            if (cgRatio > 0.35) {
                System.out.println("cg ratio higher than 0.35 " + gene);
                cgRatioCount++;
            }
            
            longest = Math.max(longest, currentLength);
        }
        
        System.out.println("total genes: " + sr.size());
        System.out.println("total gene longer than 60: " + lengthCount);
        System.out.println("total gene with cg ratio higher than 0.35: " + cgRatioCount);
        System.out.println("length of longest dna: " + longest);
    }
    
    /*public void testProcessGenes() {
        String dna = "TCCAGCAGATTGTGAGATGTGAACTTCCAAGACTAATAAGCACCTCATAACGGTACCATCATAATTCCCACGGGAAAAGTCTTTCGTCTCCCTAATGAGGGCCGTTCGGCTGATGGTATCCTTGGAATCCCAACACCCTCCCGTAGTAAATGCCGATAGTTTAACGCTTTGACGGACCCATGGTTGGTCGTTACAGATACCAGACTGACTACCCCTATTTCAATCACGGCGAGACACTTCAAAGGGGAGCCACTTTGGATGCGTAACTGGAGGTGACGTCAAAACATACTTTTCTAGGCGTGTAAGGGACGGAAGGTATGCAAAAAAGGATTAGACGACAACGTTCCAGATGAGGGAAGCCTCAATACTACGGCTTCAAATTAAACAGGGACACTTATATTCTTAGGAGAGACACGGGAGCCAACGTCTCGGGGGTCGCTATTTCGGGGACTAAGCGCTGGCTCATCGTCGTATAAGAGCGAGACGCTACAGTACATCTATAGAGTGTGCTAAGGATCACTTGCATAACCTAGAAGCCAGGTGATCCGACTAATCCTGACCCATAGGCTCAGGTTGGTTT";
        StorageResource geneList = getAllGenes(dna);
        processGenes(geneList);
        
        dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        geneList = getAllGenes(dna);
        processGenes(geneList);
    }*/
    
    public void testProcessGenes() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        
        StorageResource geneList = getAllGenes(dna.toUpperCase());
        processGenes(geneList);
    }
}

