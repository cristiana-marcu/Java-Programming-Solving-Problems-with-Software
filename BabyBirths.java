import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource("us_babynames/us_babynames_test/example-small.csv");
        for(CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + 
                    " Gender: " + rec.get(1) +
                    "Num Born " + rec.get(2));
            }

        }
    }

    private void totalBirths (FileResource fr) {

        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;

        int totalNames = 0;
        int boysNames = 0;
        int girlsNames = 0;

        for(CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;

            totalNames++;

            if(rec.get(1).equals("M")) {
                totalBoys += numBorn;
                boysNames++;
            } else {
                totalGirls += numBorn;
                girlsNames++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("Total Names = " + totalNames);
        System.out.println("Total boys names = " + boysNames);
        System.out.println("Total girl names = " + girlsNames);
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource("us_babynames/us_babynames_test/example-small.csv");
        totalBirths(fr);
    }

    private int getRank (int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv");
        int position = 0;

        for(CSVRecord rec : fr.getCSVParser(false)) {

            String recordGender = rec.get(1);

            if(gender.equals(recordGender)) {
                position++;
                String recordName = rec.get(0);
                if(recordName.equals(name)) {
                    return position;
                }
            }

        }
        return -1;
    }

    public void testGetRank() {
        System.out.println(getRank(2012, "Jacob", "M"));
    }

    private String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv");

        int position = 0;

        for(CSVRecord rec : fr.getCSVParser(false)) {

            String recordGender = rec.get(1);

            if(gender.equals(recordGender)) {
                position++;
                String recordName = rec.get(0);
                if(position == rank) {
                    return recordName;
                }
            }

        }
        return "NO NAME";
    }

    public void testGetName() {
        System.out.println(getName(2012, 5, "F"));
    }

    public void whatIsNameInYear( String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);

        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }

    private int yearOfHighestRank( String name, String gender) {

        ArrayList<Integer> years = new ArrayList<Integer>();
        years.add(2012);
        years.add(2013);
        years.add(2014);

        int lowestRank = 100000;
        int yearOfHighestRank = -1;
        for (int year : years) {

            int rank = getRank(year, name, gender);
            if(rank < lowestRank) {
                lowestRank = rank;
                yearOfHighestRank = year;
            }
        }
        return yearOfHighestRank;
    }

    public void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("Sophia", "F"));
    }

    private double getAverageRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int numberOfFiles = 0;
        double averageRank = 0;
        for (File f : dr.selectedFiles()) {
            numberOfFiles++;
            String fileName = f.getName();
            int fileYear = Integer.parseInt(fileName.substring(3, 7));
            int fileRank = getRank ( fileYear, name, gender);
            averageRank = averageRank + fileRank;
        }
        return averageRank / numberOfFiles;
    }

    public void testgetAverageRank() {
        System.out.println(getAverageRank("Sophia", "F"));
    }

    private int getTotalbirthsRankedHigher (int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv");
        int givenNameRank = getRank(year, name, gender);
        int births = 0;
        int currentRank = 1;
        for(CSVRecord rec : fr.getCSVParser(false)) {
            
            if(givenNameRank == currentRank) {
                break;
            }
            
            if(gender.equals(rec.get(1))) {
                currentRank++;
                births += Integer.parseInt(rec.get(2));
            }
        }
        return births;
    }
    
    public void testGetTotalbirthsRankedHigher() {
        System.out.println(getTotalbirthsRankedHigher(2012, "Isabella", "F"));
    }
}
