class szulo
 {
   protected int kor;
   protected String nev;
	
   public void setNev(String neve)
   {
	nev = neve;
   }

}

class gyerek extends szulo
 {
	public String getNev() 
	{
		return nev;
    }
 }   

class szuloGyerek
{
 public static void main (String args[])
{
	szulo l = new gyerek();
	l.setNev("szülő");
	gyerek k = new gyerek();
	k.setNev("gyerek.");
	System.out.println(k.getNev() + " " + l.getNev());
 }
}

