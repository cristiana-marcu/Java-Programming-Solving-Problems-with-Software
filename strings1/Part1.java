
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
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
        String dna1 = "ACGTCCGAAGCTAAGCTA";
        System.out.println("String: " + dna1);
        String gene1 = findSimpleGene(dna1);
        System.out.println("Gene: " + gene1);
        
        String dna2 = "ACATGGTCCGAATGCGCTA";
        System.out.println("String: " + dna2);
        String gene2 = findSimpleGene(dna2);
        System.out.println("Gene: " + gene2);
        
        String dna3 = "ACGATGGAATCCTGCTAAGCTA";
        System.out.println("String: " + dna3);
        String gene3 = findSimpleGene(dna3);
        System.out.println("Gene: " + gene3);
        
        String dna4 = "ACGATGGAATTGCTAAGCTA";
        System.out.println("String: " + dna4);
        String gene4 = findSimpleGene(dna4);
        System.out.println("Gene: " + gene4);
        
    }
}
