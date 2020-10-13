
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
    
    public void testFindStopCodon(){
        String dna1 = "xxxxxTAAxxxxxxTAA";
        int test1 = findStopCodon(dna1, 5, "TAA");
        System.out.println(test1);
    }
    
    public void testFindGene() {
        String dna1 = "CGTCGTCGTCGTCGTACCGTT";
        System.out.println(dna1);
        System.out.println(findGene(dna1, 0));
        System.out.println("------------");
        
        String dna2 = "CGTCGTATGCGTCGTCGTTAA";
        System.out.println(dna2);
        System.out.println(findGene(dna2, 0));
        System.out.println("------------");
        
        String dna3 = "ATGCGTCGTCGTTAACGTTAA";
        System.out.println(dna3);
        System.out.println(findGene(dna3, 0));
        System.out.println("------------");
        
        String dna4 = "ATGCGTCGTCGT";
        System.out.println(dna4);
        System.out.println(findGene(dna4, 0));
    }
}
