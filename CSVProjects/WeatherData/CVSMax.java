
/**
 * Write a description of CVSMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CVSMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargerOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    
    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargerOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    
    public CSVRecord getLargerOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
                largestSoFar = currentRow;
            } else {
                double currentTemp  = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp  = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                }
            }
        return largestSoFar; 
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallerSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallerSoFar = getSmallerOfTwo(currentRow, smallerSoFar);
        }
        return smallerSoFar;
        
        
    }
    
    public CSVRecord getSmallerOfTwo(CSVRecord currentRow, CSVRecord smallerSoFar) {
        if (smallerSoFar == null) {
                smallerSoFar = currentRow;
            } else {
                double currentTemp  = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallerTemp  = Double.parseDouble(smallerSoFar.get("TemperatureF"));
                if (currentTemp < smallerTemp) {
                    smallerSoFar = currentRow;
                }
            }
        return smallerSoFar; 
    }
    
    public File fileWithColdestTemperature() {
        CSVRecord smallerSoFar = null;
        File file = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (smallerSoFar == null) {
                smallerSoFar = currentRow;
                file = f;
            } else {
                double currentTemp  = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallerTemp  = Double.parseDouble(smallerSoFar.get("TemperatureF"));
                if (currentTemp < smallerTemp) {
                    smallerSoFar = currentRow;
                    file = f;
                }
            }
            
        }
        return file;
        
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
        
    }
    
    public CSVRecord highestHumidityInFile(CSVParser parser) {
        CSVRecord highestSoFar = null;
        for (CSVRecord currentRow : parser) {
            highestSoFar = getHighestOfTwo(currentRow, highestSoFar);
        }
        return highestSoFar;
        
    }
    
    public CSVRecord getHighestOfTwo(CSVRecord currentRow, CSVRecord highestSoFar) {
        String na = "N/A";
        if (highestSoFar == null) {
                highestSoFar = currentRow;
            } else if  (!currentRow.get("Humidity").equals(na)) {
                 
                double currentTemp  = Double.parseDouble(currentRow.get("Humidity"));
                double highestTemp  = Double.parseDouble(highestSoFar.get("Humidity"));
                if (currentTemp > highestTemp) {
                    highestSoFar = currentRow;
                }
            }
        return highestSoFar; 
    
    }
    
    public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
        String na = "N/A";
        if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else if  (!currentRow.get("Humidity").equals(na)) {
                 
                double currentTemp  = Double.parseDouble(currentRow.get("Humidity"));
                double lowestTemp  = Double.parseDouble(lowestSoFar.get("Humidity"));
                if (currentTemp < lowestTemp) {
                    lowestSoFar = currentRow;
                }
            }
        return lowestSoFar; 
    
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
        
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double averageTemp = 0;
        double count = 0;
        for (CSVRecord record : parser) {
            double currentTemp  = Double.parseDouble(record.get("TemperatureF"));
            averageTemp = currentTemp + averageTemp;
            count++;
        }
        return averageTemp/count;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        /*double averageTemp  = 0;
        CSVRecord csvHumidity = highestHumidityInFile(parser);
        double humidity  = Double.parseDouble(csvHumidity.get("Humidity"));
        if (humidity >= value) {
            averageTemp = averageTemperatureInFile(parser);
        }
        return averageTemp;*/
        
        double totalTemperature = 0;
        int recordCount = 0;
        for (CSVRecord record : parser) {
               int currentHumidity = Integer.parseInt(record.get("Humidity"));
                    if (currentHumidity >= value) {
                        double currentTemperature = Double.parseDouble(record.get("TemperatureF"));
                        totalTemperature += currentTemperature;
                        recordCount++;
                    }
        }
        
        if (recordCount == 0) {
            return Double.NEGATIVE_INFINITY;
        } else {
            return totalTemperature / recordCount;
        }
        
    }
    
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double averageTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (averageTemp == Double.NEGATIVE_INFINITY) {
            System.out.println("No temperature with that humidity");
        } else {
            System.out.println("Average temperature when high humidity is " + averageTemp);
        }
    }
        
    
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Średnia temperatura w pliku wynosiła " + average);
    }
    
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Najniższa wilgotność wynosiła " + lowest.get("Humidity") +
                            " w dniu " + lowest.get("DateUTC"));
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Najniższa wilgotność wynosiła " + csv.get("Humidity") + " w dniu " + csv.get("DateUTC"));
    }
    
    public void printAllRecords(CSVParser parser) {
        for (CSVRecord record : parser) {
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
    }
    
    public void testFileWithColdestTemperature() {
        File file =  fileWithColdestTemperature();
        System.out.println("Najzimniejszy dzień w wybranych plikach " + file.getName());
        
        FileResource fr = new FileResource(file);
        String coldestTemperature = coldestHourInFile(fr.getCSVParser()).get("TemperatureF");
        System.out.println("Najniższa temperatura tegodnia była " + coldestTemperature);
        
        System.out.println("Wszytskie temperatury z najzimniejszego dnia to ");
        printAllRecords(fr.getCSVParser());
        
        
    }
    
    public void testHottestInDay() {
        FileResource fr = new FileResource("nc_weather/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Najwyższa temperatura wynosiła " + largest.get("TemperatureF") +
                            " o godzinie " + largest.get("TimeEST"));
    }
    
    public void testHottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("Najwyższa temperatura wynosiła " + largest.get("TemperatureF") +
                            " w dniu " + largest.get("DateUTC"));
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2015/weather-2015-01-01.csv");
        CSVRecord smaller = coldestHourInFile(fr.getCSVParser());
        System.out.println("Najniższa temperatura wynosiła " + smaller.get("TemperatureF") +
                            " o godzinie " + smaller.get("TimeEST"));
    }
}
