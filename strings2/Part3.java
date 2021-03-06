
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
    
    public int countGenes(String dna) {
        int startIndex = 0;
        int count = 0;
        while(true) {
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()) {
                break;
            }
            count = count + 1;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return count;
    }
    
    public void testCountGenes() {
        System.out.println(countGenes("ATGCCCTAAATGCCCCCCTGAATGCCCTAGCCATGTAA"));
    }
}
