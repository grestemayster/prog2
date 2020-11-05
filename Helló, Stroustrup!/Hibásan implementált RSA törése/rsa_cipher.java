import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map.Entry;

class Decode {
	private HashMap<Character, Integer> charRank;
	private String decoded;
	
	public Decode(String str) {
		this.charRank = new HashMap<Character, Integer>();
		this.decoded = str;
		
		this.loadFreqList();
		
		HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c != ' ')
				if(frequency.containsKey(c))
					frequency.put(c, frequency.get(c) + 1);
				else
					frequency.put(c, 1);
		}
		
		while (frequency.size() > 0) {
			int mi = 0;
			char c = 0;
			for (Entry<Character, Integer> e : frequency.entrySet()) {
				if (mi < e.getValue()) {
					mi = e.getValue();
					c = e.getKey();
				}
			}
			this.decoded = this.decoded.replace(c, this.nextFreq());
			frequency.remove(c);
		}
	}
	
	private void loadFreqList() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("gyakorisag.txt"));
			String line;
			while((line = reader.readLine()) != null) {
				String[] args = line.split("\t");
				char c = args[0].charAt(0);
				int num = Integer.parseInt(args[1]);
				this.charRank.put(c, num);
			}
		} catch (Exception e) {
			System.out.println("Error when loading list -> " + e.getMessage());
		}		
	}
	
	private char nextFreq() {
		char c = 0;
		int nowFreq = 0;
		for(Entry<Character, Integer> e : this.charRank.entrySet()) {
			if (e.getValue() > nowFreq) {
				nowFreq = e.getValue();
				c = e.getKey();
			}
		}
		if (this.charRank.containsKey(c))
			this.charRank.remove(c);
		return c;
	}
	
	public String getDecoded() {
		return this.decoded;
	}
}

public class rsa_cipher {
	public static void main(String[] args) {
		int bitlength = 2100;
		
		SecureRandom random = new SecureRandom();
		
		BigInteger p = BigInteger.probablePrime(bitlength/2, random);
		BigInteger q = BigInteger.probablePrime(bitlength/2, random);
		
		BigInteger publicKey = new BigInteger("65537");
		BigInteger modulus = p.multiply(q);
		
		String str = "this is a perfect string".toUpperCase();
		System.out.println("Eredeti: " + str);
		
		byte[] out = new byte[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == ' ')
				out[i] = (byte)c;
			else
				out[i] = new BigInteger(new byte[] {(byte)c}).modPow(publicKey, modulus).byteValue();
		}
		String encoded = new String(out);
		System.out.println("Kodolt:" + encoded);
		
		Decode de = new Decode(encoded);
		System.out.println("Visszafejtett: " + de.getDecoded());
	}
}
