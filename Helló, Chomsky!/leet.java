import java.util.*;

class LeetCypher 
{
	private static String atalakitando = new String();

	private static String atalakitott = new String();

	Map<String, String> character = new HashMap<String, String>();

	public void print(String leet) 
	{
		System.out.println(leet);
	};

	public void atalakit(String szo) 
	{
		character.put("A", "4");
		character.put("a", "4");
		character.put("B", "8");
		character.put("b", "8");
		character.put("C", "<");
		character.put("c", "<");
		character.put("D", "|)");
		character.put("d", "o|");
		character.put("E", "3");
		character.put("e", "3");
		character.put("F", "|=");
		character.put("G", "(");
		character.put("g", "9");
		character.put("H", "|-|");
		character.put("h", "|-|");
		character.put("I", "1");
		character.put("i", "1");
		character.put("J", "_|");
		character.put("j", "_|");
		character.put("K", "|<");
		character.put("k", "|<");
		character.put("L", "|_");
		character.put("l", "|_");
		character.put("M", "44");
		character.put("m", "44");
		character.put("N", "И");
		character.put("n", "И");
		character.put("O", "0");
		character.put("o", "0");
		character.put("P", "|o");
		character.put("p", "|o");
		character.put("q", "O__");
		character.put("Q", "O_");
		character.put("r", "Я");
		character.put("R", "Я");
		character.put("s", "5");
		character.put("S", "5");
		character.put("t", "7");
		character.put("T", "7");
		character.put("u", "|_|");
		character.put("U", "|_|");
		character.put("v", "|/");
		character.put("V", "|/");
		character.put("w", "Ш");
		character.put("W", "Ш");
		character.put("x", "><");
		character.put("X", "><");
		character.put("y", "`/");
		character.put("Y", "`/");
		character.put("z", "2");
		character.put("Z", "2");
		character.put(" ", " ");
		for (int i = 0; i < szo.length(); i++)
			if (character.get(szo.substring(i, i + 1)) != null)
				atalakitando += character.get(szo.substring(i, i + 1)) + " ";

	};

	public LeetCypher() 
	{

		atalakit(atalakitott);
		print(atalakitando);  //átalakítva
	}


	public static void main(String args[]) 
	{
		StringBuilder builder = new StringBuilder(atalakitott.length());
			for (String str : args) 
			{
				builder.append(str + ' ');
				
			}
	
			atalakitott = builder.toString();
			
			new LeetCypher();
	
	}

}
