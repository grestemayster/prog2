package Binfa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LZWtest {

	LZWBinFa testBinfa = new LZWBinFa();
    
    @Test
    public void testOperator() {
    	String input = "01111001001001000111";    
        
    	for(char b: input.toCharArray()){
            testBinfa.operator(b);
        }
        
        assertEquals(4, testBinfa.getMelyseg());
        assertEquals(2.75, testBinfa.getAtlag(), 0.001);
        assertEquals(0.957427, testBinfa.getSzoras(), 0.000001);	
    }

}