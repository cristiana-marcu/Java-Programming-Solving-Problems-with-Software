
/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.ArrayList;

public class CSVMax {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallerSoFar = null;
        for(CSVRecord currentRow : parser) {
            if(smallerSoFar == null) {
                smallerSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallerTemp = Double.parseDouble(smallerSoFar.get("TemperatureF"));
                if(currentTemp < smallerTemp) {
                    smallerSoFar = currentRow;
                }
            }
        }
        return smallerSoFar;
    }
    
    public String fileWithColdestTemperature() {
        
        String myFile = null;
        CSVRecord smallerSoFar = null;
        
        DirectoryResource dr = new DirectoryResource();
        
        ArrayList<String> filePaths = new ArrayList<String>();
        filePaths.add("nc_weather/2012/weather-2012-01-01.csv");
        filePaths.add("nc_weather/2012/weather-2012-01-02.csv");
        filePaths.add("nc_weather/2012/weather-2012-01-03.csv");
        
        for (String filePath : filePaths) {
            
            FileResource fr = new FileResource(filePath);
            
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = coldestHourInFile(parser);
            
            if(smallerSoFar == null) {
                smallerSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallerTemp = Double.parseDouble(smallerSoFar.get("TemperatureF"));
                if(currentTemp < smallerTemp) {
                    smallerSoFar = currentRow;
                    myFile = filePath;
                }
            }
        }
        
        return myFile;
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser)  {
        CSVRecord lowestSoFar = null;
        for(CSVRecord currentRow : parser) {
            if(lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));
                if(currentHumidity < lowestHumidity) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles () {
        CSVRecord lowestSoFar = null;
        
        DirectoryResource dr = new DirectoryResource();
        
        ArrayList<String> filePaths = new ArrayList<String>();
        filePaths.add("nc_weather/2014/weather-2014-01-19.csv");
        filePaths.add("nc_weather/2014/weather-2014-01-20.csv");
        filePaths.add("nc_weather/2014/weather-2014-01-21.csv");
        
        for (String filePath : filePaths) {
            
            FileResource fr = new FileResource(filePath);
            
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = lowestHumidityInFile(parser);
            
            if(lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));
                if(currentHumidity < lowestHumidity) {
                    lowestSoFar = currentRow;
                }
            }
        }
        
        return lowestSoFar;
    }
    
    public double averageTemperatureInFile (CSVParser parser) {
        double numberOfRecords = 0;
        double total = 0;
        for(CSVRecord currentRow : parser) {
            double currentTemperature = Double.parseDouble(currentRow.get("TemperatureF"));
            total = total + currentTemperature;
            numberOfRecords++;
        }
        return total/numberOfRecords;
    }
    
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double numberOfRecords = 0;
        double total = 0;
        for(CSVRecord currentRow : parser) {
            double currentTemperature = Double.parseDouble(currentRow.get("TemperatureF"));
            int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
            if(currentHumidity >= value) {
                total = total + currentTemperature;
                numberOfRecords++;
            }
            
        }
        return total/numberOfRecords;
    }
        
    public void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVParser parser = fr.getCSVParser();
        
        CSVRecord smaller = coldestHourInFile(parser);
        System.out.println("Coldest temperature was " + smaller.get("TemperatureF") + " at " + smaller.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature() {
        System.out.println(fileWithColdestTemperature());
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-04-01.csv");
        CSVParser parser = fr.getCSVParser();
        
        CSVRecord lowest = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-06-01.csv");
        CSVParser parser = fr.getCSVParser();
        
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature is " + average);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile () {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-03-30.csv");
        CSVParser parser = fr.getCSVParser();
        
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(Double.isNaN(average)) {
            System.out.println("No temperatures with that humidity");
        }
        System.out.println("Average Temp when high Humidity is " + average);
    }
}
