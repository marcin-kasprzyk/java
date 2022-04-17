
/**
 * Write a description of BabyBriths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyBriths {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            System.out.println("Name " + rec.get(0) + 
                                " Gender " + rec.get(1) +
                                " Num Born " + rec.get(2));
        }
    }
    
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys  += numBorn;
            } else {
                totalGirls +=  numBorn;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total boys births = " + totalBoys);
        System.out.println("Total girls births = " + totalGirls);
    }
    
    public int totalBirthsName(int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        int totalBirths = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                int numBorn = Integer.parseInt(rec.get(2));
                totalBirths += numBorn;
            }
        }
        return totalBirths;
    }
    
    public void testTotalBirthsName() {
        int year = 2012;
        String name = "Ethan";
        String gender = "M";
        System.out.println(totalBirthsName(year, name, gender));
    }
    
    public int getRank (int year, String name, String gender) {
        int getRank = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        boolean recFound = false;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currentName = rec.get(0);
            String currentGender = rec.get(1);
            if (currentGender.equals(gender)) {
               getRank++;
               if (currentName.equals(name)) {
                   recFound = true;
                   break;
               }
            }
        }
        if (recFound) {
            return getRank;
        } else return -1;
    }
    
    public int getRankV1 (int year, String name, String gender) {
        int getRank = 0;
       
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        boolean recFound = false;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currentName = rec.get(0);
            String currentGender = rec.get(1);
            int tempNum = Integer.parseInt(rec.get(2));
            
            if (currentGender.equals(gender)) {
                int currentNum = Integer.parseInt(rec.get(2));
                if (currentNum <= tempNum) {
                    getRank++;
                }
            }
                
            if (currentName.equals(name)) {
                   recFound = true;
                   break;
            }
        }
    
        if (recFound) {
            return getRank;
        } else return -1;
    }
        

    
    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        String name = "NO NAME";
        int currentRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currentGender = rec.get(1);
            int tempNum = Integer.parseInt(rec.get(2));
            if (currentGender.equals(gender)) {
               int currentNum = Integer.parseInt(rec.get(2));
               if (currentNum <= tempNum) {
                   currentRank++;
               }
     
               if (currentRank == rank) {
                   name = rec.get(0);
                   break;
               }
            }
        }
        return name;
    }
    
    public void whatIsNameInYear(String name, int year, int NewYear, String gender) {
        int rank = getRankV1(year, name, gender);
        String newName = getName(NewYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName +
                            " if she was born in " + NewYear);
        
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int year = Integer.MIN_VALUE;
        int rank = Integer.MAX_VALUE;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRankV1(currentYear, name, gender);
            if (currentRank != -1 && currentRank < rank) {
                rank = currentRank;
                year = currentYear;
            }
        }
        if (year  == Integer.MIN_VALUE) {
            return - 1;
        } else return year;
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double count = 0;
        double totalRank = 0;
        for (File f : dr.selectedFiles()) {
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            double currentRank = getRank(currentYear, name, gender);
            if (currentRank != -1) {
                totalRank += currentRank;
                count++;
            }
        }
        if (count == 0) {
            return -1.0;
        } else return totalRank/count;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv" );
        int rank = getRank(year, name, gender);
        int numberBirths = totalBirthsName(year, name, gender);
        int totalBirths = 0;
        int currentRank = 0;
        
        for (CSVRecord record : fr.getCSVParser(false)) {
            String currentGender = record.get(1);
            
            if (currentGender.equals(gender)) {
                int currNum = Integer.parseInt(record.get(2));
                //String currName = record.get(0);

                
                if (currNum >= numberBirths && !name.equals(record.get(0))) {
                    //int currentBirths = Integer.parseInt(record.get(2));
                    totalBirths += currNum;
                } else break;
                
            }
        }
        
        return totalBirths;
        
    }
    
    public void testGetTotalBirthsRankedHigher() {
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        System.out.println("Total births higher than " + name + " is " + getTotalBirthsRankedHigher(year, name, gender));
    }
    
    public void testGetAverageRank() {
        String name = "Susan";
        String gender = "F";
        System.out.println(getAverageRank(name, gender));
    }  
    
    
    public void testYearOfHighestRank() {
        String name = "Mich";
        String gender = "M";
        System.out.println(name + " most popular year is " + yearOfHighestRank(name, gender));
    }
    
    public void testGetName() {
        int year = 1982;
        int rank = 450;
        String gender = "M";
        
        String name = getName(year, rank, gender);
        System.out.println(year + " rank " + rank + " is " + name);
    }
    
    public void testGetRank() {
        String name = "Frank";
        String gender = "M";
        System.out.println(name + " rank is " + getRank(1971, name, gender));
        
    }
    
    public void testGetRankV1() {
        String name = "Frank";
        String gender = "M";
        System.out.println(name + " rank is " + getRankV1(1970, name, gender));
        
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}
