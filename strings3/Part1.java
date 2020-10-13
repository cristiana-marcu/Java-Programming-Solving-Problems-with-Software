import edu.duke.*;
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1) {
            if((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if(minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public void printAllGenes(String dna) {
        int startIndex = 0;
        while(true) {
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }

    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        while(true) {
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()) {
                break;
            }
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return sr;
    }

    public float cgRatio(String dna) {
        int count = 0;
        for(int i = 0; i < dna.length(); i++){
            String character = dna.substring(i, i+1);
            if(character.equals("C") || character.equals("G")) {
                count++;
            }
        }
        return (float) count / dna.length();
    }

    public int countCTG(String dna) {
        int count = 0;
        for(int i = 0; i < dna.length() - 2; i++){
            String character = dna.substring(i, i+3);
            System.out.println(character);
            if(character.equals("CTG")) {
                count++;
            }
            System.out.println("-------------");
        }
        return count;
    }

    public void processGenes(StorageResource sr){
        int countGreaterThan9 = 0;
        int cgRatioGreaterThan35 = 0;
        int maxLength = 0;
        
        for (String item : sr.data()) {
            if(item.length() > 60) {
                System.out.println(item);
                countGreaterThan9++;
            }
            
            if(cgRatio(item) > 0.35) {
                System.out.println(item);
                cgRatioGreaterThan35++;
            }
            
            if(item.length() > maxLength) {
                maxLength = item.length();
            }
        }
        System.out.println(countGreaterThan9);
        System.out.println(cgRatioGreaterThan35);
        System.out.println(maxLength);
    }

    public void testCountCTG() {
        String dna = "CTGCTGCTGX";
        System.out.println(countCTG(dna));
    }

    public void testCGRatio() {
        String dna = "CGTCGTATGCGTCGTCGTTAX";
        System.out.println(cgRatio(dna));
    }
    
    public void testProcessGenes() {
        /*StorageResource sr = new StorageResource();
        sr.add("CGTCGTATGCGTCGTCGTTAA");
        sr.add("ATGCCCTAA");
        sr.add("ATGCGCGCGCGCTAA");
        sr.add("ATGTATATATATATATAA");
        processGenes(sr);*/
        
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        StorageResource sr = getAllGenes(dna.toUpperCase());
        
        processGenes(sr);
    }

    public void testGetAllGenes() {
        //String dna1 = "CGTCGTATGCGTCGTCGTTAAATGCCCGGGTGA";
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        System.out.println(dna);
        StorageResource genes = getAllGenes(dna.toUpperCase());
        for(String gene: genes.data()){
            System.out.println(gene);
        }

    }
}
