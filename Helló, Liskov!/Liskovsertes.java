class tank
{
	 public void javit()
	 {
		System.out.print("Tudok Lánctalpat Javítani!\n");
	 }
}

class ebr extends tank // Az ebr tud lánctalpat javítani
{
}

public class Liskovsert
{
	 public static void fgv(tank tank)
	 {
		  tank.javit();
	 }

	 public static void main(String[] args)
	{
        tank ebr = new ebr();
        
        fgv(ebr);
		 // sérül az LSP, mert a P::fgv megjavítaná az ebr lánctalpát, ami lehetetlen mert az ebr kerekes

	}
}

