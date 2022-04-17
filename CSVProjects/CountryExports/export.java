
/**
 * Write a description of export here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class export {
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String nation = record.get("Country");
            if (nation.contains(country)) {
                String info = nation + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return info;
            } 
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for(CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for(CSVRecord record : parser) {
            String currentAmount  = record.get("Value (dollars)");
            if(currentAmount.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    
    public void test() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("countryInfo");
        System.out.println(countryInfo(parser, "Nauru"));
        
        System.out.println();
        System.out.println("listExportersTwoProducts");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        System.out.println();
        System.out.println("numberOfExporters");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "sugar"));
        
        System.out.println();
        System.out.println("bigExporters");
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        
    }
        

}
