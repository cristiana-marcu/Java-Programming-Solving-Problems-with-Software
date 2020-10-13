/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        boolean upper = dna.startsWith("A") || dna.startsWith("C") || dna.startsWith("G") || dna.startsWith("T");
        if(!upper) {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        int startIndex = dna.indexOf(startCodon);
        if(startIndex == -1){
            return "";
        }
        
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if(stopIndex == -1){
            return "";
        }
        
        if((stopIndex - startIndex) % 3 == 0){
            String result = dna.substring(startIndex, stopIndex + 3);
            return result;
        }
        
        return "No gene";
        
    }
    
    public void testSimpleGene() {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        String dna1 = "ACGTCCGAAGCTAAGCTA";
        System.out.println("String 1: " + dna1);
        String gene1 = findSimpleGene(dna1, startCodon, stopCodon);
        System.out.println("Gene: " + gene1);
        
        String dna2 = "ACATGGTCCGAATGCGCTA";
        System.out.println("String 2: " + dna2);
        String gene2 = findSimpleGene(dna2, startCodon, stopCodon);
        System.out.println("Gene: " + gene2);
        
        String dna3 = "ACGATGGAATCCTGCTAAGCTA";
        String dna3Lowered = dna3.toLowerCase();
        System.out.println("String 3: " + dna3Lowered);
        String gene3 = findSimpleGene(dna3Lowered, startCodon, stopCodon);
        System.out.println("Gene: " + gene3);
        
        String dna4 = "ACGATGGAATTGCTAAGCTA";
        System.out.println("String 4: " + dna4);
        String gene4 = findSimpleGene(dna4, startCodon, stopCodon);
        System.out.println("Gene: " + gene4);
        
    }
}