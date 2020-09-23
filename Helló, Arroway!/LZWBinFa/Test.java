import java.util.ArrayList;

public class Test 
{

	public static void main(String[] args) {
		LZWBinFa binfa1 = new LZWBinFa();
		LZWBinFa binfa2 = new LZWBinFa();
		LZWBinFa binfa3 = new LZWBinFa();
		LZWBinFa binfa4 = new LZWBinFa();
		LZWBinFa binfa5 = new LZWBinFa();
		
		for (char c : "011000010110001001100011".toCharArray()) 
		{
			binfa1.egyBitFeldolg(c);
		}
		for (char c : "0".toCharArray()) 
		{
			binfa2.egyBitFeldolg(c);
		}
		for (char c : "011001000110010101100110".toCharArray()) 
		{
			binfa3.egyBitFeldolg(c);
		}
		for (char c : "01100001".toCharArray()) 
		{
			binfa4.egyBitFeldolg(c);
		}
		for (char c : "01100010".toCharArray()) 
		{
			binfa5.egyBitFeldolg(c);
		}

		ArrayList<LZWBinFa> list = new ArrayList<LZWBinFa>();

		list.add(binfa1);
		list.add(binfa2);
		list.add(binfa3);
		list.add(binfa4);
		list.add(binfa5);
		
		for (int i = 0; i < list.size(); i++) 
		{
			System.out.println("Mélység: "+list.get(i).getMelyseg() + ", átlag: " + list.get(i).getAtlag());
		}
		
		java.util.Collections.sort(list);
		
		System.out.println("Rendezés után:");

		for (int i = 0; i < list.size(); i++) 
		{
			System.out.println("Mélység: "+list.get(i).getMelyseg() + ", átlag: " + list.get(i).getAtlag());
		}
	}

}